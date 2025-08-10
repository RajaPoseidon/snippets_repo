"""
advanced_filter_validator.py

This module brings together several best‑practice patterns for building
SQL filters and dataset validations in a dialect‑agnostic way.  It
combines concepts from the `sqlframe` and `sqlmesh` projects, such as
engine‑agnostic table abstractions, rule‑based validations, and
mixins for extensible behaviour.  It also implements a filter
expression parser that converts nested JSON structures into
`sqlglot` expression trees, allowing complex filter logic to be
constructed programmatically.

Classes
-------
Dataset
    Represents a table (or view/query) and holds the target SQL dialect.
    Provides helpers for building qualified column expressions.

ExplainableMixin
    A mixin that adds an ``explain`` method to classes.  Classes that
    define their own attributes can leverage this to produce
    human‑readable descriptions without duplicating code.

FilterNode, SimpleRule, GroupRule
    Classes for representing a filter as a tree.  A ``SimpleRule``
    encapsulates a single comparison (equality, inequality, range,
    pattern match, or membership) on a column.  A ``GroupRule``
    combines multiple child rules with ``AND`` or ``OR``.  The
    ``FilterNode`` base defines the common interface.

ValidationRule and subclasses
    Abstract base for validations applied to an entire dataset.  Specific
    rules include ``NotNullRule``, ``UniqueValuesRule``,
    ``AllowedValuesRule``, and ``RangeRule``.  Each rule returns a
    boolean expression via ``sqlglot`` that can be composed with other
    conditions.

FilterExpression
    Encapsulates parsing of nested JSON into a tree of ``FilterNode``
    objects and exposes methods to obtain a combined SQL expression or
    explanation.  This is similar to how filter UIs output JSON that
    must be converted into SQL.

QueryBuilder
    Given a ``Dataset``, a ``FilterExpression``, and optional
    ``ValidationRule`` instances, this class produces a complete SQL
    query.  The resulting query filters the dataset according to the
    filter expression and enforces all validation rules via ``AND``
    conditions.  It can generate SQL for any supported dialect via
    `sqlglot`.

Example
-------
To demonstrate the usage of these classes, run this module as a script::

    python advanced_filter_validator.py

This will build a sample filter from a nested JSON definition, apply
several validation rules, generate the corresponding SQL for DuckDB,
and print both the SQL and a simplified explanation of the filter and
validation logic.

Dependencies
------------
This code requires the ``sqlglot`` package.  Install it via
``pip install sqlglot`` in your own environment to use these classes.

"""

from __future__ import annotations

from typing import Any, Dict, Iterable, List, Optional, Sequence, Tuple

try:
    from sqlglot import exp  # type: ignore
except ImportError as e:  # pragma: no cover
    raise ImportError(
        "advanced_filter_validator requires sqlglot. Install with 'pip install sqlglot'."
    ) from e


class Dataset:
    """Represents a dataset (table, view, or query) with a target SQL dialect.

    Parameters
    ----------
    table_name : str
        The fully qualified table name (e.g. ``schema.table``) or alias.
    dialect : str, optional
        The target SQL dialect (e.g. ``"snowflake"`` or ``"databricks"``).
        Defaults to ``"snowflake"``.

    This class mirrors the design found in sqlframe, where a DataFrame
    object carries its associated SQL context.  It exposes helper
    methods for building table and column expressions in ``sqlglot``.
    """

    def __init__(self, table_name: str, dialect: str = "snowflake") -> None:
        self.table_name = table_name
        self.dialect = dialect

    def table_expr(self) -> exp.Table:
        """Return a ``sqlglot`` Table expression for this dataset."""
        return exp.table_(self.table_name)

    def column_expr(self, column: str) -> exp.Column:
        """Return a qualified ``sqlglot`` Column expression.

        Parameters
        ----------
        column : str
            Column name without a table prefix.

        Returns
        -------
        sqlglot.expressions.Column
            A column expression qualified by this dataset's table.
        """
        return exp.column(column, table=self.table_name)


class ExplainableMixin:
    """Mixin providing a default ``explain`` method.

    When used with classes that define their own attributes, this mixin
    returns a simple representation of the class name and its public
    attributes.  It can be combined with validation and filter rule
    classes to produce human‑readable descriptions without extra code.
    """

    def explain(self) -> str:  # type: ignore[override]
        params = [f"{k}={v!r}" for k, v in self.__dict__.items() if not k.startswith("_")]
        return f"{self.__class__.__name__}({', '.join(params)})"


