/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.intermediate;

import com.databricks.labs.morpheus.intermediate.NullsLast$;
import com.databricks.labs.morpheus.intermediate.UnspecifiedNullOrdering;
import scala.Product;
import scala.Serializable;
import scala.collection.Iterator;
import scala.runtime.ScalaRunTime$;

public final class SnowflakeUnspecifiedAscendingNullOrdering$
extends UnspecifiedNullOrdering
implements Product,
Serializable {
    public static SnowflakeUnspecifiedAscendingNullOrdering$ MODULE$;

    static {
        new SnowflakeUnspecifiedAscendingNullOrdering$();
    }

    @Override
    public String productPrefix() {
        return "SnowflakeUnspecifiedAscendingNullOrdering";
    }

    @Override
    public int productArity() {
        return 0;
    }

    @Override
    public Object productElement(int x$1) {
        int n = x$1;
        throw new IndexOutOfBoundsException(Integer.toString(x$1));
    }

    @Override
    public Iterator<Object> productIterator() {
        return ScalaRunTime$.MODULE$.typedProductIterator(this);
    }

    @Override
    public boolean canEqual(Object x$1) {
        return x$1 instanceof SnowflakeUnspecifiedAscendingNullOrdering$;
    }

    public int hashCode() {
        return 482703310;
    }

    public String toString() {
        return "SnowflakeUnspecifiedAscendingNullOrdering";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private SnowflakeUnspecifiedAscendingNullOrdering$() {
        super(NullsLast$.MODULE$);
        MODULE$ = this;
        Product.$init$(this);
    }
}

