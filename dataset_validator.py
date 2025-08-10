"""
dataset_validator.py

This module provides a set of classes to define datasets and validation rules
in an engine‑agnostic way.  It uses the sqlglot library to build SQL
expressions that can be transpiled to the dialect of the underlying engine.

Classes
-------
Dataset
    Represents a table or query in a given SQL dialect.  Provides helper
    methods to build column expressions.

ValidationRule
    Abstract base class for all validation rules.  Subclasses must
    implement the ``expression`` method which returns a sqlglot expression
    representing the validation condition.

Concrete validation rules:
    NotNullRule, UniqueValuesRule, AllowedValuesRule, RangeRule

Validator
    Combines a dataset with a list of validation rules and can generate a
    complete SQL query to execute the validations.  The generated SQL
    returns only the rows that pass all validations.  A ``run`` method is
    provided as a convenience for executing the query against a provided
    connection object.

Mixins
-------
Simple mixin classes illustrate how additional behaviour can be mixed into
rule classes.  For example, ``ExplainableMixin`` adds an ``explain`` method
that returns a human‑readable description of a rule.

Notes
-----
This code depends on the ``sqlglot`` package.  Install it via
``pip install sqlglot`` in your own environment to use these classes.

"""

from __future__ import annotations

from typing import Iterable, List, Optional, Sequence, Tuple

try:
    # sqlglot is required for expression construction and SQL generation
    from sqlglot import exp
except ImportError as e:  # pragma: no cover
    raise ImportError(
        "sqlglot is required for dataset_validator. Install with 'pip install sqlglot'."
    ) from e


class Dataset:
    """Represents a dataset (table or query) in a given SQL dialect.

    Parameters
    ----------
    table_name : str
        The fully qualified table name (e.g. schema.table) or alias.
    dialect : str
        The target SQL dialect (e.g. "snowflake", "databricks").  Used when
        generating SQL from sqlglot expressions.

    Attributes
    ----------
    table_name : str
        The name of the table.
    dialect : str
        SQL dialect for generation.

    """

    def __init__(self, table_name: str, dialect: str = "snowflake") -> None:
        self.table_name = table_name
        self.dialect = dialect

    def table_expr(self) -> exp.Expression:
        """Return a sqlglot Table expression for this dataset."""
        return exp.table_(self.table_name)

    def column_expr(self, column: str) -> exp.Column:
        """Return a sqlglot Column expression scoped to this dataset's table.

        Parameters
        ----------
        column : str
            Column name without table prefix.

        Returns
        -------
        sqlglot.expressions.Column
            A column expression with this dataset's table as the qualifier.
        """
        return exp.column(column, table=self.table_name)


class ValidationRule:
    """Abstract base class for validation rules.

    Subclasses must implement ``expression`` which builds a sqlglot
    expression representing the rule for a given dataset.
    """

    def expression(self, dataset: Dataset) -> exp.Expression:
        """Return a sqlglot expression for this rule on the given dataset.

        Must be implemented by subclasses.

        Parameters
        ----------
        dataset : Dataset
            The dataset on which to apply the rule.

        Returns
        -------
        sqlglot.expressions.Expression
            A boolean expression representing this rule.
        """
        raise NotImplementedError

    def explain(self) -> str:
        """Return a human‑readable description of the rule.

        Subclasses can override this.  The default is the class name.
        """
        return self.__class__.__name__


class ExplainableMixin:
    """Mixin that adds an ``explain`` method to validation rules.

    When used with a ValidationRule subclass, this mixin allows the rule
    to provide a description of itself, potentially including parameters.
    """

    def explain(self) -> str:  # type: ignore[override]
        params = [f"{k}={v!r}" for k, v in self.__dict__.items() if not k.startswith("_")]
        return f"{self.__class__.__name__}({', '.join(params)})"


class NotNullRule(ExplainableMixin, ValidationRule):
    """Validation rule to ensure a column contains no NULL values."""

    def __init__(self, column: str) -> None:
        self.column = column

    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.column)
        return exp.IsNot(this=col, expression=exp.Null())


