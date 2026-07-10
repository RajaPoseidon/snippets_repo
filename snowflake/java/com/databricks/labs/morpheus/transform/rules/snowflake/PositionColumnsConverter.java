/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Alias;
import com.databricks.labs.morpheus.intermediate.Cast;
import com.databricks.labs.morpheus.intermediate.Cast$;
import com.databricks.labs.morpheus.intermediate.CurrentOrigin$;
import com.databricks.labs.morpheus.intermediate.DataType;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Filter;
import com.databricks.labs.morpheus.intermediate.Fn;
import com.databricks.labs.morpheus.intermediate.IRHelpers;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.Id$;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.Position;
import com.databricks.labs.morpheus.intermediate.Project;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.ScalarSubquery;
import com.databricks.labs.morpheus.intermediate.SyntheticName;
import com.databricks.labs.morpheus.intermediate.TableAlias;
import com.databricks.labs.morpheus.intermediate.Values;
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
import scala.Function2;
import scala.MatchError;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Tuple2;
import scala.collection.GenMap;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.IndexedSeq$;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.math.Ordering$Int$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LambdaDeserialize;
import scala.runtime.Nothing$;
import scala.runtime.RichInt$;

@ScalaSignature(bytes="\u0006\u0001i4A\u0001C\u0005\u00011!)q\u0006\u0001C\u0001a!)1\u0007\u0001C!i!1!\b\u0001Q\u0005\nmBa!\u0010\u0001!\n\u0013q\u0004BB#\u0001A\u0013%a\t\u0003\u0004l\u0001\u0001&I\u0001\u001c\u0005\u0007k\u0002\u0001K\u0011\u0002<\u00031A{7/\u001b;j_:\u001cu\u000e\\;n]N\u001cuN\u001c<feR,'O\u0003\u0002\u000b\u0017\u0005I1O\\8xM2\f7.\u001a\u0006\u0003\u00195\tQA];mKNT!AD\b\u0002\u0013Q\u0014\u0018M\\:g_Jl'B\u0001\t\u0012\u0003!iwN\u001d9iKV\u001c(B\u0001\n\u0014\u0003\u0011a\u0017MY:\u000b\u0005Q)\u0012A\u00033bi\u0006\u0014'/[2lg*\ta#A\u0002d_6\u001c\u0001aE\u0003\u00013\t*\u0013\u0006E\u0002\u001b;}i\u0011a\u0007\u0006\u00039=\tA\"\u001b8uKJlW\rZ5bi\u0016L!AH\u000e\u0003\tI+H.\u001a\t\u00035\u0001J!!I\u000e\u0003\u00171{w-[2bYBc\u0017M\u001c\t\u00035\rJ!\u0001J\u000e\u0003\u0013%\u0013\u0006*\u001a7qKJ\u001c\bC\u0001\u0014(\u001b\u0005i\u0011B\u0001\u0015\u000e\u0005i!&/\u00198tM>\u0014X.\u0019;j_:\u001cuN\\:ueV\u001cGo\u001c:t!\tQS&D\u0001,\u0015\tas\"A\u0004qCJ\u001cXM]:\n\u00059Z#AD*z]RDW\r^5d\u001d\u0006lWm]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003E\u0002\"A\r\u0001\u000e\u0003%\tQ!\u00199qYf$\"!\u000e\u001d\u0011\u0007\u00192t$\u0003\u00028\u001b\tqAK]1og\u001a|'/\\1uS>t\u0007\"B\u001d\u0003\u0001\u0004y\u0012\u0001\u00029mC:\fQ\u0002\u001e:b]N4wN]7QY\u0006tGCA\u001b=\u0011\u0015I4\u00011\u0001 \u0003A\u0019wN\u001c<feR\u0004vn]5uS>t7\u000f\u0006\u0002@\u0007B\u0019aE\u000e!\u0011\u0005i\t\u0015B\u0001\"\u001c\u0005\u001d\u0001&o\u001c6fGRDQ\u0001\u0012\u0003A\u0002\u0001\u000bq\u0001\u001d:pU\u0016\u001cG/\u0001\u0011d_2dWm\u0019;B]\u0012\u001cuN\u001c<feR\u0004vn]5uS>t7i\u001c7v[:\u001cHCA$k!\u0011A5*\u00140\u000e\u0003%S\u0011AS\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0019&\u0013a\u0001V;qY\u0016\u0014\u0004\u0003\u0002(V1ns!aT*\u0011\u0005AKU\"A)\u000b\u0005I;\u0012A\u0002\u001fs_>$h(\u0003\u0002U\u0013\u00061\u0001K]3eK\u001aL!AV,\u0003\u00075\u000b\u0007O\u0003\u0002U\u0013B\u0011\u0001*W\u0005\u00035&\u00131!\u00138u!\tQB,\u0003\u0002^7\t\u0011\u0011\n\u001a\t\u0004?\u0012<gB\u00011c\u001d\t\u0001\u0016-C\u0001K\u0013\t\u0019\u0017*A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00154'aA*fc*\u00111-\u0013\t\u00035!L!![\u000e\u0003\u0015\u0015C\bO]3tg&|g\u000eC\u0003E\u000b\u0001\u0007\u0001)A\u0007d_:4XM\u001d;WC2,Xm\u001d\u000b\u0006\u007f5t\u0007o\u001d\u0005\u0006\t\u001a\u0001\r\u0001\u0011\u0005\u0006_\u001a\u0001\raZ\u0001\u000bC2L\u0017m]0oC6,\u0007\"B9\u0007\u0001\u0004\u0011\u0018AC1mS\u0006\u001cxlY8mgB\u0019q\fZ.\t\u000bQ4\u0001\u0019\u00010\u0002!\u0011,\u0017\r\\5bg\u0016$7i\u001c7v[:\u001c\u0018!\u0005;sC:\u001chm\u001c:n'V\u0014\u0017/^3ssR\u0011q\u000f\u001f\t\u0004MY:\u0007\"B=\b\u0001\u00049\u0017aA3ya\u0002")
public class PositionColumnsConverter
extends Rule<LogicalPlan>
implements IRHelpers,
TransformationConstructors,
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
        return this.com$databricks$labs$morpheus$transform$rules$snowflake$PositionColumnsConverter$$transformPlan(plan);
    }

    public Transformation<LogicalPlan> com$databricks$labs$morpheus$transform$rules$snowflake$PositionColumnsConverter$$transformPlan(LogicalPlan plan) {
        return plan.transform(new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ PositionColumnsConverter $outer;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Project) {
                    Project project = (Project)A1;
                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$PositionColumnsConverter$$convertPositions(project);
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

    public Transformation<Project> com$databricks$labs$morpheus$transform$rules$snowflake$PositionColumnsConverter$$convertPositions(Project project) {
        Tuple2<Map<Object, Id>, Seq<Expression>> tuple2 = this.collectAndConvertPositionColumns(project);
        if (tuple2 == null) {
            throw new MatchError(tuple2);
        }
        Map<Object, Id> aliases = tuple2._1();
        Seq<Expression> columns = tuple2._2();
        Tuple2<Map<Object, Id>, Seq<Expression>> tuple22 = new Tuple2<Map<Object, Id>, Seq<Expression>>(aliases, columns);
        Map<Object, Id> aliases2 = tuple22._1();
        Seq<Expression> columns2 = tuple22._2();
        if (aliases2.isEmpty()) {
            return this.ok(project);
        }
        return this.freshName("__table__").flatMap((Function1<SyntheticName, Transformation> & Serializable & scala.Serializable)aliasName -> {
            IndexedSeq<Id> aliasCols = RichInt$.MODULE$.to$extension0(Predef$.MODULE$.intWrapper(1), BoxesRunTime.unboxToInt(aliases2.keys().max(Ordering$Int$.MODULE$))).map((Function1<Object, Id> & Serializable & scala.Serializable)index -> PositionColumnsConverter.$anonfun$convertPositions$2(aliases2, BoxesRunTime.unboxToInt(index)), IndexedSeq$.MODULE$.canBuildFrom());
            return this.convertValues(project, (Expression)aliasName, (Seq<Id>)aliasCols, columns2);
        });
    }

    private Tuple2<Map<Object, Id>, Seq<Expression>> collectAndConvertPositionColumns(Project project) {
        return project.columns().foldLeft(new Tuple2<GenMap, Nil$>(Predef$.MODULE$.Map().empty(), Nil$.MODULE$), (Function2<Tuple2, Expression, Tuple2> & Serializable & scala.Serializable)(x0$1, x1$1) -> {
            Tuple2<Tuple2, Expression> tuple2 = new Tuple2<Tuple2, Expression>((Tuple2)x0$1, (Expression)x1$1);
            if (tuple2 != null) {
                Tuple2 current = tuple2._1();
                Expression expression2 = tuple2._2();
                if (expression2 instanceof Alias) {
                    Alias alias = (Alias)expression2;
                    Expression expression3 = alias.expr();
                    Expression name = alias.name();
                    if (expression3 instanceof Cast) {
                        Cast cast = (Cast)expression3;
                        Expression pos = cast.expr();
                        DataType dataType = cast.dataType();
                        if (pos instanceof Position) {
                            Position position = (Position)pos;
                            if (name instanceof Id) {
                                Id id = (Id)name;
                                Map<Integer, Id> aliases = ((Map)current._1()).$plus(Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(position.index())), id));
                                Alias transformed = new Alias(new Cast(id, dataType, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5()), id);
                                Seq columns = ((SeqLike)current._2()).$colon$plus(transformed, Seq$.MODULE$.canBuildFrom());
                                return new Tuple2(aliases, columns);
                            }
                        }
                    }
                }
            }
            if (tuple2 != null) {
                Tuple2 current = tuple2._1();
                return current;
            }
            throw new MatchError(tuple2);
        });
    }

    private Transformation<Project> convertValues(Project project, Expression alias_name, Seq<Id> alias_cols, Seq<Expression> dealiasedColumns) {
        return project.input().transformUp(new scala.Serializable(this, alias_name, alias_cols){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ PositionColumnsConverter $outer;
            private final Expression alias_name$1;
            private final Seq alias_cols$1;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof Filter) {
                    Filter filter2 = (Filter)A1;
                    LogicalPlan in = filter2.input();
                    Expression cond = filter2.condition();
                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$PositionColumnsConverter$$transformSubquery(cond).map((Function1<Expression, Filter> & Serializable & scala.Serializable)transformedCondition -> (Filter)CurrentOrigin$.MODULE$.withOrigin(filter2.origin(), (Function0<Filter> & Serializable & scala.Serializable)() -> new Filter(in, (Expression)transformedCondition)));
                }
                if (A1 instanceof Values) {
                    Values values = (Values)A1;
                    return (B1)this.$outer.ok(CurrentOrigin$.MODULE$.withOrigin(values.origin(), (Function0<TableAlias> & Serializable & scala.Serializable)() -> new TableAlias(values, $this.alias_name$1, $this.alias_cols$1)));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                LogicalPlan logicalPlan2 = x1;
                if (logicalPlan2 instanceof Filter) {
                    return true;
                }
                return logicalPlan2 instanceof Values;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.alias_name$1 = alias_name$1;
                this.alias_cols$1 = alias_cols$1;
            }

            private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$1(com.databricks.labs.morpheus.intermediate.Filter com.databricks.labs.morpheus.intermediate.LogicalPlan com.databricks.labs.morpheus.intermediate.Expression ), $anonfun$applyOrElse$3(com.databricks.labs.morpheus.transform.rules.snowflake.PositionColumnsConverter$$anonfun$convertValues$1 com.databricks.labs.morpheus.intermediate.Values ), $anonfun$applyOrElse$2(com.databricks.labs.morpheus.intermediate.LogicalPlan com.databricks.labs.morpheus.intermediate.Expression )}, serializedLambda);
            }
        }).map((Function1<LogicalPlan, Project> & Serializable & scala.Serializable)aliasedInput -> (Project)CurrentOrigin$.MODULE$.withOrigin(project.origin(), (Function0<Project> & Serializable & scala.Serializable)() -> new Project((LogicalPlan)aliasedInput, dealiasedColumns)));
    }

    public Transformation<Expression> com$databricks$labs$morpheus$transform$rules$snowflake$PositionColumnsConverter$$transformSubquery(Expression exp) {
        return exp.transformUp(new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ PositionColumnsConverter $outer;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof ScalarSubquery) {
                    ScalarSubquery scalarSubquery = (ScalarSubquery)A1;
                    return (B1)this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$PositionColumnsConverter$$transformPlan(scalarSubquery.plan()).map((Function1<LogicalPlan, ScalarSubquery> & Serializable & scala.Serializable)plan -> (ScalarSubquery)CurrentOrigin$.MODULE$.withOrigin(scalarSubquery.origin(), (Function0<ScalarSubquery> & Serializable & scala.Serializable)() -> new ScalarSubquery((LogicalPlan)plan)));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                return expression2 instanceof ScalarSubquery;
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }

            private static /* synthetic */ Object $deserializeLambda$(SerializedLambda serializedLambda) {
                return LambdaDeserialize.bootstrap("lambdaDeserialize", new MethodHandle[]{$anonfun$applyOrElse$4(com.databricks.labs.morpheus.intermediate.ScalarSubquery com.databricks.labs.morpheus.intermediate.LogicalPlan ), $anonfun$applyOrElse$5(com.databricks.labs.morpheus.intermediate.LogicalPlan )}, serializedLambda);
            }
        });
    }

    public static final /* synthetic */ Id $anonfun$convertPositions$2(Map aliases$1, int index) {
        return (Id)aliases$1.getOrElse(BoxesRunTime.boxToInteger(index), (Function0<Id> & Serializable & scala.Serializable)() -> new Id(new StringBuilder(1).append("_").append(index).toString(), Id$.MODULE$.apply$default$2()));
    }

    public PositionColumnsConverter() {
        IRHelpers.$init$(this);
        TransformationConstructors.$init$(this);
        SyntheticNames.$init$(this);
    }
}

