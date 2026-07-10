/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Assign;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Join;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.MergeAction;
import com.databricks.labs.morpheus.intermediate.MergeIntoTable;
import com.databricks.labs.morpheus.intermediate.MergeIntoTable$;
import com.databricks.labs.morpheus.intermediate.Noop$;
import com.databricks.labs.morpheus.intermediate.NoopNode$;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.UpdateAction;
import com.databricks.labs.morpheus.intermediate.UpdateTable;
import com.databricks.labs.morpheus.statistics.TranspileStatistics;
import com.databricks.labs.morpheus.transform.Phase;
import com.databricks.labs.morpheus.transform.Result;
import com.databricks.labs.morpheus.transform.Transformation;
import com.databricks.labs.morpheus.transform.TransformationConstructors;
import com.databricks.labs.morpheus.transform.TranspilerConfig;
import com.databricks.labs.morpheus.transform.TranspilerState;
import com.databricks.labs.morpheus.transform.WorkflowStage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import scala.Function1;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Serializable;
import scala.Some;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001A3AAB\u0004\u0001-!)A\u0005\u0001C\u0001K!)\u0001\u0006\u0001C!S!)q\u0006\u0001C\u0005a!)q\t\u0001C\u0005\u0011\")!\n\u0001C\u0005\u0017\niQ\u000b\u001d3bi\u0016$v.T3sO\u0016T!\u0001C\u0005\u0002\u0013Mtwn\u001e4mC.,'B\u0001\u0006\f\u0003\u0015\u0011X\u000f\\3t\u0015\taQ\"A\u0005ue\u0006t7OZ8s[*\u0011abD\u0001\t[>\u0014\b\u000f[3vg*\u0011\u0001#E\u0001\u0005Y\u0006\u00147O\u0003\u0002\u0013'\u0005QA-\u0019;bEJL7m[:\u000b\u0003Q\t1aY8n\u0007\u0001\u00192\u0001A\f!!\rA2$H\u0007\u00023)\u0011!$D\u0001\rS:$XM]7fI&\fG/Z\u0005\u00039e\u0011AAU;mKB\u0011\u0001DH\u0005\u0003?e\u00111\u0002T8hS\u000e\fG\u000e\u00157b]B\u0011\u0011EI\u0007\u0002\u0017%\u00111e\u0003\u0002\u001b)J\fgn\u001d4pe6\fG/[8o\u0007>t7\u000f\u001e:vGR|'o]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0019\u0002\"a\n\u0001\u000e\u0003\u001d\tQ!\u00199qYf$\"AK\u0017\u0011\u0007\u0005ZS$\u0003\u0002-\u0017\tqAK]1og\u001a|'/\\1uS>t\u0007\"\u0002\u0018\u0003\u0001\u0004i\u0012\u0001\u00029mC:\fa\"\\1uG\",G-Q2uS>t7\u000f\u0006\u00022\u0005B\u0019!\u0007P \u000f\u0005MJdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c\u0016\u0003\u0019a$o\\8u}%\t\u0001(A\u0003tG\u0006d\u0017-\u0003\u0002;w\u00059\u0001/Y2lC\u001e,'\"\u0001\u001d\n\u0005ur$aA*fc*\u0011!h\u000f\t\u00031\u0001K!!Q\r\u0003\u00175+'oZ3BGRLwN\u001c\u0005\u0006\u0007\u000e\u0001\r\u0001R\u0001\u0007kB$\u0017\r^3\u0011\u0005a)\u0015B\u0001$\u001a\u0005-)\u0006\u000fZ1uKR\u000b'\r\\3\u0002\rM|WO]2f)\ti\u0012\nC\u0003D\t\u0001\u0007A)A\u0005d_:$\u0017\u000e^5p]R\u0011Aj\u0014\t\u000315K!AT\r\u0003\u0015\u0015C\bO]3tg&|g\u000eC\u0003D\u000b\u0001\u0007A\t")
public class UpdateToMerge
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
        return plan.transform(new Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ UpdateToMerge $outer;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                boolean bl = false;
                UpdateTable updateTable = null;
                A1 A1 = x1;
                if (A1 instanceof UpdateTable) {
                    bl = true;
                    updateTable = (UpdateTable)A1;
                    Option<LogicalPlan> option = updateTable.source();
                    if (None$.MODULE$.equals(option)) {
                        return (B1)this.$outer.ok(updateTable);
                    }
                }
                if (bl) {
                    return (B1)this.$outer.ok(new MergeIntoTable(updateTable.target(), this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$UpdateToMerge$$source(updateTable), this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$UpdateToMerge$$condition(updateTable), this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$UpdateToMerge$$matchedActions(updateTable), MergeIntoTable$.MODULE$.apply$default$5(), MergeIntoTable$.MODULE$.apply$default$6()));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                boolean bl = false;
                UpdateTable updateTable = null;
                LogicalPlan logicalPlan2 = x1;
                if (logicalPlan2 instanceof UpdateTable) {
                    bl = true;
                    updateTable = (UpdateTable)logicalPlan2;
                    Option<LogicalPlan> option = updateTable.source();
                    if (None$.MODULE$.equals(option)) {
                        return true;
                    }
                }
                return bl;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public Seq<MergeAction> com$databricks$labs$morpheus$transform$rules$snowflake$UpdateToMerge$$matchedActions(UpdateTable update) {
        Seq<Assign> set = update.set().collect(new Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Assign) {
                    Assign assign = (Assign)A1;
                    return (B1)assign;
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                return expression2 instanceof Assign;
            }
        }, Seq$.MODULE$.canBuildFrom());
        return new $colon$colon<Nothing$>((Nothing$)((Object)new UpdateAction(None$.MODULE$, set)), Nil$.MODULE$);
    }

    public LogicalPlan com$databricks$labs$morpheus$transform$rules$snowflake$UpdateToMerge$$source(UpdateTable update) {
        Option<LogicalPlan> option = update.source();
        if (option instanceof Some) {
            Some some = (Some)option;
            LogicalPlan plan = (LogicalPlan)some.value();
            LogicalPlan logicalPlan2 = plan;
            if (logicalPlan2 instanceof Join) {
                Join join = (Join)logicalPlan2;
                LogicalPlan source = join.right();
                return source;
            }
            return plan;
        }
        if (None$.MODULE$.equals(option)) {
            return NoopNode$.MODULE$;
        }
        throw new MatchError(option);
    }

    public Expression com$databricks$labs$morpheus$transform$rules$snowflake$UpdateToMerge$$condition(UpdateTable update) {
        Option<Expression> option = update.where();
        if (option instanceof Some) {
            Some some = (Some)option;
            Expression condition = (Expression)some.value();
            return condition;
        }
        if (None$.MODULE$.equals(option)) {
            return Noop$.MODULE$;
        }
        throw new MatchError(option);
    }

    public UpdateToMerge() {
        TransformationConstructors.$init$(this);
    }
}

