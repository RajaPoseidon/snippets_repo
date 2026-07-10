/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.generators.sql.DataTypeGenerator$;
import com.databricks.labs.morpheus.intermediate.CallFunction;
import com.databricks.labs.morpheus.intermediate.Cast;
import com.databricks.labs.morpheus.intermediate.DataType;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.JsonToStructs;
import com.databricks.labs.morpheus.intermediate.Literal$;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.statistics.TranspileStatistics;
import com.databricks.labs.morpheus.transform.Phase;
import com.databricks.labs.morpheus.transform.Result;
import com.databricks.labs.morpheus.transform.SQLCodeBlock;
import com.databricks.labs.morpheus.transform.Transformation;
import com.databricks.labs.morpheus.transform.TransformationConstructors;
import com.databricks.labs.morpheus.transform.TranspilerConfig;
import com.databricks.labs.morpheus.transform.TranspilerState;
import com.databricks.labs.morpheus.transform.WorkflowStage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.None$;
import scala.PartialFunction;
import scala.Some;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.LambdaDeserialize;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u000112Aa\u0001\u0003\u0001'!)\u0011\u0005\u0001C\u0001E!)Q\u0005\u0001C!M\t92)Y:u!\u0006\u00148/\u001a&t_:$vN\u0012:p[*\u001bxN\u001c\u0006\u0003\u000b\u0019\t\u0011b\u001d8po\u001ad\u0017m[3\u000b\u0005\u001dA\u0011!\u0002:vY\u0016\u001c(BA\u0005\u000b\u0003%!(/\u00198tM>\u0014XN\u0003\u0002\f\u0019\u0005AQn\u001c:qQ\u0016,8O\u0003\u0002\u000e\u001d\u0005!A.\u00192t\u0015\ty\u0001#\u0001\u0006eCR\f'M]5dWNT\u0011!E\u0001\u0004G>l7\u0001A\n\u0004\u0001Qi\u0002cA\u000b\u001955\taC\u0003\u0002\u0018\u0015\u0005a\u0011N\u001c;fe6,G-[1uK&\u0011\u0011D\u0006\u0002\u0005%VdW\r\u0005\u0002\u00167%\u0011AD\u0006\u0002\f\u0019><\u0017nY1m!2\fg\u000e\u0005\u0002\u001f?5\t\u0001\"\u0003\u0002!\u0011\tQBK]1og\u001a|'/\\1uS>t7i\u001c8tiJ,8\r^8sg\u00061A(\u001b8jiz\"\u0012a\t\t\u0003I\u0001i\u0011\u0001B\u0001\u0006CB\u0004H.\u001f\u000b\u0003O)\u00022A\b\u0015\u001b\u0013\tI\u0003B\u0001\bUe\u0006t7OZ8s[\u0006$\u0018n\u001c8\t\u000b-\u0012\u0001\u0019\u0001\u000e\u0002\tAd\u0017M\u001c")
public class CastParseJsonToFromJson
extends Rule<LogicalPlan>
implements TransformationConstructors {
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
        return plan.transformAllExpressions((PartialFunction<Expression, Transformation<Expression>>)((Object)new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Cast) {
                    Cast cast = (Cast)A1;
                    Expression expression2 = cast.expr();
                    DataType dt = cast.dataType();
                    if (expression2 instanceof CallFunction) {
                        Some<Seq<Expression>> some;
                        CallFunction callFunction = (CallFunction)expression2;
                        String string = callFunction.function_name();
                        Seq<Expression> seq = callFunction.arguments();
                        if ("PARSE_JSON".equals(string) && !(some = Seq$.MODULE$.unapplySeq(seq)).isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(1) == 0) {
                            Expression payload = (Expression)((SeqLike)some.get()).apply(0);
                            return (B1)DataTypeGenerator$.MODULE$.generateDataType(dt).map((Function1<SQLCodeBlock, JsonToStructs> & Serializable & scala.Serializable)dataType -> new JsonToStructs(payload, Literal$.MODULE$.apply(dataType), None$.MODULE$));
                        }
                    }
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Cast cast;
                Expression expression2;
                Expression expression3 = x1;
                if (expression3 instanceof Cast && (expression2 = (cast = (Cast)expression3).expr()) instanceof CallFunction) {
                    Some<Seq<Expression>> some;
                    CallFunction callFunction = (CallFunction)expression2;
                    String string = callFunction.function_name();
                    Seq<Expression> seq = callFunction.arguments();
                    if ("PARSE_JSON".equals(string) && !(some = Seq$.MODULE$.unapplySeq(seq)).isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(1) == 0) {
                        return true;
                    }
                }
                return false;
            }

            private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$1(com.databricks.labs.morpheus.intermediate.Expression com.databricks.labs.morpheus.transform.SQLCodeBlock )}, serializedLambda);
            }
        }));
    }

    public CastParseJsonToFromJson() {
        TransformationConstructors.$init$(this);
    }
}