class UniqueValuesRule(ExplainableMixin, ValidationRule):
    """Validation rule to ensure a set of columns has unique combinations.

    If ``columns`` contains a single column, uniqueness is enforced on that
    column; if multiple columns, uniqueness is enforced on their tuple.
    """

    def __init__(self, columns: Sequence[str]) -> None:
        self.columns = tuple(columns)

    def expression(self, dataset: Dataset) -> exp.Expression:
        # For uniqueness, compare count(*) vs count(distinct)
        table_expr = dataset.table_expr()
        cols = [dataset.column_expr(c) for c in self.columns]
        # Build count(*) and count(distinct tuple)
        total_count = exp.count(this=exp.Star())
        if len(cols) == 1:
            distinct_count = exp.count(this=cols[0], distinct=True)
        else:
            # For multiple columns, use Tuple to count distinct combinations
            distinct_tuple = exp.Tuple(expressions=cols)
            distinct_count = exp.count(this=distinct_tuple, distinct=True)
        return exp.EQ(this=total_count, expression=distinct_count)


class AllowedValuesRule(ExplainableMixin, ValidationRule):
    """Validation rule to ensure a column's values are in a given set."""

    def __init__(self, column: str, allowed_values: Sequence) -> None:
        self.column = column
        self.allowed_values = tuple(allowed_values)

    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.column)
        values = [exp.Literal.string(v) if isinstance(v, str) else exp.Literal.number(v) for v in self.allowed_values]
        return exp.In(this=col, expressions=values)


class RangeRule(ExplainableMixin, ValidationRule):
    """Validation rule to ensure a numeric column lies within a range.

    Parameters
    ----------
    column : str
        Column name.
    minimum : Optional[float]
        Minimum allowed value (inclusive).  Use ``None`` for no minimum.
    maximum : Optional[float]
        Maximum allowed value (inclusive).  Use ``None`` for no maximum.
    """

    def __init__(self, column: str, minimum: Optional[float] = None, maximum: Optional[float] = None) -> None:
        if minimum is None and maximum is None:
            raise ValueError("At least one of minimum or maximum must be provided")
        self.column = column
        self.minimum = minimum
        self.maximum = maximum

    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.column)
        exprs: List[exp.Expression] = []
        if self.minimum is not None:
            exprs.append(exp.GTE(this=col, expression=exp.Literal.number(self.minimum)))
        if self.maximum is not None:
            exprs.append(exp.LTE(this=col, expression=exp.Literal.number(self.maximum)))
        # Combine with AND
        return exp.and_(*exprs) if len(exprs) > 1 else exprs[0]


class Validator:
    """Combines a dataset with a set of validation rules and generates SQL.

    The ``generate_sql`` method returns a single SELECT statement that
    filters the dataset by all validation conditions.  Only rows satisfying
    every rule will be returned.  The ``run`` method is a convenience to
    execute the validation against a provided connection.
    """

    def __init__(self, dataset: Dataset, rules: Iterable[ValidationRule]) -> None:
        self.dataset = dataset
        self.rules = list(rules)

    def combined_expression(self) -> exp.Expression:
        """Combine all rule expressions using logical AND."""
        exprs = [rule.expression(self.dataset) for rule in self.rules]
        if not exprs:
            # No rules means no filter (always true)
            return exp.true()
        return exp.and_(*exprs)

    def generate_sql(self, select_all: bool = True) -> str:
        """Generate a SQL query applying all validations.

        Parameters
        ----------
        select_all : bool, optional
            If True (default), ``SELECT *`` will be used.  If False,
            only the columns used in the rules will be selected.

        Returns
        -------
        str
            A SQL SELECT statement that can be executed on the target engine.
        """
        table_expr = self.dataset.table_expr()
        condition = self.combined_expression()
        query = exp.select("*") if select_all else exp.select(*{
            # Unique set of column names referenced in rules
            dataset.column_expr(col) for rule in self.rules if isinstance(rule, (NotNullRule, AllowedValuesRule, RangeRule)) for col in [getattr(rule, 'column', None)] if col
        })
        query = query.from_(table_expr).where(condition)
        return query.sql(dialect=self.dataset.dialect)

    def run(self, connection) -> Iterable[Tuple]:
        """Execute the generated SQL against a connection.

        This method requires a connection object that has an ``execute``
        method returning an iterable of rows.  The connection can be a
        Snowflake connector, Spark session, or any object implementing
        ``execute(sql)``.

        Parameters
        ----------
        connection : object
            Database connection with an ``execute`` method.

        Returns
        -------
        Iterable[Tuple]
            Result rows passing all validations.
        """
        sql = self.generate_sql()
        cursor = connection.execute(sql)
        try:
            return cursor.fetchall()
        except AttributeError:
            # Some connectors (e.g. Spark) return list of rows directly
            return cursor