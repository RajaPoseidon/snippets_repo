/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.intermediate;

import com.databricks.labs.morpheus.intermediate.NullsFirst$;
import com.databricks.labs.morpheus.intermediate.UnspecifiedNullOrdering;
import scala.Product;
import scala.Serializable;
import scala.collection.Iterator;
import scala.runtime.ScalaRunTime$;

public final class SnowflakeUnspecifiedDescendingNullOrdering$
extends UnspecifiedNullOrdering
implements Product,
Serializable {
    public static SnowflakeUnspecifiedDescendingNullOrdering$ MODULE$;

    static {
        new SnowflakeUnspecifiedDescendingNullOrdering$();
    }

    @Override
    public String productPrefix() {
        return "SnowflakeUnspecifiedDescendingNullOrdering";
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
        return x$1 instanceof SnowflakeUnspecifiedDescendingNullOrdering$;
    }

    public int hashCode() {
        return 1413619352;
    }

    public String toString() {
        return "SnowflakeUnspecifiedDescendingNullOrdering";
    }

    private Object readResolve() {
        return MODULE$;
    }

    private SnowflakeUnspecifiedDescendingNullOrdering$() {
        super(NullsFirst$.MODULE$);
        MODULE$ = this;
        Product.$init$(this);
    }
}

