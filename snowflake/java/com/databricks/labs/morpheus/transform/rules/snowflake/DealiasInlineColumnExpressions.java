/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.WithCTE;
import com.databricks.labs.morpheus.intermediate.snowflake.InlineColumnExpression;
import com.databricks.labs.morpheus.statistics.TranspileStatistics;
import com.databricks.labs.morpheus.transform.Phase;
import com.databricks.labs.morpheus.transform.Result;
import com.databricks.labs.morpheus.transform.Transformation;
import com.databricks.labs.morpheus.transform.TransformationConstructors;
import com.databricks.labs.morpheus.transform.TranspilerConfig;
import com.databricks.labs.morpheus.transform.TranspilerState;
import com.databricks.labs.morpheus.transform.WorkflowStage;
import com.databricks.labs.morpheus.transform.rules.snowflake.DealiasInlineColumnExpressions$$anonfun$com$databricks$labs$morpheus$transform$rules$snowflake$DealiasInlineColumnExpressions$;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001\u00053A\u0001B\u0003\u0001)!)!\u0005\u0001C\u0001G!)a\u0005\u0001C!O!)Q\u0006\u0001C\u0005]\tqB)Z1mS\u0006\u001c\u0018J\u001c7j]\u0016\u001cu\u000e\\;n]\u0016C\bO]3tg&|gn\u001d\u0006\u0003\r\u001d\t\u0011b\u001d8po\u001ad\u0017m[3\u000b\u0005!I\u0011!\u0002:vY\u0016\u001c(B\u0001\u0006\f\u0003%!(/\u00198tM>\u0014XN\u0003\u0002\r\u001b\u0005AQn\u001c:qQ\u0016,8O\u0003\u0002\u000f\u001f\u0005!A.\u00192t\u0015\t\u0001\u0012#\u0001\u0006eCR\f'M]5dWNT\u0011AE\u0001\u0004G>l7\u0001A\n\u0004\u0001Uq\u0002c\u0001\f\u001a75\tqC\u0003\u0002\u0019\u0017\u0005a\u0011N\u001c;fe6,G-[1uK&\u0011!d\u0006\u0002\u0005%VdW\r\u0005\u0002\u00179%\u0011Qd\u0006\u0002\f\u0019><\u0017nY1m!2\fg\u000e\u0005\u0002 A5\t\u0011\"\u0003\u0002\"\u0013\tQBK]1og\u001a|'/\\1uS>t7i\u001c8tiJ,8\r^8sg\u00061A(\u001b8jiz\"\u0012\u0001\n\t\u0003K\u0001i\u0011!B\u0001\u0006CB\u0004H.\u001f\u000b\u0003Q-\u00022aH\u0015\u001c\u0013\tQ\u0013B\u0001\bUe\u0006t7OZ8s[\u0006$\u0018n\u001c8\t\u000b1\u0012\u0001\u0019A\u000e\u0002\tAd\u0017M\\\u0001\u0014EVtG\r\\3J]2Lg.Z\"pYVlgn\u001d\u000b\u0004Q=z\u0004\"\u0002\u0019\u0004\u0001\u0004\t\u0014!\u00029mC:\u001c\bc\u0001\u001a=79\u00111'\u000f\b\u0003i]j\u0011!\u000e\u0006\u0003mM\ta\u0001\u0010:p_Rt\u0014\"\u0001\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005iZ\u0014a\u00029bG.\fw-\u001a\u0006\u0002q%\u0011QH\u0010\u0002\u0004'\u0016\f(B\u0001\u001e<\u0011\u0015\u00015\u00011\u0001\u001c\u0003\u0015\tX/\u001a:z\u0001")
public class DealiasInlineColumnExpressions
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
        return plan.transform(new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ DealiasInlineColumnExpressions $outer;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof WithCTE) {
                    WithCTE withCTE = (WithCTE)A1;
                    Seq<LogicalPlan> ctes = withCTE.ctes();
                    LogicalPlan query = withCTE.query();
                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasInlineColumnExpressions$$bundleInlineColumns(ctes, query);
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                LogicalPlan logicalPlan2 = x1;
                return logicalPlan2 instanceof WithCTE;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public Transformation<LogicalPlan> com$databricks$labs$morpheus$transform$rules$snowflake$DealiasInlineColumnExpressions$$bundleInlineColumns(Seq<LogicalPlan> plans, LogicalPlan query) {
        Tuple2<Nil$, Nil$> tuple2 = plans.foldLeft(new Tuple2<Nil$, Nil$>(Nil$.MODULE$, Nil$.MODULE$), (Function2<Tuple2, LogicalPlan, Tuple2> & Serializable & scala.Serializable)(x0$1, x1$1) -> {
            Tuple2<Tuple2, LogicalPlan> tuple2 = new Tuple2<Tuple2, LogicalPlan>((Tuple2)x0$1, (LogicalPlan)x1$1);
            if (tuple2 != null) {
                Tuple2 tuple22 = tuple2._1();
                LogicalPlan i = tuple2._2();
                if (tuple22 != null) {
                    Seq ics = (Seq)tuple22._1();
                    Seq tbls = (Seq)tuple22._2();
                    if (i instanceof InlineColumnExpression) {
                        InlineColumnExpression inlineColumnExpression = (InlineColumnExpression)i;
                        return new Tuple2(ics.$colon$plus(inlineColumnExpression, Seq$.MODULE$.canBuildFrom()), tbls);
                    }
                }
            }
            if (tuple2 != null) {
                Tuple2 tuple23 = tuple2._1();
                LogicalPlan t2 = tuple2._2();
                if (tuple23 != null) {
                    Seq ics = (Seq)tuple23._1();
                    Seq tbls = (Seq)tuple23._2();
                    return new Tuple2(ics, tbls.$colon$plus(t2, Seq$.MODULE$.canBuildFrom()));
                }
            }
            throw new MatchError(tuple2);
        });
        if (tuple2 == null) {
            throw new MatchError(tuple2);
        }
        Seq inlineColumns = tuple2._1();
        Seq tables = tuple2._2();
        Tuple2<Seq, Seq> tuple22 = new Tuple2<Seq, Seq>(inlineColumns, tables);
        Seq inlineColumns2 = tuple22._1();
        Seq tables2 = tuple22._2();
        Map columnNamesToValues = ((TraversableOnce)inlineColumns2.map((Function1<InlineColumnExpression, Tuple2> & Serializable & scala.Serializable)ic -> Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(ic.columnName()), ic.value()), Seq$.MODULE$.canBuildFrom())).toMap(Predef$.MODULE$.$conforms());
        return query.transformUp(new scala.Serializable(this, columnNamesToValues){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ DealiasInlineColumnExpressions $outer;
            public final Map columnNamesToValues$1;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                return (B1)A1.transformExpressionsUp((PartialFunction<Expression, Transformation<Expression>>)((Object)new scala.Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$com$databricks$labs$morpheus$transform$rules$snowflake$DealiasInlineColumnExpressions$$bundleInlineColumns$1 $outer;

                    public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                        Id id;
                        A1 A1 = x1;
                        if (A1 instanceof Id && this.$outer.columnNamesToValues$1.contains(id = (Id)A1)) {
                            return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$DealiasInlineColumnExpressions$$anonfun$$$outer().ok(this.$outer.columnNamesToValues$1.apply(id));
                        }
                        return function1.apply(x1);
                    }

                    public final boolean isDefinedAt(Expression x1) {
                        Id id;
                        Expression expression2 = x1;
                        return expression2 instanceof Id && this.$outer.columnNamesToValues$1.contains(id = (Id)expression2);
                    }
                    {
                        if ($outer == null) {
                            throw null;
                        }
                        this.$outer = $outer;
                    }
                }));
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                LogicalPlan logicalPlan2 = x1;
                return true;
            }

            public /* synthetic */ DealiasInlineColumnExpressions com$databricks$labs$morpheus$transform$rules$snowflake$DealiasInlineColumnExpressions$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.columnNamesToValues$1 = columnNamesToValues$1;
            }
        }).map((Function1<LogicalPlan, WithCTE> & Serializable & scala.Serializable)x$2 -> new WithCTE(tables2, (LogicalPlan)x$2));
    }

    public DealiasInlineColumnExpressions() {
        TransformationConstructors.$init$(this);
    }
}

