"""
class_based_filter.py

This module demonstrates an alternative filter builder design inspired
by the validation framework.  Instead of a single ``SimpleRule`` class
handling all operators via conditional logic, each comparison operator
is encapsulated in its own class.  This approach mirrors how
validation rules are modelled in the ``advanced_filter_validator``
module: each rule has a clear, single responsibility and can be
extended or mixed in with additional behaviour.

Classes
-------
BaseFilterRule
    Abstract base class for all filter rules.  Defines the common
    interface and stores the field name.

SingleValueMixin
    Mixin providing a helper to convert a single Python value to a
    ``sqlglot`` literal.  Used by rules that operate on one value
    (e.g., ``EqualsFilter``).

Comparison filters
    ``EqualsFilter``, ``NotEqualsFilter``, ``GreaterThanFilter``,
    ``LessThanFilter``, ``GreaterOrEqualFilter``, ``LessOrEqualFilter``,
    ``LikeFilter``.

ListFilterMixin
    Mixin for filters that operate on a list of values.  Provides
    conversion of Python lists to lists of ``sqlglot`` literals.

InFilter
    A filter rule enforcing membership in a list of values.

LogicalGroup
    Represents a group of filter rules combined with ``AND`` or ``OR``.

FilterParser
    Parses a nested JSON object into a tree of filter rule objects.
    Maps operator strings to their corresponding filter classes.

Example
-------
Run this module as a script to see a demonstration of parsing a
complex nested filter JSON (the same structure used earlier), building
the ``sqlglot`` AST, and printing the resulting SQL.

Note
----
This module depends on ``sqlglot``.  Install with ``pip install sqlglot``.

"""

from __future__ import annotations

from typing import Any, Dict, Iterable, List, Optional, Sequence, Type

try:
    from sqlglot import exp  # type: ignore
except ImportError as e:  # pragma: no cover
    raise ImportError(
        "class_based_filter requires sqlglot. Install with 'pip install sqlglot'."
    ) from e

from advanced_filter_validator import Dataset  # Reuse Dataset from previous module


class BaseFilterRule:
    """Abstract base class for all filter rules."""

    def __init__(self, field: str) -> None:
        self.field = field

    def expression(self, dataset: Dataset) -> exp.Expression:
        """Return the ``sqlglot`` expression for this rule."""
        raise NotImplementedError

    def explain(self) -> str:
        return f"{self.__class__.__name__}(field={self.field!r})"


class SingleValueMixin:
    """Mixin for rules that operate on a single value."""

    def __init__(self, field: str, value: Any) -> None:
        super().__init__(field)  # type: ignore[misc]
        self.value = value

    def _literal(self, v: Any) -> exp.Expression:
        return exp.Literal.string(v) if isinstance(v, str) else exp.Literal.number(v)


class ListValueMixin:
    """Mixin for rules that operate on a list of values."""

    def __init__(self, field: str, values: Sequence[Any]) -> None:
        super().__init__(field)  # type: ignore[misc]
        self.values = tuple(values)

    def _literal_list(self) -> List[exp.Expression]:
        return [exp.Literal.string(v) if isinstance(v, str) else exp.Literal.number(v) for v in self.values]


class EqualsFilter(SingleValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.EQ(this=col, expression=self._literal(self.value))


class NotEqualsFilter(SingleValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.NEQ(this=col, expression=self._literal(self.value))


class GreaterThanFilter(SingleValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.GT(this=col, expression=self._literal(self.value))


class LessThanFilter(SingleValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.LT(this=col, expression=self._literal(self.value))


class GreaterOrEqualFilter(SingleValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.GTE(this=col, expression=self._literal(self.value))


class LessOrEqualFilter(SingleValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.LTE(this=col, expression=self._literal(self.value))


class LikeFilter(SingleValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.Like(this=col, expression=self._literal(self.value))


class InFilter(ListValueMixin, BaseFilterRule):
    def expression(self, dataset: Dataset) -> exp.Expression:
        col = dataset.column_expr(self.field)
        return exp.In(this=col, expressions=self._literal_list())

    def explain(self) -> str:
        return f"InFilter(field={self.field!r}, values={self.values!r})"


class LogicalGroup(BaseFilterRule):
    """Group of filter rules combined by AND or OR."""

    def __init__(self, combinator: str, children: Iterable[BaseFilterRule]) -> None:
        super().__init__(field="")
        combinator = combinator.upper()
        if combinator not in {"AND", "OR"}:
            raise ValueError("combinator must be 'AND' or 'OR'")
        self.combinator = combinator
        self.children: List[BaseFilterRule] = list(children)

    def expression(self, dataset: Dataset) -> exp.Expression:
        child_exprs = [child.expression(dataset) for child in self.children]
        if not child_exprs:
            return exp.true()
        return exp.and_(*child_exprs) if self.combinator == "AND" else exp.or_(*child_exprs)

    def explain(self) -> str:
        return f"{self.combinator}Group({', '.join(child.explain() for child in self.children)})"


class FilterParser:
    """Parse nested JSON into a tree of filter rule objects."""

    # Mapping from operator string to rule class
    OPERATOR_MAP: Dict[str, Type[BaseFilterRule]] = {
        "=": EqualsFilter,
        "==": EqualsFilter,
        "!=": NotEqualsFilter,
        "<>": NotEqualsFilter,
        ">": GreaterThanFilter,
        "<": LessThanFilter,
        ">=": GreaterOrEqualFilter,
        "<=": LessOrEqualFilter,
        "LIKE": LikeFilter,
        "IN": InFilter,
    }

    @classmethod
    def parse(cls, node: Dict[str, Any]) -> BaseFilterRule:
        if "field" in node:
            op = node["operator"].upper()
            value = node.get("value")
            rule_cls = cls.OPERATOR_MAP.get(op)
            if rule_cls is None:
                raise ValueError(f"Unsupported operator: {op}")
            # For IN we expect a sequence of values
            if op == "IN" and not isinstance(value, Iterable):
                raise ValueError("IN operator requires a list of values")
            return rule_cls(node["field"], value)  # type: ignore[misc]
        # Otherwise it's a group
        combinator = node.get("combinator")
        if not combinator:
            raise ValueError("Group node missing 'combinator'")
        children = [cls.parse(child) for child in node.get("rules", [])]
        return LogicalGroup(combinator, children)


def _demo() -> None:  # pragma: no cover
    """Demonstration of the class‑based filter builder."""
    # Example nested filter JSON (same as earlier)
    filter_json = {
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
    # Parse into a filter tree
    root_rule = FilterParser.parse(filter_json)
    dataset = Dataset("demo.schema.table", dialect="duckdb")
    # Build AST and SQL
    ast = root_rule.expression(dataset)
    print("Explanation:")
    print(root_rule.explain())
    print("\nAST:")
    print(repr(ast))
    print("\nSQL (DuckDB):")
    print(ast.sql(dialect=dataset.dialect))


if __name__ == "__main__":  # pragma: no cover
    _demo()
