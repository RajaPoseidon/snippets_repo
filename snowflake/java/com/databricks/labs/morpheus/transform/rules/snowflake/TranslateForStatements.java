/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.CurrentOrigin$;
import com.databricks.labs.morpheus.intermediate.Dot;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.Id$;
import com.databricks.labs.morpheus.intermediate.Literal$;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.Project;
import com.databricks.labs.morpheus.intermediate.Range;
import com.databricks.labs.morpheus.intermediate.Range$;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.procedures.ForInRange;
import com.databricks.labs.morpheus.intermediate.procedures.ForStatement;
import com.databricks.labs.morpheus.intermediate.procedures.ForStatement$;
import com.databricks.labs.morpheus.statistics.TranspileStatistics;
import com.databricks.labs.morpheus.transform.Phase;
import com.databricks.labs.morpheus.transform.Result;
import com.databricks.labs.morpheus.transform.Transformation;
import com.databricks.labs.morpheus.transform.TransformationConstructors;
import com.databricks.labs.morpheus.transform.TranspilerConfig;
import com.databricks.labs.morpheus.transform.TranspilerState;
import com.databricks.labs.morpheus.transform.WorkflowStage;
import com.databricks.labs.morpheus.transform.package$;
import com.databricks.labs.morpheus.transform.rules.snowflake.TranslateForStatements$;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import scala.Function0;
import scala.Function1;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001)3A!\u0002\u0004\u0001+!)1\u0005\u0001C\u0001I!)q\u0005\u0001C!Q!)a\u0006\u0001C\u0005_!)\u0001\b\u0001C\u0005s\t1BK]1og2\fG/\u001a$peN#\u0018\r^3nK:$8O\u0003\u0002\b\u0011\u0005I1O\\8xM2\f7.\u001a\u0006\u0003\u0013)\tQA];mKNT!a\u0003\u0007\u0002\u0013Q\u0014\u0018M\\:g_Jl'BA\u0007\u000f\u0003!iwN\u001d9iKV\u001c(BA\b\u0011\u0003\u0011a\u0017MY:\u000b\u0005E\u0011\u0012A\u00033bi\u0006\u0014'/[2lg*\t1#A\u0002d_6\u001c\u0001aE\u0002\u0001-}\u00012a\u0006\u000e\u001d\u001b\u0005A\"BA\r\r\u00031Ig\u000e^3s[\u0016$\u0017.\u0019;f\u0013\tY\u0002D\u0001\u0003Sk2,\u0007CA\f\u001e\u0013\tq\u0002DA\u0006M_\u001eL7-\u00197QY\u0006t\u0007C\u0001\u0011\"\u001b\u0005Q\u0011B\u0001\u0012\u000b\u0005i!&/\u00198tM>\u0014X.\u0019;j_:\u001cuN\\:ueV\u001cGo\u001c:t\u0003\u0019a\u0014N\\5u}Q\tQ\u0005\u0005\u0002'\u00015\ta!A\u0003baBd\u0017\u0010\u0006\u0002*YA\u0019\u0001E\u000b\u000f\n\u0005-R!A\u0004+sC:\u001chm\u001c:nCRLwN\u001c\u0005\u0006[\t\u0001\r\u0001H\u0001\u0005iJ,W-A\nue\u0006t7\u000f\\1uK\u001a{'/\u00138SC:<W\r\u0006\u0002*a!)\u0011g\u0001a\u0001e\u0005)!/\u00198hKB\u00111GN\u0007\u0002i)\u0011Q\u0007G\u0001\u000baJ|7-\u001a3ve\u0016\u001c\u0018BA\u001c5\u0005)1uN]%o%\u0006tw-Z\u0001\u0010M&DX\u000f\u001d*fM\u0016\u0014XM\\2fgR\u0019\u0011FO%\t\u000bm\"\u0001\u0019\u0001\u001f\u0002\u0007M$(\u000f\u0005\u0002>\r:\u0011a\b\u0012\t\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003R\ta\u0001\u0010:p_Rt$\"A\"\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0013\u0015A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!\u0012\"\t\u000b5\"\u0001\u0019\u0001\u000f")
public class TranslateForStatements
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
    public Transformation<LogicalPlan> apply(LogicalPlan tree) {
        return tree.transformUp(new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TranslateForStatements $outer;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof ForInRange) {
                    ForInRange forInRange = (ForInRange)A1;
                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$TranslateForStatements$$translateForInRange(forInRange);
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                LogicalPlan logicalPlan2 = x1;
                return logicalPlan2 instanceof ForInRange;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        });
    }

    public Transformation<LogicalPlan> com$databricks$labs$morpheus$transform$rules$snowflake$TranslateForStatements$$translateForInRange(ForInRange range) {
        String variableName = range.variableName().id();
        Option step = range.isReverse() ? new Some<Expression>(Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(-1))) : None$.MODULE$;
        return package$.MODULE$.TransformationSeqOps(range.statements().map((Function1<LogicalPlan, Transformation> & Serializable & scala.Serializable)x$1 -> this.fixupReferences(variableName, (LogicalPlan)x$1), Seq$.MODULE$.canBuildFrom())).sequence().map((Function1<Seq, ForStatement> & Serializable & scala.Serializable)statements -> (ForStatement)CurrentOrigin$.MODULE$.withOrigin(range.origin(), (Function0<ForStatement> & Serializable & scala.Serializable)() -> new ForStatement((Option<String>)new Some<String>(variableName), new Project(new Range(range.lowerBound(), range.upperBound(), step, Range$.MODULE$.apply$default$4()), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new Id("id", Id$.MODULE$.apply$default$2())), Nil$.MODULE$)), (Seq<LogicalPlan>)statements, ForStatement$.MODULE$.apply$default$4())));
    }

    private Transformation<LogicalPlan> fixupReferences(String str, LogicalPlan tree) {
        return tree.transformUp(new scala.Serializable(this, str){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TranslateForStatements $outer;
            public final String str$1;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                return (B1)A1.transformExpressionsUp((PartialFunction<Expression, Transformation<Expression>>)((Object)new scala.Serializable(this){
                    public static final long serialVersionUID = 0L;
                    private final /* synthetic */ $anonfun$fixupReferences$1 $outer;

                    public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                        A1 A1 = x1;
                        if (A1 instanceof Id) {
                            Id id = (Id)A1;
                            String string = id.id();
                            String string2 = this.$outer.str$1;
                            if (!(string != null ? !string.equals(string2) : string2 != null)) {
                                return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$TranslateForStatements$$anonfun$$$outer().ok(new Dot(id, new Id("id", Id$.MODULE$.apply$default$2())));
                            }
                        }
                        return function1.apply(x1);
                    }

                    public final boolean isDefinedAt(Expression x1) {
                        Expression expression2 = x1;
                        if (expression2 instanceof Id) {
                            Id id = (Id)expression2;
                            String string = id.id();
                            String string2 = this.$outer.str$1;
                            if (!(string != null ? !string.equals(string2) : string2 != null)) {
                                return true;
                            }
                        }
                        return false;
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

            public /* synthetic */ TranslateForStatements com$databricks$labs$morpheus$transform$rules$snowflake$TranslateForStatements$$anonfun$$$outer() {
                return this.$outer;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.str$1 = str$1;
            }
        });
    }

    public TranslateForStatements() {
        TransformationConstructors.$init$(this);
    }
}