class FilterNode:
    """Abstract base class for filter rules and groups."""

    def expression(self, dataset: Dataset) -> exp.Expression:
        """Return a ``sqlglot`` expression for this filter node."""
        raise NotImplementedError

    def explain(self) -> str:
        """Return a human‑readable explanation of this node."""
        return self.__class__.__name__


class SimpleRule(ExplainableMixin, FilterNode):
    """Leaf rule representing a single comparison or membership test.

    Parameters
    ----------
    field : str
        Column name.
    operator : str
        One of ``=``, ``!=``, ``<>``, ``>``, ``<``, ``>=``, ``<=``, ``LIKE``, or ``IN``.
    value : Any
        The comparison value or list of values (for ``IN``).
    """

    def __init__(self, field: str, operator: str, value: Any) -> None:
        self.field = field
        self.operator = operator
        self.value = value

    def _to_literal(self, v: Any) -> exp.Expression:
        """Convert a Python value to a ``sqlglot`` literal expression."""
        return exp.Literal.string(v) if isinstance(v, str) else exp.Literal.number(v)

    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        op = self.operator.upper()
        val = self.value

        if op in {"=", "=="}:
            return exp.EQ(this=col, expression=self._to_literal(val))
        if op in {"!=", "<>"}:
            return exp.NEQ(this=col, expression=self._to_literal(val))
        if op == ">":
            return exp.GT(this=col, expression=self._to_literal(val))
        if op == "<":
            return exp.LT(this=col, expression=self._to_literal(val))
        if op == ">=":
            return exp.GTE(this=col, expression=self._to_literal(val))
        if op == "<=":
            return exp.LTE(this=col, expression=self._to_literal(val))
        if op == "LIKE":
            return exp.Like(this=col, expression=self._to_literal(val))
        if op == "IN":
            if not isinstance(val, Iterable) or isinstance(val, (str, bytes)):
                raise ValueError("IN operator requires a sequence of values")
            exprs = [self._to_literal(v) for v in val]
            return exp.In(this=col, expressions=exprs)
        raise ValueError(f"Unsupported operator: {self.operator}")


class GroupRule(FilterNode):
    """Represents a group of filter nodes combined by AND or OR."""

    def __init__(self, combinator: str, children: Iterable[FilterNode]) -> None:
        combinator = combinator.upper()
        if combinator not in {"AND", "OR"}:
            raise ValueError("combinator must be 'AND' or 'OR'")
        self.combinator = combinator
        self.children: List[FilterNode] = list(children)

    def expression(self, dataset: Dataset) -> exp.Expression:
        child_exprs = [child.expression(dataset) for child in self.children]
        if not child_exprs:
            return exp.true()
        if self.combinator == "AND":
            return exp.and_(*child_exprs)
        return exp.or_(*child_exprs)

    def explain(self) -> str:
        return f"{self.combinator}({', '.join(child.explain() for child in self.children)})"


class FilterExpression:
    """Encapsulates a filter tree built from a nested JSON definition."""

    def __init__(self, root: FilterNode) -> None:
        self.root = root

    @classmethod
    def from_json(cls, node: Dict[str, Any]) -> "FilterExpression":
        """Parse nested JSON into a tree of filter nodes.

        The JSON format must follow this structure:
        - A group node contains a ``combinator`` (``"AND"`` or ``"OR"``) and a
          ``rules`` list of child nodes.
        - A leaf node contains a ``field``, an ``operator``, and a ``value``.
        """
        def parse(n: Dict[str, Any]) -> FilterNode:
            if "field" in n:
                return SimpleRule(n["field"], n["operator"], n.get("value"))
                
            combinator = n.get("combinator")
            if combinator is None:
                raise ValueError("Group rule missing 'combinator'")
            children_defs = n.get("rules", [])
            children = [parse(child) for child in children_defs]
            return GroupRule(combinator, children)
        return cls(parse(node))

    def expression(self, dataset: Dataset) -> exp.Expression:
        """Return the combined ``sqlglot`` expression for this filter."""
        return self.root.expression(dataset)

    def explain(self) -> str:
        """Return a human‑readable explanation of the filter structure."""
        return self.root.explain()


