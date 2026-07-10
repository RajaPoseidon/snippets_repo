/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.intermediate.snowflake;

import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.snowflake.InlineColumnExpression;
import scala.None$;
import scala.Option;
import scala.Serializable;
import scala.Some;
import scala.Tuple2;
import scala.runtime.AbstractFunction2;

public final class InlineColumnExpression$
extends AbstractFunction2<Id, Expression, InlineColumnExpression>
implements Serializable {
    public static InlineColumnExpression$ MODULE$;

    static {
        new InlineColumnExpression$();
    }

    @Override
    public final String toString() {
        return "InlineColumnExpression";
    }

    @Override
    public InlineColumnExpression apply(Id columnName, Expression value) {
        return new InlineColumnExpression(columnName, value);
    }

    public Option<Tuple2<Id, Expression>> unapply(InlineColumnExpression x$0) {
        if (x$0 == null) {
            return None$.MODULE$;
        }
        return new Some<Tuple2<Id, Expression>>(new Tuple2<Id, Expression>(x$0.columnName(), x$0.value()));
    }

    private Object readResolve() {
        return MODULE$;
    }

    private InlineColumnExpression$() {
        MODULE$ = this;
    }
}

