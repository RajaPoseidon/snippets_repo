/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.CallFunction;
import com.databricks.labs.morpheus.intermediate.CurrentTimestamp;
import com.databricks.labs.morpheus.intermediate.DateFormatClass;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Literal;
import com.databricks.labs.morpheus.intermediate.Literal$;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.statistics.TranspileStatistics;
import com.databricks.labs.morpheus.transform.Phase;
import com.databricks.labs.morpheus.transform.Result;
import com.databricks.labs.morpheus.transform.Transformation;
import com.databricks.labs.morpheus.transform.TransformationConstructors;
import com.databricks.labs.morpheus.transform.TranspilerConfig;
import com.databricks.labs.morpheus.transform.TranspilerState;
import com.databricks.labs.morpheus.transform.WorkflowStage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Some;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.java8.JFunction0$mcI$sp;

@ScalaSignature(bytes="\u0006\u0001\u00054AAB\u0004\u0001-!)A\u0005\u0001C\u0001K!1\u0001\u0006\u0001Q\u0001\n%BQ!\u0010\u0001\u0005ByBQ\u0001\u0012\u0001\u0005\n\u0015CQa\u0014\u0001\u0005\nA\u0013qcQ8om\u0016\u0014HO\u0012:bGRLwN\\1m'\u0016\u001cwN\u001c3\u000b\u0005!I\u0011!C:o_^4G.Y6f\u0015\tQ1\"A\u0003sk2,7O\u0003\u0002\r\u001b\u0005IAO]1og\u001a|'/\u001c\u0006\u0003\u001d=\t\u0001\"\\8sa\",Wo\u001d\u0006\u0003!E\tA\u0001\\1cg*\u0011!cE\u0001\u000bI\u0006$\u0018M\u0019:jG.\u001c(\"\u0001\u000b\u0002\u0007\r|Wn\u0001\u0001\u0014\u0007\u00019\u0002\u0005E\u0002\u00197ui\u0011!\u0007\u0006\u000355\tA\"\u001b8uKJlW\rZ5bi\u0016L!\u0001H\r\u0003\tI+H.\u001a\t\u00031yI!aH\r\u0003\u00171{w-[2bYBc\u0017M\u001c\t\u0003C\tj\u0011aC\u0005\u0003G-\u0011!\u0004\u0016:b]N4wN]7bi&|gnQ8ogR\u0014Xo\u0019;peN\fa\u0001P5oSRtD#\u0001\u0014\u0011\u0005\u001d\u0002Q\"A\u0004\u0002\u0017QLW.Z'baBLgn\u001a\t\u0005UM2$H\u0004\u0002,cA\u0011AfL\u0007\u0002[)\u0011a&F\u0001\u0007yI|w\u000e\u001e \u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0002\rA\u0013X\rZ3g\u0013\t!TGA\u0002NCBT!AM\u0018\u0011\u0005]BT\"A\u0018\n\u0005ez#aA%oiB\u0011!fO\u0005\u0003yU\u0012aa\u0015;sS:<\u0017!B1qa2LHCA C!\r\t\u0003)H\u0005\u0003\u0003.\u0011a\u0002\u0016:b]N4wN]7bi&|g\u000eC\u0003D\u0007\u0001\u0007Q$\u0001\u0003qY\u0006t\u0017aD4fi&sG/Z4feZ\u000bG.^3\u0015\u0005\u0019K\u0005cA\u001cHm%\u0011\u0001j\f\u0002\u0007\u001fB$\u0018n\u001c8\t\u000b)#\u0001\u0019A&\u0002\u000f1LG/\u001a:bYB\u0019qg\u0012'\u0011\u0005ai\u0015B\u0001(\u001a\u0005\u001da\u0015\u000e^3sC2\f\u0001\u0004[1oI2,7\u000b]3dS\u0006dGk\u0015$v]\u000e$\u0018n\u001c8t)\r\tFK\u0016\t\u00031IK!aU\r\u0003\u0015\u0015C\bO]3tg&|g\u000eC\u0003V\u000b\u0001\u0007!(\u0001\u0007gk:\u001cG/[8o\u001d\u0006lW\rC\u0003X\u000b\u0001\u0007\u0001,A\u0005be\u001e,X.\u001a8ugB\u0019\u0011LX)\u000f\u0005icfB\u0001\u0017\\\u0013\u0005\u0001\u0014BA/0\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u00181\u0003\u0007M+\u0017O\u0003\u0002^_\u0001")
public class ConvertFractionalSecond
extends Rule<LogicalPlan>
implements TransformationConstructors {
    private final Map<Object, String> timeMapping;

    @Override
    public <A> Transformation<A> ok(A a) {
        return TransformationConstructors.ok$(this, a);
    }

    @Override
    public Transformation<Nothing$> ko(WorkflowStage stage, MorpheusError err) {
        return TransformationConstructors.ko$(this, stage, err);
    }

    @Override
    public <X> Transformation<X> lift(Result<X> res) {
        return TransformationConstructors.lift$(this, res);
    }

    @Override
    @JsonIgnore
    public Transformation<TranspilerConfig> getConfig() {
        return TransformationConstructors.getConfig$(this);
    }

    @Override
    @JsonIgnore
    public Transformation<Phase> getCurrentPhase() {
        return TransformationConstructors.getCurrentPhase$(this);
    }

    @Override
    public <X> Transformation<X> getFromPhase(PartialFunction<Phase, X> extract) {
        return TransformationConstructors.getFromPhase$(this, extract);
    }

    @Override
    public Transformation<BoxedUnit> setPhase(Phase newPhase) {
        return TransformationConstructors.setPhase$(this, newPhase);
    }

    @Override
    public Transformation<BoxedUnit> updatePhase(PartialFunction<Phase, Phase> f) {
        return TransformationConstructors.updatePhase$(this, f);
    }

    @Override
    public Transformation<BoxedUnit> updateStats(Function1<TranspileStatistics, TranspileStatistics> f) {
        return TransformationConstructors.updateStats$(this, f);
    }

    @Override
    public <A> Transformation<A> withState(Function1<TranspilerState, Transformation<A>> doWithState) {
        return TransformationConstructors.withState$(this, doWithState);
    }

    @Override
    @JsonIgnore
    public Transformation<TranspilerState> getState() {
        return TransformationConstructors.getState$(this);
    }

    @Override
    public <Out> Transformation<Out> withGenCtx(Function1<GeneratorContext, Transformation<Out>> transfoUsingCtx) {
        return TransformationConstructors.withGenCtx$(this, transfoUsingCtx);
    }

    @Override
    public Transformation<String> print(String text) {
        return TransformationConstructors.print$(this, text);
    }

    @Override
    public Transformation<LogicalPlan> apply(LogicalPlan plan) {
        return plan.transformAllExpressions((PartialFunction<Expression, Transformation<Expression>>)((Object)new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ ConvertFractionalSecond $outer;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                boolean bl = false;
                CallFunction callFunction = null;
                A1 A1 = x1;
                if (A1 instanceof CallFunction) {
                    bl = true;
                    callFunction = (CallFunction)A1;
                    String string = callFunction.function_name();
                    Seq<Expression> right = callFunction.arguments();
                    if ("CURRENT_TIME".equals(string)) {
                        return (B1)this.$outer.ok(this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$ConvertFractionalSecond$$handleSpecialTSFunctions("CURRENT_TIME", right));
                    }
                }
                if (bl) {
                    String string = callFunction.function_name();
                    Seq<Expression> right = callFunction.arguments();
                    if ("LOCALTIME".equals(string)) {
                        return (B1)this.$outer.ok(this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$ConvertFractionalSecond$$handleSpecialTSFunctions("LOCALTIME", right));
                    }
                }
                if (bl) {
                    String string = callFunction.function_name();
                    Seq<Expression> right = callFunction.arguments();
                    if ("CURRENT_TIMESTAMP".equals(string)) {
                        return (B1)this.$outer.ok(right.isEmpty() ? new CurrentTimestamp() : this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$ConvertFractionalSecond$$handleSpecialTSFunctions("CURRENT_TIMESTAMP", right));
                    }
                }
                if (bl) {
                    String string = callFunction.function_name();
                    Seq<Expression> right = callFunction.arguments();
                    if ("LOCALTIMESTAMP".equals(string)) {
                        return (B1)this.$outer.ok(right.isEmpty() ? new CurrentTimestamp() : this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$ConvertFractionalSecond$$handleSpecialTSFunctions("LOCALTIMESTAMP", right));
                    }
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                String string;
                String string2;
                String string3;
                boolean bl = false;
                CallFunction callFunction = null;
                Expression expression2 = x1;
                if (expression2 instanceof CallFunction) {
                    bl = true;
                    callFunction = (CallFunction)expression2;
                    String string4 = callFunction.function_name();
                    if ("CURRENT_TIME".equals(string4)) {
                        return true;
                    }
                }
                if (bl && "LOCALTIME".equals(string3 = callFunction.function_name())) {
                    return true;
                }
                if (bl && "CURRENT_TIMESTAMP".equals(string2 = callFunction.function_name())) {
                    return true;
                }
                return bl && "LOCALTIMESTAMP".equals(string = callFunction.function_name());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    private Option<Object> getIntegerValue(Option<Literal> literal) {
        Object value;
        Some some;
        Literal literal2;
        Option<Literal> option = literal;
        if (option instanceof Some && (literal2 = (Literal)(some = (Some)option).value()) != null && (value = literal2.value()) instanceof Integer) {
            int n = BoxesRunTime.unboxToInt(value);
            return new Some<Object>(BoxesRunTime.boxToInteger(n));
        }
        return None$.MODULE$;
    }

    public Expression com$databricks$labs$morpheus$transform$rules$snowflake$ConvertFractionalSecond$$handleSpecialTSFunctions(String functionName, Seq<Expression> arguments) {
        String timeFormat = (String)this.timeMapping.apply(this.getIntegerValue(arguments.headOption().flatMap((Function1<Expression, Option> & Serializable & scala.Serializable)x0$1 -> {
            Expression expression2 = x0$1;
            if (expression2 instanceof Literal) {
                Literal literal = (Literal)expression2;
                return new Some<Literal>(literal);
            }
            return None$.MODULE$;
        })).getOrElse((JFunction0$mcI$sp & scala.Serializable)() -> 0));
        String string = functionName;
        String formatString = ("CURRENT_TIME".equals(string) ? true : "LOCALTIME".equals(string)) ? timeFormat : new StringBuilder(15).append("yyyy-MM-dd ").append(timeFormat).append(".SSS").toString();
        return new DateFormatClass(new CurrentTimestamp(), Literal$.MODULE$.apply(formatString));
    }

    public ConvertFractionalSecond() {
        TransformationConstructors.$init$(this);
        this.timeMapping = (Map)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[]{Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(0)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(1)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(2)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(3)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(4)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(5)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(6)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(7)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(8)), "HH:mm:ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(9)), "HH:mm:ss")}));
    }
}

