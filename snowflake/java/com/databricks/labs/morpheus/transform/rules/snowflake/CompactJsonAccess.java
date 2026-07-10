/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Dot;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Fn;
import com.databricks.labs.morpheus.intermediate.IRHelpers;
import com.databricks.labs.morpheus.intermediate.JsonAccess;
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
import com.databricks.labs.morpheus.transform.rules.snowflake.CompactJsonAccess$;
import com.fasterxml.jackson.annotation.JsonIgnore;
import scala.Function1;
import scala.PartialFunction;
import scala.Serializable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001=2Aa\u0001\u0003\u0001'!)A\u0005\u0001C\u0001K!)\u0001\u0006\u0001C!S\t\t2i\\7qC\u000e$(j]8o\u0003\u000e\u001cWm]:\u000b\u0005\u00151\u0011!C:o_^4G.Y6f\u0015\t9\u0001\"A\u0003sk2,7O\u0003\u0002\n\u0015\u0005IAO]1og\u001a|'/\u001c\u0006\u0003\u00171\t\u0001\"\\8sa\",Wo\u001d\u0006\u0003\u001b9\tA\u0001\\1cg*\u0011q\u0002E\u0001\u000bI\u0006$\u0018M\u0019:jG.\u001c(\"A\t\u0002\u0007\r|Wn\u0001\u0001\u0014\t\u0001!R\u0004\t\t\u0004+aQR\"\u0001\f\u000b\u0005]Q\u0011\u0001D5oi\u0016\u0014X.\u001a3jCR,\u0017BA\r\u0017\u0005\u0011\u0011V\u000f\\3\u0011\u0005UY\u0012B\u0001\u000f\u0017\u0005-aunZ5dC2\u0004F.\u00198\u0011\u0005Uq\u0012BA\u0010\u0017\u0005%I%\u000bS3ma\u0016\u00148\u000f\u0005\u0002\"E5\t\u0001\"\u0003\u0002$\u0011\tQBK]1og\u001a|'/\\1uS>t7i\u001c8tiJ,8\r^8sg\u00061A(\u001b8jiz\"\u0012A\n\t\u0003O\u0001i\u0011\u0001B\u0001\u0006CB\u0004H.\u001f\u000b\u0003U5\u00022!I\u0016\u001b\u0013\ta\u0003B\u0001\bUe\u0006t7OZ8s[\u0006$\u0018n\u001c8\t\u000b9\u0012\u0001\u0019\u0001\u000e\u0002\tAd\u0017M\u001c")
public class CompactJsonAccess
extends Rule<LogicalPlan>
implements IRHelpers,
TransformationConstructors {
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
    public LogicalPlan namedTable(String name) {
        return IRHelpers.namedTable$(this, name);
    }

    @Override
    public LogicalPlan crossJoin(LogicalPlan left, LogicalPlan right) {
        return IRHelpers.crossJoin$(this, left, right);
    }

    @Override
    public Fn withNormalizedName(Fn call2) {
        return IRHelpers.withNormalizedName$(this, call2);
    }

    @Override
    public Transformation<LogicalPlan> apply(LogicalPlan plan) {
        return plan.transformAllExpressions((PartialFunction<Expression, Transformation<Expression>>)((Object)new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ CompactJsonAccess $outer;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 != null) {
                    A1 A12 = A1;
                    return (B1)A12.transform((PartialFunction<Expression, Transformation<Expression>>)((Object)new Serializable(this){
                        public static final long serialVersionUID = 0L;
                        private final /* synthetic */ $anonfun$apply$1 $outer;

                        public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                            boolean bl = false;
                            JsonAccess jsonAccess = null;
                            A1 A1 = x1;
                            if (A1 instanceof JsonAccess) {
                                bl = true;
                                jsonAccess = (JsonAccess)A1;
                                Expression expression2 = jsonAccess.json();
                                Expression expression3 = jsonAccess.path();
                                if (expression2 instanceof JsonAccess) {
                                    JsonAccess jsonAccess2 = (JsonAccess)expression2;
                                    Expression l1 = jsonAccess2.json();
                                    Expression r1 = jsonAccess2.path();
                                    if (expression3 instanceof JsonAccess) {
                                        JsonAccess jsonAccess3 = (JsonAccess)expression3;
                                        Expression l2 = jsonAccess3.json();
                                        Expression r2 = jsonAccess3.path();
                                        return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$CompactJsonAccess$$anonfun$$$outer().ok(new JsonAccess(l1, new Dot(r1, new Dot(l2, r2))));
                                    }
                                }
                            }
                            if (bl) {
                                Expression expression4 = jsonAccess.json();
                                Expression r2 = jsonAccess.path();
                                if (expression4 instanceof JsonAccess) {
                                    JsonAccess jsonAccess4 = (JsonAccess)expression4;
                                    Expression l1 = jsonAccess4.json();
                                    Expression r1 = jsonAccess4.path();
                                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$CompactJsonAccess$$anonfun$$$outer().ok(new JsonAccess(l1, new Dot(r1, r2)));
                                }
                            }
                            if (bl) {
                                Expression l1 = jsonAccess.json();
                                Expression expression5 = jsonAccess.path();
                                if (expression5 instanceof JsonAccess) {
                                    JsonAccess jsonAccess5 = (JsonAccess)expression5;
                                    Expression l2 = jsonAccess5.json();
                                    Expression r2 = jsonAccess5.path();
                                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$CompactJsonAccess$$anonfun$$$outer().ok(new JsonAccess(l1, new Dot(l2, r2)));
                                }
                            }
                            return function1.apply(x1);
                        }

                        public final boolean isDefinedAt(Expression x1) {
                            Expression expression2;
                            Expression expression3;
                            boolean bl = false;
                            JsonAccess jsonAccess = null;
                            Expression expression4 = x1;
                            if (expression4 instanceof JsonAccess) {
                                bl = true;
                                jsonAccess = (JsonAccess)expression4;
                                Expression expression5 = jsonAccess.json();
                                Expression expression6 = jsonAccess.path();
                                if (expression5 instanceof JsonAccess && expression6 instanceof JsonAccess) {
                                    return true;
                                }
                            }
                            if (bl && (expression3 = jsonAccess.json()) instanceof JsonAccess) {
                                return true;
                            }
                            return bl && (expression2 = jsonAccess.path()) instanceof JsonAccess;
                        }
                        {
                            if ($outer == null) {
                                throw null;
                            }
                            this.$outer = $outer;
                        }
                    }));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                return expression2 != null;
            }

            public /* synthetic */ CompactJsonAccess com$databricks$labs$morpheus$transform$rules$snowflake$CompactJsonAccess$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public CompactJsonAccess() {
        IRHelpers.$init$(this);
        TransformationConstructors.$init$(this);
    }
}

