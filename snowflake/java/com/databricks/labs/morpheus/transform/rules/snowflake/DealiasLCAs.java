/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Aggregate;
import com.databricks.labs.morpheus.intermediate.AggregateFn;
import com.databricks.labs.morpheus.intermediate.Alias;
import com.databricks.labs.morpheus.intermediate.AllColumn;
import com.databricks.labs.morpheus.intermediate.AnyColumn;
import com.databricks.labs.morpheus.intermediate.CurrentOrigin$;
import com.databricks.labs.morpheus.intermediate.Exists;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Filter;
import com.databricks.labs.morpheus.intermediate.Fn;
import com.databricks.labs.morpheus.intermediate.IRHelpers;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.Project;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.ScalarSubquery;
import com.databricks.labs.morpheus.intermediate.TreeNode;
import com.databricks.labs.morpheus.intermediate.Window;
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
import java.lang.invoke.MethodHandle;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Tuple2;
import scala.collection.GenMap;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LambdaDeserialize;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001q3Aa\u0002\u0005\u0001/!)\u0001\u0006\u0001C\u0001S!)A\u0006\u0001C![!11\u0007\u0001C\u0001\u0015QBQA\u000e\u0001\u0005\n]BQA\u0010\u0001\u0005\n}BQ\u0001\u0017\u0001\u0005\ne\u00131\u0002R3bY&\f7\u000fT\"Bg*\u0011\u0011BC\u0001\ng:|wO\u001a7bW\u0016T!a\u0003\u0007\u0002\u000bI,H.Z:\u000b\u00055q\u0011!\u0003;sC:\u001chm\u001c:n\u0015\ty\u0001#\u0001\u0005n_J\u0004\b.Z;t\u0015\t\t\"#\u0001\u0003mC\n\u001c(BA\n\u0015\u0003)!\u0017\r^1ce&\u001c7n\u001d\u0006\u0002+\u0005\u00191m\\7\u0004\u0001M!\u0001\u0001G\u0011%!\rIBDH\u0007\u00025)\u00111DD\u0001\rS:$XM]7fI&\fG/Z\u0005\u0003;i\u0011AAU;mKB\u0011\u0011dH\u0005\u0003Ai\u00111\u0002T8hS\u000e\fG\u000e\u00157b]B\u0011\u0011DI\u0005\u0003Gi\u0011\u0011\"\u0013*IK2\u0004XM]:\u0011\u0005\u00152S\"\u0001\u0007\n\u0005\u001db!A\u0007+sC:\u001chm\u001c:nCRLwN\\\"p]N$(/^2u_J\u001c\u0018A\u0002\u001fj]&$h\bF\u0001+!\tY\u0003!D\u0001\t\u0003\u0015\t\u0007\u000f\u001d7z)\tq\u0013\u0007E\u0002&_yI!\u0001\r\u0007\u0003\u001dQ\u0013\u0018M\\:g_Jl\u0017\r^5p]\")!G\u0001a\u0001=\u0005!\u0001\u000f\\1o\u00035!(/\u00198tM>\u0014X\u000e\u00157b]R\u0011a&\u000e\u0005\u0006e\r\u0001\rAH\u0001\u000fI\u0016\fG.[1t!J|'.Z2u)\tAD\bE\u0002&_e\u0002\"!\u0007\u001e\n\u0005mR\"a\u0002)s_*,7\r\u001e\u0005\u0006{\u0011\u0001\r!O\u0001\baJ|'.Z2u\u0003q!W-\u00197jCN<\u0006.\u001a:f\u0019\u000e\u000b\u0015j]+ogV\u0004\bo\u001c:uK\u0012$2\u0001\u0011#G!\r)s&\u0011\t\u00033\tK!a\u0011\u000e\u0003\u0015\u0015C\bO]3tg&|g\u000eC\u0003F\u000b\u0001\u0007\u0011)\u0001\u0003fqB\u0014\b\"B$\u0006\u0001\u0004A\u0015aB1mS\u0006\u001cXm\u001d\t\u0005\u0013J+\u0016I\u0004\u0002K!B\u00111JT\u0007\u0002\u0019*\u0011QJF\u0001\u0007yI|w\u000e\u001e \u000b\u0003=\u000bQa]2bY\u0006L!!\u0015(\u0002\rA\u0013X\rZ3g\u0013\t\u0019FKA\u0002NCBT!!\u0015(\u0011\u0005%3\u0016BA,U\u0005\u0019\u0019FO]5oO\u0006\tB-Z1mS\u0006\u001cX\t\u001f9sKN\u001c\u0018n\u001c8\u0015\u0007\u0001S6\fC\u0003F\r\u0001\u0007\u0011\tC\u0003H\r\u0001\u0007\u0001\n")
public class DealiasLCAs
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
        return this.transformPlan(plan);
    }

    public Transformation<LogicalPlan> transformPlan(LogicalPlan plan) {
        return plan.transform(new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ DealiasLCAs $outer;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Project) {
                    Project project = (Project)A1;
                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasProject(project);
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                LogicalPlan logicalPlan2 = x1;
                return logicalPlan2 instanceof Project;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public Transformation<Project> com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasProject(Project project) {
        Transformation<Tuple2<GenMap, Nil$>> init = this.ok(new Tuple2<GenMap, Nil$>(Predef$.MODULE$.Map().empty(), Nil$.MODULE$));
        boolean isGroupBy = project.input().find((Function1<LogicalPlan, Object> & Serializable & scala.Serializable)x$1 -> BoxesRunTime.boxToBoolean(DealiasLCAs.$anonfun$dealiasProject$1(x$1))).nonEmpty();
        return project.expressions().foldLeft(init, (Function2<Transformation, Expression, Transformation> & Serializable & scala.Serializable)(x0$1, x1$1) -> {
            Tuple2<Transformation, Expression> tuple2 = new Tuple2<Transformation, Expression>((Transformation)x0$1, (Expression)x1$1);
            if (tuple2 != null) {
                Transformation transfo = tuple2._1();
                Expression a = tuple2._2();
                if (a instanceof Alias) {
                    Alias alias = (Alias)a;
                    Expression expr = alias.expr();
                    Expression name = alias.name();
                    if (name instanceof Id) {
                        Id id = (Id)name;
                        return transfo.flatMap((Function1<Tuple2, Transformation> & Serializable & scala.Serializable)x0$2 -> {
                            Tuple2 tuple2 = x0$2;
                            if (tuple2 != null) {
                                Map aliases = (Map)tuple2._1();
                                Seq exprs = (Seq)tuple2._2();
                                if (isGroupBy) {
                                    return this.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasExpression(expr, aliases).map((Function1<Expression, Tuple2> & Serializable & scala.Serializable)newFoundAlias -> {
                                        Map<String, Expression> updatedAliases = aliases.$plus(Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(id.id()), newFoundAlias));
                                        return new Tuple2(updatedAliases, exprs.$colon$plus(CurrentOrigin$.MODULE$.withOrigin(alias.origin(), (Function0<Alias> & Serializable & scala.Serializable)() -> new Alias((Expression)newFoundAlias, id)), Seq$.MODULE$.canBuildFrom()));
                                    });
                                }
                                return this.dealiasWhereLCAIsUnsupported(expr, aliases).flatMap((Function1<Expression, Transformation> & Serializable & scala.Serializable)dw -> {
                                    Seq accumulatedExprs = exprs.$colon$plus(CurrentOrigin$.MODULE$.withOrigin(alias.origin(), (Function0<Alias> & Serializable & scala.Serializable)() -> new Alias((Expression)dw, id)), Seq$.MODULE$.canBuildFrom());
                                    return this.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasExpression((Expression)dw, aliases).map((Function1<Expression, Tuple2> & Serializable & scala.Serializable)newFoundAlias -> {
                                        Map<String, Expression> updatedAliases = aliases.$plus(Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(id.id()), newFoundAlias));
                                        return new Tuple2<Map<String, Expression>, Seq>(updatedAliases, accumulatedExprs);
                                    });
                                });
                            }
                            throw new MatchError(tuple2);
                        });
                    }
                }
            }
            if (tuple2 != null) {
                Transformation transfo = tuple2._1();
                Expression e = tuple2._2();
                return transfo.flatMap((Function1<Tuple2, Transformation> & Serializable & scala.Serializable)x0$3 -> {
                    Tuple2 tuple2 = x0$3;
                    if (tuple2 != null) {
                        Map aliases = (Map)tuple2._1();
                        Seq exprs = (Seq)tuple2._2();
                        if (isGroupBy) {
                            return this.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasExpression(e, aliases).map((Function1<Expression, Tuple2> & Serializable & scala.Serializable)de -> new Tuple2(aliases, exprs.$colon$plus(de, Seq$.MODULE$.canBuildFrom())));
                        }
                        return this.dealiasWhereLCAIsUnsupported(e, aliases).map((Function1<Expression, Tuple2> & Serializable & scala.Serializable)dw -> new Tuple2(aliases, exprs.$colon$plus(dw, Seq$.MODULE$.canBuildFrom())));
                    }
                    throw new MatchError(tuple2);
                });
            }
            throw new MatchError(tuple2);
        }).flatMap((Function1<Tuple2, Transformation> & Serializable & scala.Serializable)x0$4 -> {
            Tuple2 tuple2 = x0$4;
            if (tuple2 != null) {
                Map aliases = (Map)tuple2._1();
                Seq dealiasedExpressions = (Seq)tuple2._2();
                return project.input().transformDown(new scala.Serializable(this, aliases){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ DealiasLCAs $outer;
                    private final Map aliases$3;

                    public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                        A1 A1 = x1;
                        if (A1 instanceof Filter) {
                            Filter filter2 = (Filter)A1;
                            LogicalPlan in = filter2.input();
                            Expression cond = filter2.condition();
                            return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasExpression(cond, this.aliases$3).map((Function1<Expression, Filter> & Serializable & scala.Serializable)dealiasedCond -> (Filter)CurrentOrigin$.MODULE$.withOrigin(filter2.origin(), (Function0<Filter> & Serializable & scala.Serializable)() -> new Filter(in, (Expression)dealiasedCond)));
                        }
                        return function1.apply(x1);
                    }

                    public final boolean isDefinedAt(LogicalPlan x1) {
                        LogicalPlan logicalPlan2 = x1;
                        return logicalPlan2 instanceof Filter;
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                        this.aliases$3 = aliases$3;
                    }

                    private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                        return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$1(com.databricks.labs.morpheus.intermediate.Filter com.databricks.labs.morpheus.intermediate.LogicalPlan com.databricks.labs.morpheus.intermediate.Expression ), $anonfun$applyOrElse$2(com.databricks.labs.morpheus.intermediate.LogicalPlan com.databricks.labs.morpheus.intermediate.Expression )}, serializedLambda);
                    }
                }).map((Function1<LogicalPlan, Project> & Serializable & scala.Serializable)dealiasedInput -> (Project)CurrentOrigin$.MODULE$.withOrigin(project.origin(), (Function0<Project> & Serializable & scala.Serializable)() -> new Project((LogicalPlan)dealiasedInput, dealiasedExpressions)));
            }
            throw new MatchError(tuple2);
        });
    }

    private Transformation<Expression> dealiasWhereLCAIsUnsupported(Expression expr, Map<String, Expression> aliases) {
        return expr.transformDown(new scala.Serializable(this, aliases){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ DealiasLCAs $outer;
            private final Map aliases$4;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Window) {
                    Window window = (Window)A1;
                    return (B1)window.mapChildren((Function1<Expression, Transformation> & Serializable & scala.Serializable)x$2 -> $this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasExpression((Expression)x$2, $this.aliases$4));
                }
                if (A1 instanceof AggregateFn) {
                    AggregateFn aggregateFn = (AggregateFn)((Object)A1);
                    return (B1)((TreeNode)((Object)aggregateFn)).mapChildren((Function1<Expression, Transformation> & Serializable & scala.Serializable)x$3 -> $this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasExpression((Expression)x$3, $this.aliases$4));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                if (expression2 instanceof Window) {
                    return true;
                }
                return expression2 instanceof AggregateFn;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.aliases$4 = aliases$4;
            }

            private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$3(com.databricks.labs.morpheus.transform.rules.snowflake.DealiasLCAs$$anonfun$dealiasWhereLCAIsUnsupported$1 com.databricks.labs.morpheus.intermediate.Expression ), $anonfun$applyOrElse$4(com.databricks.labs.morpheus.transform.rules.snowflake.DealiasLCAs$$anonfun$dealiasWhereLCAIsUnsupported$1 com.databricks.labs.morpheus.intermediate.Expression )}, serializedLambda);
            }
        });
    }

    public Transformation<Expression> com$databricks$labs$morpheus$transform$rules$snowflake$DealiasLCAs$$dealiasExpression(Expression expr, Map<String, Expression> aliases) {
        return expr.transformUp(new scala.Serializable(this, aliases){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ DealiasLCAs $outer;
            private final Map aliases$5;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Id) {
                    Id id = (Id)A1;
                    return (B1)this.$outer.ok(this.aliases$5.getOrElse(id.id(), (Function0<Id> & Serializable & scala.Serializable)() -> id));
                }
                if (A1 instanceof Exists) {
                    Exists exists2 = (Exists)A1;
                    return (B1)this.$outer.transformPlan(exists2.relation()).map((Function1<LogicalPlan, Exists> & Serializable & scala.Serializable)plan -> (Exists)CurrentOrigin$.MODULE$.withOrigin(exists2.origin(), (Function0<Exists> & Serializable & scala.Serializable)() -> new Exists((LogicalPlan)plan)));
                }
                if (A1 instanceof ScalarSubquery) {
                    ScalarSubquery scalarSubquery = (ScalarSubquery)A1;
                    return (B1)this.$outer.transformPlan(scalarSubquery.plan()).map((Function1<LogicalPlan, ScalarSubquery> & Serializable & scala.Serializable)plan -> (ScalarSubquery)CurrentOrigin$.MODULE$.withOrigin(scalarSubquery.origin(), (Function0<ScalarSubquery> & Serializable & scala.Serializable)() -> new ScalarSubquery((LogicalPlan)plan)));
                }
                if (A1 instanceof AllColumn) {
                    AllColumn allColumn = (AllColumn)A1;
                    return (B1)this.$outer.transformPlan(allColumn.plan()).map((Function1<LogicalPlan, AllColumn> & Serializable & scala.Serializable)plan -> (AllColumn)CurrentOrigin$.MODULE$.withOrigin(allColumn.origin(), (Function0<AllColumn> & Serializable & scala.Serializable)() -> new AllColumn((LogicalPlan)plan)));
                }
                if (A1 instanceof AnyColumn) {
                    AnyColumn anyColumn = (AnyColumn)A1;
                    return (B1)this.$outer.transformPlan(anyColumn.plan()).map((Function1<LogicalPlan, AnyColumn> & Serializable & scala.Serializable)plan -> (AnyColumn)CurrentOrigin$.MODULE$.withOrigin(anyColumn.origin(), (Function0<AnyColumn> & Serializable & scala.Serializable)() -> new AnyColumn((LogicalPlan)plan)));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                if (expression2 instanceof Id) {
                    return true;
                }
                if (expression2 instanceof Exists) {
                    return true;
                }
                if (expression2 instanceof ScalarSubquery) {
                    return true;
                }
                if (expression2 instanceof AllColumn) {
                    return true;
                }
                return expression2 instanceof AnyColumn;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.aliases$5 = aliases$5;
            }

            private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$5(com.databricks.labs.morpheus.intermediate.Id ), $anonfun$applyOrElse$6(com.databricks.labs.morpheus.intermediate.Exists com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$8(com.databricks.labs.morpheus.intermediate.ScalarSubquery com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$10(com.databricks.labs.morpheus.intermediate.AllColumn com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$12(com.databricks.labs.morpheus.intermediate.AnyColumn com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$7(com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$9(com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$11(com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$13(com.databricks.labs.morpheus.intermediate.LogicalPlan )}, serializedLambda);
            }
        });
    }

    public static final /* synthetic */ boolean $anonfun$dealiasProject$1(LogicalPlan x$1) {
        return x$1 instanceof Aggregate;
    }

    public DealiasLCAs() {
        IRHelpers.$init$(this);
        TransformationConstructors.$init$(this);
    }
}

