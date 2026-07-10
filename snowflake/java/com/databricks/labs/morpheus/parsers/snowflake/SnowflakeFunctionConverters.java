/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.parsers.snowflake;

import com.databricks.labs.morpheus.intermediate.CallFunction;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.parsers.ConversionStrategy;
import scala.Product;
import scala.Serializable;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005Et!B\r\u001b\u0011\u00039c!B\u0015\u001b\u0011\u0003Q\u0003\"B\u0019\u0002\t\u0003\u0011d\u0001B\u001a\u0002\u0001RB\u0001bP\u0002\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0019\u000e\u0011\t\u0012)A\u0005\u0003\")\u0011g\u0001C\u0001\u001b\")\u0011k\u0001C!%\"9amAA\u0001\n\u00039\u0007bB5\u0004#\u0003%\tA\u001b\u0005\bk\u000e\t\t\u0011\"\u0011w\u0011\u001dq8!!A\u0005\u0002}D\u0011\"a\u0002\u0004\u0003\u0003%\t!!\u0003\t\u0013\u0005U1!!A\u0005B\u0005]\u0001\"CA\u0013\u0007\u0005\u0005I\u0011AA\u0014\u0011%\t\tdAA\u0001\n\u0003\n\u0019\u0004C\u0005\u00026\r\t\t\u0011\"\u0011\u00028!I\u0011\u0011H\u0002\u0002\u0002\u0013\u0005\u00131H\u0004\n\u0003\u007f\t\u0011\u0011!E\u0001\u0003\u00032\u0001bM\u0001\u0002\u0002#\u0005\u00111\t\u0005\u0007cM!\t!!\u0015\t\u0013\u0005U2#!A\u0005F\u0005]\u0002\"CA*'\u0005\u0005I\u0011QA+\u0011%\tIfEA\u0001\n\u0003\u000bY\u0006C\u0005\u0002hM\t\t\u0011\"\u0003\u0002j\u0005Y2K\\8xM2\f7.\u001a$v]\u000e$\u0018n\u001c8D_:4XM\u001d;feNT!a\u0007\u000f\u0002\u0013Mtwn\u001e4mC.,'BA\u000f\u001f\u0003\u001d\u0001\u0018M]:feNT!a\b\u0011\u0002\u00115|'\u000f\u001d5fkNT!!\t\u0012\u0002\t1\f'm\u001d\u0006\u0003G\u0011\n!\u0002Z1uC\n\u0014\u0018nY6t\u0015\u0005)\u0013aA2p[\u000e\u0001\u0001C\u0001\u0015\u0002\u001b\u0005Q\"aG*o_^4G.Y6f\rVt7\r^5p]\u000e{gN^3si\u0016\u00148o\u0005\u0002\u0002WA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u0014\u0003\u0013MKhn\u001c8z[>37#B\u0002,keb\u0004C\u0001\u001c8\u001b\u0005a\u0012B\u0001\u001d\u001d\u0005I\u0019uN\u001c<feNLwN\\*ue\u0006$XmZ=\u0011\u00051R\u0014BA\u001e.\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001L\u001f\n\u0005yj#\u0001D*fe&\fG.\u001b>bE2,\u0017!D2b]>t\u0017nY1m\u001d\u0006lW-F\u0001B!\t\u0011\u0015J\u0004\u0002D\u000fB\u0011A)L\u0007\u0002\u000b*\u0011aIJ\u0001\u0007yI|w\u000e\u001e \n\u0005!k\u0013A\u0002)sK\u0012,g-\u0003\u0002K\u0017\n11\u000b\u001e:j]\u001eT!\u0001S\u0017\u0002\u001d\r\fgn\u001c8jG\u0006dg*Y7fAQ\u0011a\n\u0015\t\u0003\u001f\u000ei\u0011!\u0001\u0005\u0006\u007f\u0019\u0001\r!Q\u0001\bG>tg/\u001a:u)\r\u0019\u0016l\u0017\t\u0003)^k\u0011!\u0016\u0006\u0003-z\tA\"\u001b8uKJlW\rZ5bi\u0016L!\u0001W+\u0003\u0015\u0015C\bO]3tg&|g\u000eC\u0003[\u000f\u0001\u0007\u0011)\u0001\u0004je:\u000bW.\u001a\u0005\u00069\u001e\u0001\r!X\u0001\u0005CJ<7\u000fE\u0002_GNs!aX1\u000f\u0005\u0011\u0003\u0017\"\u0001\u0018\n\u0005\tl\u0013a\u00029bG.\fw-Z\u0005\u0003I\u0016\u00141aU3r\u0015\t\u0011W&\u0001\u0003d_BLHC\u0001(i\u0011\u001dy\u0004\u0002%AA\u0002\u0005\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001lU\t\tEnK\u0001n!\tq7/D\u0001p\u0015\t\u0001\u0018/A\u0005v]\u000eDWmY6fI*\u0011!/L\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001;p\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003]\u0004\"\u0001_?\u000e\u0003eT!A_>\u0002\t1\fgn\u001a\u0006\u0002y\u0006!!.\u0019<b\u0013\tQ\u00150\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0002A\u0019A&a\u0001\n\u0007\u0005\u0015QFA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\f\u0005E\u0001c\u0001\u0017\u0002\u000e%\u0019\u0011qB\u0017\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002\u00141\t\t\u00111\u0001\u0002\u0002\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0007\u0011\r\u0005m\u0011\u0011EA\u0006\u001b\t\tiBC\u0002\u0002 5\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019#!\b\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003S\ty\u0003E\u0002-\u0003WI1!!\f.\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\u0005\u000f\u0003\u0003\u0005\r!a\u0003\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0001\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a^\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005%\u0012Q\b\u0005\n\u0003'\t\u0012\u0011!a\u0001\u0003\u0017\t\u0011bU=o_:LXn\u00144\u0011\u0005=\u001b2\u0003B\n\u0002Fq\u0002b!a\u0012\u0002N\u0005sUBAA%\u0015\r\tY%L\u0001\beVtG/[7f\u0013\u0011\ty%!\u0013\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0006\u0002\u0002B\u0005)\u0011\r\u001d9msR\u0019a*a\u0016\t\u000b}2\u0002\u0019A!\u0002\u000fUt\u0017\r\u001d9msR!\u0011QLA2!\u0011a\u0013qL!\n\u0007\u0005\u0005TF\u0001\u0004PaRLwN\u001c\u0005\t\u0003K:\u0012\u0011!a\u0001\u001d\u0006\u0019\u0001\u0010\n\u0019\u0002\u0017I,\u0017\r\u001a*fg>dg/\u001a\u000b\u0003\u0003W\u00022\u0001_A7\u0013\r\ty'\u001f\u0002\u0007\u001f\nTWm\u0019;")
public final class SnowflakeFunctionConverters {