class ValidationRule:
    """Abstract base class for dataset validation rules.

    Each subclass must implement ``expression`` to return a boolean
    ``sqlglot`` expression that is ``True`` for rows that pass the rule.
    """

    def expression(self, dataset: Dataset) -> exp.Expression:
        raise NotImplementedError

    def explain(self) -> str:
        return self.__class__.__name__


class NotNullRule(ExplainableMixin, ValidationRule):
    """Ensure a column contains no NULL values."""

    def __init__(self, column: str) -> None:
        self.column = column

    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.column)
        return exp.IsNot(this=col, expression=exp.Null())


class UniqueValuesRule(ExplainableMixin, ValidationRule):
    """Ensure a combination of columns is unique across the dataset."""

    def __init__(self, columns: Sequence[str]) -> None:
        self.columns = tuple(columns)

    def expression(self, dataset: Dataset) -> exp.Expression:
        cols = [dataset.column_expr(c) for c in self.columns]
        total_count = exp.count(this=exp.Star())
        if len(cols) == 1:
            distinct_count = exp.count(this=cols[0], distinct=True)
        else:
            tuple_expr = exp.Tuple(expressions=cols)
            distinct_count = exp.count(this=tuple_expr, distinct=True)
        return exp.EQ(this=total_count, expression=distinct_count)


class AllowedValuesRule(ExplainableMixin, ValidationRule):
    """Ensure a column's values are within an allowed set."""

    def __init__(self, column: str, allowed: Sequence[Any]) -> None:
        self.column = column
        self.allowed = tuple(allowed)

    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.column)
        exprs = [exp.Literal.string(v) if isinstance(v, str) else exp.Literal.number(v) for v in self.allowed]
        return exp.In(this=col, expressions=exprs)


class RangeRule(ExplainableMixin, ValidationRule):
    """Ensure a numeric column falls within a given inclusive range."""

    def __init__(self, column: str, minimum: Optional[float] = None, maximum: Optional[float] = None) -> None:
        if minimum is None and maximum is None:
            raise ValueError("At least one of minimum or maximum must be provided")
        self.column = column
        self.minimum = minimum
        self.maximum = maximum

    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.column)
        parts: List[exp.Expression] = []
        if self.minimum is not None:
            parts.append(exp.GTE(this=col, expression=exp.Literal.number(self.minimum)))
        if self.maximum is not None:
            parts.append(exp.LTE(this=col, expression=exp.Literal.number(self.maximum)))
        return exp.and_(*parts) if len(parts) > 1 else parts[0]


class QueryBuilder:
    """Build a SQL query combining a filter expression and validation rules.

    Parameters
    ----------
    dataset : Dataset
        The dataset to query.
    filter_expr : FilterExpression
        The filter logic to apply to rows.
    validation_rules : Iterable[ValidationRule], optional
        Additional rules that must hold for each row.  Defaults to an empty list.

    This class is inspired by SQLMesh's approach of bundling audits
    (validation rules) together with model definitions.  By combining
    filters and validations, we can produce a single SQL ``WHERE`` clause
    that enforces both user‑defined filtering logic and quality checks.
    """

    def __init__(self, dataset: Dataset, filter_expr: FilterExpression,
                 validation_rules: Iterable[ValidationRule] = ()) -> None:
        self.dataset = dataset
        self.filter_expr = filter_expr
        self.validation_rules = list(validation_rules)

    def combined_condition(self) -> exp.Expression:
        """Combine filter expression and validation rules into one expression."""
        filter_condition = self.filter_expr.expression(self.dataset)
        rule_conditions = [rule.expression(self.dataset) for rule in self.validation_rules]
        all_conditions = [filter_condition] + rule_conditions if rule_conditions else [filter_condition]
        return exp.and_(*all_conditions) if len(all_conditions) > 1 else all_conditions[0]

    def generate_sql(self, select_all: bool = True) -> str:
        """Generate a SELECT query with the combined conditions.

        Parameters
        ----------
        select_all : bool, optional
            If ``True``, generate ``SELECT *``.  If ``False``, select
            only the columns referenced in the filter and validation rules.
        """
        table_expr = self.dataset.table_expr()
        where_expr = self.combined_condition()
        if select_all:
            query = exp.select("*").from_(table_expr).where(where_expr)
        else:
            # Collect referenced columns from filter and validation rules
            referenced: List[str] = []
            # Extract from SimpleRules
            def collect(node: FilterNode) -> None:
                if isinstance(node, SimpleRule):
                    referenced.append(node.field)
                elif isinstance(node, GroupRule):
                    for child in node.children:
                        collect(child)
            collect(self.filter_expr.root)
            # Extract from validation rules
            for rule in self.validation_rules:
                if isinstance(rule, (NotNullRule, AllowedValuesRule, RangeRule)):
                    referenced.append(rule.column)
                if isinstance(rule, UniqueValuesRule):
                    referenced.extend(rule.columns)
            # Deduplicate while preserving order
            seen = set()
            unique_cols = [c for c in referenced if not (c in seen or seen.add(c))]
            cols = [self.dataset.column_expr(c) for c in unique_cols]
            query = exp.select(*cols).from_(table_expr).where(where_expr)
        return query.sql(dialect=self.dataset.dialect)

    def explain(self) -> str:
        """Return a textual explanation of the combined filter and validations."""
        parts = [f"Filter: {self.filter_expr.explain()}"]
        if self.validation_rules:
            parts.append("Validations: " + ", ".join(rule.explain() for rule in self.validation_rules))
        return "; ".join(parts)

    def run(self, connection) -> Iterable[Tuple]:
        """Execute the generated SQL against a provided connection.

        The connection must implement an ``execute(sql)`` method that
        returns an object with ``fetchall()``, or itself an iterable of
        rows.  This design allows use with Snowflake connectors,
        Databricks/Spark sessions, or any engine that supports SQL
        execution.
        """
        sql = self.generate_sql()
        cursor = connection.execute(sql)
        try:
            return cursor.fetchall()
        except AttributeError:
            return cursor


