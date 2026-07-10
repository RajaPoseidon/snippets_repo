/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.intermediate.snowflake;

import com.databricks.labs.morpheus.intermediate.Attribute;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.snowflake.InlineColumnExpression$;
import scala.Function1;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(bytes="\u0006\u0001\u0005\u001dd\u0001\u0002\r\u001a\u0001\u001aB\u0001\u0002\u000e\u0001\u0003\u0016\u0004%\t!\u000e\u0005\ts\u0001\u0011\t\u0012)A\u0005m!A!\b\u0001BK\u0002\u0013\u00051\b\u0003\u0005@\u0001\tE\t\u0015!\u0003=\u0011\u0015\u0001\u0005\u0001\"\u0001B\u0011\u00151\u0005\u0001\"\u0011H\u0011\u00159\u0006\u0001\"\u0011Y\u0011\u001dQ\u0006!!A\u0005\u0002mCqA\u0018\u0001\u0012\u0002\u0013\u0005q\fC\u0004k\u0001E\u0005I\u0011A6\t\u000f5\u0004\u0011\u0011!C!]\"9q\u000fAA\u0001\n\u0003A\bb\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u000f\u0001\u0011\u0011!C!\u0003\u0013A\u0011\"a\u0006\u0001\u0003\u0003%\t!!\u0007\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015r!CA\u00153\u0005\u0005\t\u0012AA\u0016\r!A\u0012$!A\t\u0002\u00055\u0002B\u0002!\u0013\t\u0003\tY\u0004C\u0005\u0002>I\t\t\u0011\"\u0012\u0002@!I\u0011\u0011\t\n\u0002\u0002\u0013\u0005\u00151\t\u0005\n\u0003\u0013\u0012\u0012\u0011!CA\u0003\u0017B\u0011\"!\u0018\u0013\u0003\u0003%I!a\u0018\u0003-%sG.\u001b8f\u0007>dW/\u001c8FqB\u0014Xm]:j_:T!AG\u000e\u0002\u0013Mtwn\u001e4mC.,'B\u0001\u000f\u001e\u00031Ig\u000e^3s[\u0016$\u0017.\u0019;f\u0015\tqr$\u0001\u0005n_J\u0004\b.Z;t\u0015\t\u0001\u0013%\u0001\u0003mC\n\u001c(B\u0001\u0012$\u0003)!\u0017\r^1ce&\u001c7n\u001d\u0006\u0002I\u0005\u00191m\\7\u0004\u0001M!\u0001aJ\u00162!\tA\u0013&D\u0001\u001c\u0013\tQ3DA\u0006M_\u001eL7-\u00197QY\u0006t\u0007C\u0001\u00170\u001b\u0005i#\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj#a\u0002)s_\u0012,8\r\u001e\t\u0003YIJ!aM\u0017\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\u0015\r|G.^7o\u001d\u0006lW-F\u00017!\tAs'\u0003\u000297\t\u0011\u0011\nZ\u0001\fG>dW/\u001c8OC6,\u0007%A\u0003wC2,X-F\u0001=!\tAS(\u0003\u0002?7\tQQ\t\u001f9sKN\u001c\u0018n\u001c8\u0002\rY\fG.^3!\u0003\u0019a\u0014N\\5u}Q\u0019!\tR#\u0011\u0005\r\u0003Q\"A\r\t\u000bQ*\u0001\u0019\u0001\u001c\t\u000bi*\u0001\u0019\u0001\u001f\u0002\r=,H\u000f];u+\u0005A\u0005cA%R):\u0011!j\u0014\b\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001b\u0016\na\u0001\u0010:p_Rt\u0014\"\u0001\u0018\n\u0005Ak\u0013a\u00029bG.\fw-Z\u0005\u0003%N\u00131aU3r\u0015\t\u0001V\u0006\u0005\u0002)+&\u0011ak\u0007\u0002\n\u0003R$(/\u001b2vi\u0016\f\u0001b\u00195jY\u0012\u0014XM\\\u000b\u00023B\u0019\u0011*U\u0014\u0002\t\r|\u0007/\u001f\u000b\u0004\u0005rk\u0006b\u0002\u001b\t!\u0003\u0005\rA\u000e\u0005\bu!\u0001\n\u00111\u0001=\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0019\u0016\u0003m\u0005\\\u0013A\u0019\t\u0003G\"l\u0011\u0001\u001a\u0006\u0003K\u001a\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u001dl\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011\u000e\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002Y*\u0012A(Y\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003=\u0004\"\u0001];\u000e\u0003ET!A]:\u0002\t1\fgn\u001a\u0006\u0002i\u0006!!.\u0019<b\u0013\t1\u0018O\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002sB\u0011AF_\u0005\u0003w6\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2A`A\u0002!\tas0C\u0002\u0002\u00025\u00121!\u00118z\u0011!\t)!DA\u0001\u0002\u0004I\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\fA)\u0011QBA\n}6\u0011\u0011q\u0002\u0006\u0004\u0003#i\u0013AC2pY2,7\r^5p]&!\u0011QCA\b\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005m\u0011\u0011\u0005\t\u0004Y\u0005u\u0011bAA\u0010[\t9!i\\8mK\u0006t\u0007\u0002CA\u0003\u001f\u0005\u0005\t\u0019\u0001@\u0002\r\u0015\fX/\u00197t)\u0011\tY\"a\n\t\u0011\u0005\u0015\u0001#!AA\u0002y\fa#\u00138mS:,7i\u001c7v[:,\u0005\u0010\u001d:fgNLwN\u001c\t\u0003\u0007J\u0019BAEA\u0018cA9\u0011\u0011GA\u001cmq\u0012UBAA\u001a\u0015\r\t)$L\u0001\beVtG/[7f\u0013\u0011\tI$a\r\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0006\u0002\u0002,\u0005AAo\\*ue&tw\rF\u0001p\u0003\u0015\t\u0007\u000f\u001d7z)\u0015\u0011\u0015QIA$\u0011\u0015!T\u00031\u00017\u0011\u0015QT\u00031\u0001=\u0003\u001d)h.\u00199qYf$B!!\u0014\u0002ZA)A&a\u0014\u0002T%\u0019\u0011\u0011K\u0017\u0003\r=\u0003H/[8o!\u0015a\u0013Q\u000b\u001c=\u0013\r\t9&\f\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005mc#!AA\u0002\t\u000b1\u0001\u001f\u00131\u0003-\u0011X-\u00193SKN|GN^3\u0015\u0005\u0005\u0005\u0004c\u00019\u0002d%\u0019\u0011QM9\u0003\r=\u0013'.Z2u\u0001")
public class InlineColumnExpression
extends LogicalPlan
implements Serializable {
    private final Id columnName;
    private final Expression value;

    public static Option<Tuple2<Id, Expression>> unapply(InlineColumnExpression inlineColumnExpression) {
        return InlineColumnExpression$.MODULE$.unapply(inlineColumnExpression);
    }

    public static Function1<Tuple2<Id, Expression>, InlineColumnExpression> tupled() {
        return InlineColumnExpression$.MODULE$.tupled();
    }

    public static Function1<Id, Function1<Expression, InlineColumnExpression>> curried() {
        return InlineColumnExpression$.MODULE$.curried();
    }

    public Id columnName() {
        return this.columnName;
    }

    public Expression value() {
        return this.value;
    }

    @Override
    public Seq<Attribute> output() {
        return Nil$.MODULE$;
    }

    @Override
    public Seq<LogicalPlan> children() {
        return Nil$.MODULE$;
    }

    public InlineColumnExpression copy(Id columnName, Expression value) {
        return new InlineColumnExpression(columnName, value);
    }

    public Id copy$default$1() {
        return this.columnName();
    }

    public Expression copy$default$2() {
        return this.value();
    }

    @Override
    public String productPrefix() {
        return "InlineColumnExpression";
    }

    @Override
    public int productArity() {
        return 2;
    }

    @Override
    public Object productElement(int x$1) {
        int n = x$1;
        switch (n) {
            case 0: {
                return this.columnName();
            }
            case 1: {
                return this.value();
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
        return x$1 instanceof InlineColumnExpression;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object x$1) {
        if (this == x$1) return true;
        Object object = x$1;
        if (!(object instanceof InlineColumnExpression)) return false;
        boolean bl = true;
        if (!bl) return false;
        InlineColumnExpression inlineColumnExpression = (InlineColumnExpression)x$1;
        Id id = this.columnName();
        Id id2 = inlineColumnExpression.columnName();
        if (id == null) {
            if (id2 != null) {
                return false;
            }
        } else if (!((Object)id).equals(id2)) return false;
        Expression expression2 = this.value();
        Expression expression3 = inlineColumnExpression.value();
        if (expression2 == null) {
            if (expression3 != null) {
                return false;
            }
        } else if (!expression2.equals(expression3)) return false;
        if (!inlineColumnExpression.canEqual(this)) return false;
        return true;
    }

    public InlineColumnExpression(Id columnName, Expression value) {
        this.columnName = columnName;
        this.value = value;
    }
}

