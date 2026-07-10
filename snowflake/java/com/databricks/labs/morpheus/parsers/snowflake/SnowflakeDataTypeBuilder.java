/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.parsers.snowflake;

import com.databricks.labs.morpheus.intermediate.ArrayType;
import com.databricks.labs.morpheus.intermediate.BinaryType$;
import com.databricks.labs.morpheus.intermediate.BooleanType$;
import com.databricks.labs.morpheus.intermediate.CharType;
import com.databricks.labs.morpheus.intermediate.DataType;
import com.databricks.labs.morpheus.intermediate.DateType$;
import com.databricks.labs.morpheus.intermediate.DecimalType;
import com.databricks.labs.morpheus.intermediate.DecimalType$;
import com.databricks.labs.morpheus.intermediate.DoubleType$;
import com.databricks.labs.morpheus.intermediate.JinjaAsDataType;
import com.databricks.labs.morpheus.intermediate.TimestampNTZType$;
import com.databricks.labs.morpheus.intermediate.TimestampType$;
import com.databricks.labs.morpheus.intermediate.TinyintType$;
import com.databricks.labs.morpheus.intermediate.UnparsedType;
import com.databricks.labs.morpheus.intermediate.UnresolvedType$;
import com.databricks.labs.morpheus.intermediate.VarcharType;
import com.databricks.labs.morpheus.intermediate.VariantType$;
import com.databricks.labs.morpheus.parsers.DataTypeBuilder;
import com.databricks.labs.morpheus.parsers.DataTypePurpose;
import com.databricks.labs.morpheus.parsers.Definition$;
import com.databricks.labs.morpheus.parsers.usql.USqlParser;
import java.io.Serializable;
import org.antlr.v4.runtime.tree.TerminalNode;
import scala.Function1;
import scala.Option$;
import scala.Predef$;
import scala.Some;
import scala.collection.JavaConverters$;
import scala.collection.TraversableLike;
import scala.collection.immutable.StringOps;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0$mcI$sp;