def _demo() -> None:  # pragma: no cover
    """Internal demonstration of building a query with filters and validations.

    This function builds a sample filter from nested JSON, applies a few
    validation rules, prints the explanation, the AST representation,
    and the final SQL for the DuckDB dialect.  It does not execute
    against a real database.
    """
    # Nested filter JSON mimicking the structure shown in the prompt
    filter_json: Dict[str, Any] = {
        "combinator": "AND",
        "rules": [
            {"field": "field1", "operator": "=", "value": "value1"},
            {"field": "field2", "operator": ">", "value": 10},
            {"field": "field3", "operator": "<", "value": 100},
            {"field": "field4", "operator": "LIKE", "value": "%abc%"},
            {
                "combinator": "OR",
                "rules": [
                    {"field": "field5", "operator": "=", "value": "x"},
                    {"field": "field6", "operator": "!=", "value": "y"},
                    {
                        "combinator": "AND",
                        "rules": [
                            {"field": "field7", "operator": ">=", "value": 5},
                            {
                                "combinator": "OR",
                                "rules": [
                                    {"field": "field8", "operator": "<", "value": 20},
                                    {
                                        "combinator": "AND",
                                        "rules": [
                                            {"field": "field9", "operator": "=", "value": "z"},
                                            {"field": "field10", "operator": ">=", "value": 20},
                                        ],
                                    },
                                ],
                            },
                        ],
                    },
                ],
            },
            {
                "combinator": "AND",
                "rules": [
                    {"field": "field11", "operator": "<", "value": 30},
                    {"field": "field12", "operator": "IN", "value": [1, 2, 3]},
                    {
                        "combinator": "OR",
                        "rules": [
                            {"field": "field13", "operator": "LIKE", "value": "%foo%"},
                            {"field": "field14", "operator": "LIKE", "value": "bar%"},
                        ],
                    },
                ],
            },
        ],
    }
    # Parse filter
    filter_expr = FilterExpression.from_json(filter_json)
    dataset = Dataset("my_schema.my_table", dialect="duckdb")
    # Define some validation rules
    validations = [
        NotNullRule("field1"),
        UniqueValuesRule(["field5", "field6"]),
        RangeRule("field2", minimum=0),
        AllowedValuesRule("field12", [1, 2, 3, 4]),
    ]
    # Build query
    builder = QueryBuilder(dataset, filter_expr, validations)
    print("Explanation:")
    print(builder.explain())
    # Combined SQLGlot expression and SQL
    expr = builder.combined_condition()
    print("\nCombined AST (repr):")
    print(repr(expr))
    sql = builder.generate_sql()
    print("\nGenerated SQL (DuckDB dialect):")
    print(sql)


if __name__ == "__main__":  # pragma: no cover
    _demo()
