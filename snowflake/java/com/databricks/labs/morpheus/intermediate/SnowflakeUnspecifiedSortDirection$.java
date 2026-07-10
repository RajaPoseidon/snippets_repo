/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.intermediate;

import com.databricks.labs.morpheus.intermediate.Ascending$;
import com.databricks.labs.morpheus.intermediate.UnspecifiedSortDirection;
import scala.Product;
import scala.Serializable;
import scala.collection.Iterator;
import scala.runtime.ScalaRunTime$;

public final class SnowflakeUnspecifiedSortDirection$
extends UnspecifiedSortDirection
implements Product,
Serializable {
    public static SnowflakeUnspecifiedSortDirection$ MODULE$;

    static {
        new SnowflakeUnspecifiedSortDirection$();
    }

    @Override
    public String productPrefix() {
        return "SnowflakeUnspecifiedSortDirection";
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
        return x$1 instanceof SnowflakeUnspecifiedSortDirection$;
    }

    public int hashCode() {
        return -1370603812;
    }

    public String toString() {
        return "SnowflakeUnspecifiedSortDirection";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private SnowflakeUnspecifiedSortDirection$() {
        super(Ascending$.MODULE$);
        MODULE$ = this;
        Product.$init$(this);
    }
}