    public static class SynonymOf
    implements ConversionStrategy,
    Product,
    Serializable {
        private final String canonicalName;

        public String canonicalName() {
            return this.canonicalName;
        }

        @Override
        public Expression convert(String irName, Seq<Expression> args) {
            return new CallFunction(this.canonicalName(), args);
        }

        public SynonymOf copy(String canonicalName) {
            return new SynonymOf(canonicalName);
        }

        public String copy$default$1() {
            return this.canonicalName();
        }

        @Override
        public String productPrefix() {
            return "SynonymOf";
        }

        @Override
        public int productArity() {
            return 1;
        }

        @Override
        public Object productElement(int x$1) {
            int n = x$1;
            switch (n) {
                case 0: {
                    return this.canonicalName();
                }
            }
            throw new IndexOutOfBoundsException(Integer.toString(x$1));
        }

        @Override
        public Iterator<Object> productIterator() {
            return ScalaRunTime$.MODULE$.typedProductIterator(this);
        }

        @Override
        public boolean canEqual(Object x$1) {
            return x$1 instanceof SynonymOf;
        }

        public int hashCode() {
            return ScalaRunTime$.MODULE$._hashCode(this);
        }

        public String toString() {
            return ScalaRunTime$.MODULE$._toString(this);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean equals(Object x$1) {
            if (this == x$1) return true;
            Object object = x$1;
            if (!(object instanceof SynonymOf)) return false;
            boolean bl = true;
            if (!bl) return false;
            SynonymOf synonymOf = (SynonymOf)x$1;
            String string = this.canonicalName();
            String string2 = synonymOf.canonicalName();
            if (string == null) {
                if (string2 != null) {
                    return false;
                }
            } else if (!string.equals(string2)) return false;
            if (!synonymOf.canEqual(this)) return false;
            return true;
        }

        public SynonymOf(String canonicalName) {
            this.canonicalName = canonicalName;
            Product.$init$(this);
        }
    }
}

