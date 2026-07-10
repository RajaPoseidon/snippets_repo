/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Concat;
import com.databricks.labs.morpheus.intermediate.Expression;
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
import com.databricks.labs.morpheus.transform.rules.snowflake.FlattenNestedConcat$;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.PartialFunction;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.LambdaDeserialize;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001e2A\u0001B\u0003\u0001)!)!\u0005\u0001C\u0001G!)a\u0005\u0001C!O!1Q\u0006\u0001C\u0001\u000f9\u00121C\u00127biR,gNT3ti\u0016$7i\u001c8dCRT!AB\u0004\u0002\u0013Mtwn\u001e4mC.,'B\u0001\u0005\n\u0003\u0015\u0011X\u000f\\3t\u0015\tQ1\"A\u0005ue\u0006t7OZ8s[*\u0011A\"D\u0001\t[>\u0014\b\u000f[3vg*\u0011abD\u0001\u0005Y\u0006\u00147O\u0003\u0002\u0011#\u0005QA-\u0019;bEJL7m[:\u000b\u0003I\t1aY8n\u0007\u0001\u00192\u0001A\u000b\u001f!\r1\u0012dG\u0007\u0002/)\u0011\u0001dC\u0001\rS:$XM]7fI&\fG/Z\u0005\u00035]\u0011AAU;mKB\u0011a\u0003H\u0005\u0003;]\u00111\u0002T8hS\u000e\fG\u000e\u00157b]B\u0011q\u0004I\u0007\u0002\u0013%\u0011\u0011%\u0003\u0002\u001b)J\fgn\u001d4pe6\fG/[8o\u0007>t7\u000f\u001e:vGR|'o]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0011\u0002\"!\n\u0001\u000e\u0003\u0015\tQ!\u00199qYf$\"\u0001K\u0016\u0011\u0007}I3$\u0003\u0002+\u0013\tqAK]1og\u001a|'/\\1uS>t\u0007\"\u0002\u0017\u0003\u0001\u0004Y\u0012\u0001\u00029mC:\fQB\u001a7biR,gnQ8oG\u0006$X#A\u0018\u0011\tA\u001aT\u0007O\u0007\u0002c)\t!'A\u0003tG\u0006d\u0017-\u0003\u00025c\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u0002\u0017m%\u0011qg\u0006\u0002\u000b\u000bb\u0004(/Z:tS>t\u0007cA\u0010*k\u0001")
public class FlattenNestedConcat
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
        return plan.transformAllExpressions(this.flattenConcat());
    }

    public PartialFunction<Expression, Transformation<Expression>> flattenConcat() {
        return new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ FlattenNestedConcat $outer;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                return (B1)A1.transformUp((PartialFunction<Expression, Transformation<Expression>>)((Object)new scala.Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$flattenConcat$1 $outer;

                    public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                        A1 A1 = x1;
                        if (A1 instanceof Concat) {
                            Concat concat = (Concat)A1;
                            Seq<Expression> items = concat.children();
                            return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenNestedConcat$$anonfun$$$outer().ok(new Concat(items.flatMap((Function1<Expression, Seq> & Serializable & scala.Serializable)x0$1 -> {
                                Expression expression2 = x0$1;
                                if (expression2 instanceof Concat) {
                                    Concat concat = (Concat)expression2;
                                    Seq<Expression> sub = concat.children();
                                    return sub;
                                }
                                return new $colon$colon<Nothing$>((Nothing$)((Object)expression2), Nil$.MODULE$);
                            }, Seq$.MODULE$.canBuildFrom())));
                        }
                        return function1.apply(x1);
                    }

                    public final boolean isDefinedAt(Expression x1) {
                        Expression expression2 = x1;
                        return expression2 instanceof Concat;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }

                    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                        return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$2(com.databricks.labs.morpheus.intermediate.Expression )}, serializedLambda);
                    }
                }));
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                return true;
            }

            public /* synthetic */ FlattenNestedConcat com$databricks$labs$morpheus$transform$rules$snowflake$FlattenNestedConcat$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        };
    }

    public FlattenNestedConcat() {
        TransformationConstructors.$init$(this);
    }
}

