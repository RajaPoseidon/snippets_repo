/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.CreateStoredProcedure;
import com.databricks.labs.morpheus.intermediate.CurrentOrigin$;
import com.databricks.labs.morpheus.intermediate.DataType;
import com.databricks.labs.morpheus.intermediate.DefineProcedureMode;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.LocalName;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.ProcedureLanguage;
import com.databricks.labs.morpheus.intermediate.ProcedureParameter;
import com.databricks.labs.morpheus.intermediate.ProcedureScalarVariable;
import com.databricks.labs.morpheus.intermediate.ProcedureScalarVariable$;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.SyntheticName;
import com.databricks.labs.morpheus.intermediate.Values;
import com.databricks.labs.morpheus.intermediate.procedures.Block;
import com.databricks.labs.morpheus.intermediate.procedures.Block$;
import com.databricks.labs.morpheus.intermediate.procedures.CompoundStatement;
import com.databricks.labs.morpheus.intermediate.procedures.Leave;
import com.databricks.labs.morpheus.intermediate.procedures.Return;
import com.databricks.labs.morpheus.intermediate.procedures.SetVariable;
import com.databricks.labs.morpheus.intermediate.procedures.Statement;
import com.databricks.labs.morpheus.intermediate.procedures.Statements;
import com.databricks.labs.morpheus.parsers.SyntheticNames;
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
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.LambdaDeserialize;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001%3A\u0001B\u0003\u0001)!)\u0001\u0006\u0001C\u0001S!)A\u0006\u0001C![!)1\u0007\u0001C\u0005i\tiQ)\\;mCR,'+\u001a;ve:T!AB\u0004\u0002\u0013Mtwn\u001e4mC.,'B\u0001\u0005\n\u0003\u0015\u0011X\u000f\\3t\u0015\tQ1\"A\u0005ue\u0006t7OZ8s[*\u0011A\"D\u0001\t[>\u0014\b\u000f[3vg*\u0011abD\u0001\u0005Y\u0006\u00147O\u0003\u0002\u0011#\u0005QA-\u0019;bEJL7m[:\u000b\u0003I\t1aY8n\u0007\u0001\u0019B\u0001A\u000b\u001fEA\u0019a#G\u000e\u000e\u0003]Q!\u0001G\u0006\u0002\u0019%tG/\u001a:nK\u0012L\u0017\r^3\n\u0005i9\"\u0001\u0002*vY\u0016\u0004\"A\u0006\u000f\n\u0005u9\"a\u0003'pO&\u001c\u0017\r\u001c)mC:\u0004\"a\b\u0011\u000e\u0003%I!!I\u0005\u00035Q\u0013\u0018M\\:g_Jl\u0017\r^5p]\u000e{gn\u001d;sk\u000e$xN]:\u0011\u0005\r2S\"\u0001\u0013\u000b\u0005\u0015Z\u0011a\u00029beN,'o]\u0005\u0003O\u0011\u0012abU=oi\",G/[2OC6,7/\u0001\u0004=S:LGO\u0010\u000b\u0002UA\u00111\u0006A\u0007\u0002\u000b\u0005)\u0011\r\u001d9msR\u0011a&\r\t\u0004?=Z\u0012B\u0001\u0019\n\u00059!&/\u00198tM>\u0014X.\u0019;j_:DQA\r\u0002A\u0002m\tA\u0001\u001e:fK\u0006iQ-\\;mCR,'+\u001a;ve:$2!\u000e\u001f?!\ryrF\u000e\t\u0003oij\u0011\u0001\u000f\u0006\u0003s]\t!\u0002\u001d:pG\u0016$WO]3t\u0013\tY\u0004HA\u0003CY>\u001c7\u000eC\u0003>\u0007\u0001\u0007a'\u0001\u0003qe>\u001c\u0007\"B \u0004\u0001\u0004\u0001\u0015A\u0003:fiV\u0014h\u000eV=qKB\u0019\u0011\t\u0012$\u000e\u0003\tS\u0011aQ\u0001\u0006g\u000e\fG.Y\u0005\u0003\u000b\n\u0013aa\u00149uS>t\u0007C\u0001\fH\u0013\tAuC\u0001\u0005ECR\fG+\u001f9f\u0001")
public class EmulateReturn
extends Rule<LogicalPlan>
implements TransformationConstructors,
SyntheticNames {
    @Override
    public Transformation<SyntheticName> freshName(String desiredName) {
        return SyntheticNames.freshName$(this, desiredName);
    }

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
        return tree.transformDown(new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ EmulateReturn $outer;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof CreateStoredProcedure) {
                    Some some;
                    LogicalPlan body2;
                    CreateStoredProcedure createStoredProcedure = (CreateStoredProcedure)A1;
                    Option<LogicalPlan> option = createStoredProcedure.body();
                    if (option instanceof Some && (body2 = (LogicalPlan)(some = (Some)option).value()) instanceof Block) {
                        Block block2 = (Block)body2;
                        return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$EmulateReturn$$emulateReturn(block2, createStoredProcedure.returnType()).map((Function1<Block, CreateStoredProcedure> & Serializable & scala.Serializable)block -> {
                            Some<LogicalPlan> x$1 = new Some<LogicalPlan>((LogicalPlan)block);
                            Expression x$2 = createStoredProcedure.copy$default$1();
                            Seq<ProcedureParameter> x$3 = createStoredProcedure.copy$default$2();
                            Seq<Expression> x$4 = createStoredProcedure.copy$default$3();
                            Option<DataType> x$5 = createStoredProcedure.copy$default$4();
                            ProcedureLanguage x$6 = createStoredProcedure.copy$default$5();
                            DefineProcedureMode x$7 = createStoredProcedure.copy$default$7();
                            int x$8 = createStoredProcedure.copy$default$8();
                            return createStoredProcedure.copy(x$2, x$3, x$4, x$5, x$6, x$1, x$7, x$8);
                        });
                    }
                    return (B1)this.$outer.ok(createStoredProcedure);
                }
                if (A1 instanceof CompoundStatement) {
                    CompoundStatement compoundStatement = (CompoundStatement)A1;
                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$EmulateReturn$$emulateReturn(Block$.MODULE$.apply(compoundStatement), None$.MODULE$);
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                LogicalPlan logicalPlan2 = x1;
                if (logicalPlan2 instanceof CreateStoredProcedure) {
                    return true;
                }
                return logicalPlan2 instanceof CompoundStatement;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }

            private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$1(com.databricks.labs.morpheus.intermediate.CreateStoredProcedure com.databricks.labs.morpheus.intermediate.procedures.Block )}, serializedLambda);
            }
        });
    }

    public Transformation<Block> com$databricks$labs$morpheus$transform$rules$snowflake$EmulateReturn$$emulateReturn(Block proc2, Option<DataType> returnType) {
        return this.freshName("early_return").flatMap((Function1<SyntheticName, Transformation> & Serializable & scala.Serializable)label -> this.freshName("result").flatMap((Function1<SyntheticName, Transformation> & Serializable & scala.Serializable)result2 -> proc2.transformUp(new scala.Serializable(this, (SyntheticName)result2, returnType, (SyntheticName)label){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ EmulateReturn $outer;
            private final SyntheticName result$1;
            private final Option returnType$1;
            private final SyntheticName label$1;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Return) {
                    Return return_ = (Return)A1;
                    if (return_.value().isDefined()) {
                        return (B1)this.$outer.ok(new Statements((Seq<Statement>)new $colon$colon<Nothing$>((Nothing$)((Object)new SetVariable(new ProcedureScalarVariable(this.result$1, this.returnType$1, return_.value()))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Leave(new Some<LocalName>(this.label$1))), Nil$.MODULE$))));
                    }
                    return (B1)this.$outer.ok(new Leave(new Some<LocalName>(this.label$1)));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                LogicalPlan logicalPlan2 = x1;
                return logicalPlan2 instanceof Return;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.result$1 = result$1;
                this.returnType$1 = returnType$1;
                this.label$1 = label$1;
            }
        }).map((Function1<LogicalPlan, Block> & Serializable & scala.Serializable)x0$1 -> {
            LogicalPlan logicalPlan2 = x0$1;
            if (logicalPlan2 instanceof Block) {
                Block block;
                Block block2 = block = (Block)logicalPlan2;
                Block block3 = proc2;
                if (!(block2 != null ? !((Object)block2).equals(block3) : block3 != null)) {
                    return proc2;
                }
                return (Block)CurrentOrigin$.MODULE$.withOrigin(proc2.origin(), (Function0<Block> & Serializable & scala.Serializable)() -> new Block(proc2.declaredVariables().$colon$plus(new SetVariable(new ProcedureScalarVariable((LocalName)result2, returnType, ProcedureScalarVariable$.MODULE$.apply$default$3())), Seq$.MODULE$.canBuildFrom()), (Seq<LogicalPlan>)new $colon$colon<Nothing$>((Nothing$)((Object)new Block(Nil$.MODULE$, block.body(), new Some<LocalName>((LocalName)label))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Values((Seq<Seq<Expression>>)new $colon$colon<Nothing$>((Nothing$)((Object)new $colon$colon<Nothing$>((Nothing$)((Object)result2), Nil$.MODULE$)), Nil$.MODULE$))), Nil$.MODULE$)), Block$.MODULE$.apply$default$3()));
            }
            throw new MatchError(logicalPlan2);
        })));
    }

    public EmulateReturn() {
        TransformationConstructors.$init$(this);
        SyntheticNames.$init$(this);
    }
}

