"""
build_ast.py

This script demonstrates how to construct a SQLGlot abstract syntax tree (AST)
from a nested JSON structure representing filter conditions.  The JSON may
contain nested groups with ``AND`` or ``OR`` combinators and rules with
operators such as ``=``, ``!=``, ``<>``, ``>``, ``<``, ``>=``, ``<=``,
``LIKE`` and ``IN``.

Running this script will build the AST and print both the structured
representation (via ``repr``) and the SQL it produces (via
``expr.sql(dialect="duckdb")``).  Note that you must have the
``sqlglot`` library installed in your environment for this script to
execute successfully.

Usage
-----
    python build_ast.py

"""

from __future__ import annotations

import json
from typing import Any, Dict

try:
    from sqlglot import exp
except ImportError:
    raise ImportError(
        "This script requires sqlglot. Install it via 'pip install sqlglot'."
    )


def build_expression(node: Dict[str, Any]) -> exp.Expression:
    """Recursively build a SQLGlot expression from a filter JSON node.

    Parameters
    ----------
    node : dict
        A dictionary representing either a group (with ``combinator`` and
        ``rules``) or a leaf rule (with ``field``, ``operator``, and
        optionally ``value``).

    Returns
    -------
    sqlglot.expressions.Expression
        The expression corresponding to the node.
    """
    # Leaf node: build comparison, like, in, etc.
    if "field" in node:
        field = node["field"]
        operator = node["operator"].upper()
        value = node.get("value")
        col_expr = exp.column(field)

        def lit(v: Any) -> exp.Expression:
            return exp.Literal.string(v) if isinstance(v, str) else exp.Literal.number(v)

        if operator in {"=", "=="}:
            return exp.EQ(this=col_expr, expression=lit(value))
        if operator in {"!=", "<>"}:
            return exp.NEQ(this=col_expr, expression=lit(value))
        if operator == ">":
            return exp.GT(this=col_expr, expression=lit(value))
        if operator == "<":
            return exp.LT(this=col_expr, expression=lit(value))
        if operator == ">=":
            return exp.GTE(this=col_expr, expression=lit(value))
        if operator == "<=":
            return exp.LTE(this=col_expr, expression=lit(value))
        if operator == "LIKE":
            return exp.Like(this=col_expr, expression=lit(value))
        if operator == "IN":
            if not isinstance(value, (list, tuple)):
                raise ValueError("IN operator expects a list of values")
            values = [lit(v) for v in value]
            return exp.In(this=col_expr, expressions=values)
        raise ValueError(f"Unsupported operator: {operator}")

    # Group node: combine child expressions with AND or OR
    combinator = node["combinator"].upper()
    children = [build_expression(child) for child in node["rules"]]
    if combinator == "AND":
        return exp.and_(*children)
    if combinator == "OR":
        return exp.or_(*children)
    raise ValueError(f"Unsupported combinator: {combinator}")


def main() -> None:
    # The nested JSON example used earlier
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
                                            {"field": "field10", "operator": ">=", "value": 20}
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                ]
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
                            {"field": "field14", "operator": "LIKE", "value": "bar%"}
                        ]
                    }
                ]
            }
        ]
    }

    # Build the AST from the filter JSON
    expr = build_expression(filter_json)
    # Print the AST using repr so that structure is visible
    print("AST representation:\n")
    print(repr(expr))

    # Print the generated SQL for a target dialect (e.g. duckdb)
    print("\nGenerated SQL (DuckDB dialect):\n")
    print(expr.sql(dialect="duckdb"))


if __name__ == "__main__":
    main()