@ScalaSignature(bytes="\u0006\u0001y3A!\u0003\u0006\u0001/!)!\u0005\u0001C\u0001G!)a\u0005\u0001C\u0005O!9a\u0006\u0001b\u0001\n\u0013y\u0003BB\u001a\u0001A\u0003%\u0001\u0007C\u00045\u0001\t\u0007I\u0011B\u0018\t\rU\u0002\u0001\u0015!\u00031\u0011\u00151\u0004\u0001\"\u00018\u0011\u0015Q\u0006\u0001\"\u0003\\\u0005a\u0019fn\\<gY\u0006\\W\rR1uCRK\b/\u001a\"vS2$WM\u001d\u0006\u0003\u00171\t\u0011b\u001d8po\u001ad\u0017m[3\u000b\u00055q\u0011a\u00029beN,'o\u001d\u0006\u0003\u001fA\t\u0001\"\\8sa\",Wo\u001d\u0006\u0003#I\tA\u0001\\1cg*\u00111\u0003F\u0001\u000bI\u0006$\u0018M\u0019:jG.\u001c(\"A\u000b\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0003?\u0001j\u0011\u0001D\u0005\u0003C1\u0011q\u0002R1uCRK\b/\u001a\"vS2$WM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0011\u0002\"!\n\u0001\u000e\u0003)\tQ\u0002Z3gCVdGOT;nE\u0016\u0014X#\u0001\u0015\u0011\u0005%bS\"\u0001\u0016\u000b\u0005-r\u0011\u0001D5oi\u0016\u0014X.\u001a3jCR,\u0017BA\u0017+\u0005-!UmY5nC2$\u0016\u0010]3\u0002+\rD\u0017M\u001d+za\u0016$UMZ1vYRdUM\\4uQV\t\u0001\u0007\u0005\u0002\u001ac%\u0011!G\u0007\u0002\u0004\u0013:$\u0018AF2iCJ$\u0016\u0010]3EK\u001a\fW\u000f\u001c;MK:<G\u000f\u001b\u0011\u00021Y\f'o\u00195beRK\b/\u001a#fM\u0006,H\u000e\u001e'f]\u001e$\b.A\rwCJ\u001c\u0007.\u0019:UsB,G)\u001a4bk2$H*\u001a8hi\"\u0004\u0013!\u00022vS2$Gc\u0001\u001d<+B\u0011\u0011&O\u0005\u0003u)\u0012\u0001\u0002R1uCRK\b/\u001a\u0005\u0006y\u001d\u0001\r!P\u0001\u0004GRD\bC\u0001 S\u001d\tytJ\u0004\u0002A\u001b:\u0011\u0011\t\u0014\b\u0003\u0005.s!a\u0011&\u000f\u0005\u0011KeBA#I\u001b\u00051%BA$\u0017\u0003\u0019a$o\\8u}%\tQ#\u0003\u0002\u0014)%\u0011\u0011CE\u0005\u0003\u001fAI!!\u0004\b\n\u00059c\u0011\u0001B;tc2L!\u0001U)\u0002\u0015U\u001b\u0016\u000f\u001c)beN,'O\u0003\u0002O\u0019%\u00111\u000b\u0016\u0002\u0010\t\u0006$\u0018\rV=qK\u000e{g\u000e^3yi*\u0011\u0001+\u0015\u0005\u0006-\u001e\u0001\raV\u0001\t?B,(\u000f]8tKB\u0011q\u0004W\u0005\u000332\u0011q\u0002R1uCRK\b/\u001a)veB|7/Z\u0001\bI\u0016\u001c\u0017.\\1m)\tAC\fC\u0003^\u0011\u0001\u0007Q(A\u0001d\u0001")
public class SnowflakeDataTypeBuilder
implements DataTypeBuilder {
    private final int charTypeDefaultLength;
    private final int varcharTypeDefaultLength;

    private DecimalType defaultNumber() {
        return new DecimalType(new Some<Object>(BoxesRunTime.boxToInteger(38)), new Some<Object>(BoxesRunTime.boxToInteger(0)));
    }

    private int charTypeDefaultLength() {
        return this.charTypeDefaultLength;
    }

    private int varcharTypeDefaultLength() {
        return this.varcharTypeDefaultLength;
    }

    @Override
    public DataType build(USqlParser.DataTypeContext ctx, DataTypePurpose _purpose) {
        USqlParser.DataTypeContext dataTypeContext = ctx;
        if (dataTypeContext == null) {
            return UnresolvedType$.MODULE$;
        }
        if (ctx.ARRAY() != null) {
            return new ArrayType(this.build(ctx.dataType(), Definition$.MODULE$));
        }
        if (ctx.OBJECT() != null) {
            return new UnparsedType("OBJECT");
        }
        if (ctx.jinjaTemplate() != null) {
            return new JinjaAsDataType(ctx.jinjaTemplate().getText());
        }
        String typeDef = ctx.id().getText().toUpperCase();
        String string = typeDef;
        if ("CHAR".equals(string) ? true : ("NCHAR".equals(string) ? true : "CHARACTER".equals(string))) {
            int length = BoxesRunTime.unboxToInt(Option$.MODULE$.apply(ctx.INT(0)).map((Function1<TerminalNode, Object> & Serializable & scala.Serializable)x$1 -> BoxesRunTime.boxToInteger(SnowflakeDataTypeBuilder.$anonfun$build$1(x$1))).getOrElse((JFunction0$mcI$sp & scala.Serializable)() -> this.charTypeDefaultLength()));
            return new CharType(length);
        }
        if ("CHAR_VARYING".equals(string) ? true : ("NCHAR_VARYING".equals(string) ? true : ("NVARCHAR2".equals(string) ? true : ("NVARCHAR".equals(string) ? true : ("STRING".equals(string) ? true : ("TEXT".equals(string) ? true : "VARCHAR".equals(string))))))) {
            int length = BoxesRunTime.unboxToInt(Option$.MODULE$.apply(ctx.INT(0)).map((Function1<TerminalNode, Object> & Serializable & scala.Serializable)x$2 -> BoxesRunTime.boxToInteger(SnowflakeDataTypeBuilder.$anonfun$build$3(x$2))).getOrElse((JFunction0$mcI$sp & scala.Serializable)() -> this.varcharTypeDefaultLength()));
            return new VarcharType(length);
        }
        if ("NUMBER".equals(string) ? true : ("NUMERIC".equals(string) ? true : "DECIMAL".equals(string))) {
            return this.decimal(ctx);
        }
        if ("TIMESTAMP".equals(string) ? true : ("TIMESTAMP_LTZ".equals(string) ? true : ("TIMESTAMP_TZ".equals(string) ? true : "TIMESTAMPTZ".equals(string)))) {
            return TimestampType$.MODULE$;
        }
        if ("DATETIME".equals(string) ? true : "TIMESTAMP_NTZ".equals(string)) {
            return TimestampNTZType$.MODULE$;
        }
        if ("BIGINT".equals(string)) {
            return this.defaultNumber();
        }
        if ("BINARY".equals(string)) {
            return BinaryType$.MODULE$;
        }
        if ("BOOLEAN".equals(string)) {
            return BooleanType$.MODULE$;
        }
        if ("BYTEINT".equals(string)) {
            return this.defaultNumber();
        }
        if ("DATE".equals(string)) {
            return DateType$.MODULE$;
        }
        if ("DOUBLE".equals(string)) {
            return DoubleType$.MODULE$;
        }
        if ("DOUBLE PRECISION".equals(string)) {
            return DoubleType$.MODULE$;
        }
        if ("FLOAT".equals(string)) {
            return DoubleType$.MODULE$;
        }
        if ("FLOAT4".equals(string)) {
            return DoubleType$.MODULE$;
        }
        if ("FLOAT8".equals(string)) {
            return DoubleType$.MODULE$;
        }
        if ("INT".equals(string)) {
            return this.defaultNumber();
        }
        if ("INTEGER".equals(string)) {
            return this.defaultNumber();
        }
        if ("REAL".equals(string)) {
            return DoubleType$.MODULE$;
        }
        if ("SMALLINT".equals(string)) {
            return this.defaultNumber();
        }
        if ("TIME".equals(string)) {
            return TimestampType$.MODULE$;
        }
        if ("TINYINT".equals(string)) {
            return TinyintType$.MODULE$;
        }
        if ("VARBINARY".equals(string)) {
            return BinaryType$.MODULE$;
        }
        if ("VARIANT".equals(string)) {
            return VariantType$.MODULE$;
        }
        return new UnparsedType(typeDef);
    }

    private DecimalType decimal(USqlParser.DataTypeContext c) {
        Buffer<TerminalNode> nums = JavaConverters$.MODULE$.asScalaBufferConverter(c.INT()).asScala();
        int precision = BoxesRunTime.unboxToInt(nums.headOption().map((Function1<TerminalNode, Object> & Serializable & scala.Serializable)x$3 -> BoxesRunTime.boxToInteger(SnowflakeDataTypeBuilder.$anonfun$decimal$1(x$3))).getOrElse((JFunction0$mcI$sp & scala.Serializable)() -> 38));
        int scale = BoxesRunTime.unboxToInt(((TraversableLike)nums.drop(1)).headOption().map((Function1<TerminalNode, Object> & Serializable & scala.Serializable)x$4 -> BoxesRunTime.boxToInteger(SnowflakeDataTypeBuilder.$anonfun$decimal$3(x$4))).getOrElse((JFunction0$mcI$sp & scala.Serializable)() -> 0));
        return DecimalType$.MODULE$.apply(precision, scale);
    }

    public static final /* synthetic */ int $anonfun$build$1(TerminalNode x$1) {
        return new StringOps(Predef$.MODULE$.augmentString(x$1.getText())).toInt();
    }

    public static final /* synthetic */ int $anonfun$build$3(TerminalNode x$2) {
        return new StringOps(Predef$.MODULE$.augmentString(x$2.getText())).toInt();
    }

    public static final /* synthetic */ int $anonfun$decimal$1(TerminalNode x$3) {
        return new StringOps(Predef$.MODULE$.augmentString(x$3.getText())).toInt();
    }

    public static final /* synthetic */ int $anonfun$decimal$3(TerminalNode x$4) {
        return new StringOps(Predef$.MODULE$.augmentString(x$4.getText())).toInt();
    }

    public SnowflakeDataTypeBuilder() {
        this.charTypeDefaultLength = 1;
        this.varcharTypeDefaultLength = 0x1000000;
    }
}

