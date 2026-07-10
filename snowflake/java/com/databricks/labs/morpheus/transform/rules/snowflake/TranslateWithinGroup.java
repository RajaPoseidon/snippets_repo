/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.ArrayJoin;
import com.databricks.labs.morpheus.intermediate.ArrayJoin$;
import com.databricks.labs.morpheus.intermediate.ArraySort;
import com.databricks.labs.morpheus.intermediate.ArrayTransform;
import com.databricks.labs.morpheus.intermediate.CallFunction;
import com.databricks.labs.morpheus.intermediate.Case;
import com.databricks.labs.morpheus.intermediate.CollectList;
import com.databricks.labs.morpheus.intermediate.CollectList$;
import com.databricks.labs.morpheus.intermediate.CreateNamedStruct;
import com.databricks.labs.morpheus.intermediate.Distinct;
import com.databricks.labs.morpheus.intermediate.Dot;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.GreaterThan;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.Id$;
import com.databricks.labs.morpheus.intermediate.LambdaFunction;
import com.databricks.labs.morpheus.intermediate.LessThan;
import com.databricks.labs.morpheus.intermediate.Literal$;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.SortArray;
import com.databricks.labs.morpheus.intermediate.SortOrder;
import com.databricks.labs.morpheus.intermediate.UnresolvedNamedLambdaVariable;
import com.databricks.labs.morpheus.intermediate.WhenBranch;
import com.databricks.labs.morpheus.intermediate.WithinGroup;
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
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableLike;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001u3AAB\u0004\u0001-!)A\u0005\u0001C\u0001K!)\u0001\u0006\u0001C!S!)q\u0006\u0001C\u0005a!)\u0011\n\u0001C\u0005\u0015\")!\f\u0001C\u00057\n!BK]1og2\fG/Z,ji\"Lgn\u0012:pkBT!\u0001C\u0005\u0002\u0013Mtwn\u001e4mC.,'B\u0001\u0006\f\u0003\u0015\u0011X\u000f\\3t\u0015\taQ\"A\u0005ue\u0006t7OZ8s[*\u0011abD\u0001\t[>\u0014\b\u000f[3vg*\u0011\u0001#E\u0001\u0005Y\u0006\u00147O\u0003\u0002\u0013'\u0005QA-\u0019;bEJL7m[:\u000b\u0003Q\t1aY8n\u0007\u0001\u00192\u0001A\f!!\rA2$H\u0007\u00023)\u0011!$D\u0001\rS:$XM]7fI&\fG/Z\u0005\u00039e\u0011AAU;mKB\u0011\u0001DH\u0005\u0003?e\u00111\u0002T8hS\u000e\fG\u000e\u00157b]B\u0011\u0011EI\u0007\u0002\u0017%\u00111e\u0003\u0002\u001b)J\fgn\u001d4pe6\fG/[8o\u0007>t7\u000f\u001e:vGR|'o]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0019\u0002\"a\n\u0001\u000e\u0003\u001d\tQ!\u00199qYf$\"AK\u0017\u0011\u0007\u0005ZS$\u0003\u0002-\u0017\tqAK]1og\u001a|'/\\1uS>t\u0007\"\u0002\u0018\u0003\u0001\u0004i\u0012\u0001\u00029mC:\f\u0011b]8si\u0006\u0013(/Y=\u0015\u0007E\"d\u0007\u0005\u0002\u0019e%\u00111'\u0007\u0002\u000b\u000bb\u0004(/Z:tS>t\u0007\"B\u001b\u0004\u0001\u0004\t\u0014aA1sO\")qg\u0001a\u0001q\u0005!1o\u001c:u!\rI4I\u0012\b\u0003u\u0001s!a\u000f \u000e\u0003qR!!P\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0005y\u0014!B:dC2\f\u0017BA!C\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011aP\u0005\u0003\t\u0016\u00131aU3r\u0015\t\t%\t\u0005\u0002\u0019\u000f&\u0011\u0001*\u0007\u0002\n'>\u0014Ho\u0014:eKJ\fQb]1nKJ+g-\u001a:f]\u000e,GcA&P#B\u0011A*T\u0007\u0002\u0005&\u0011aJ\u0011\u0002\b\u0005>|G.Z1o\u0011\u0015\u0001F\u00011\u00012\u0003\u0011aWM\u001a;\t\u000bI#\u0001\u0019A\u0019\u0002\u000bILw\r\u001b;)\u0005\u0011!\u0006CA+Y\u001b\u00051&BA,C\u0003)\tgN\\8uCRLwN\\\u0005\u00033Z\u0013q\u0001^1jYJ,7-A\u0007t_J$\u0018N\\4MC6\u0014G-\u0019\u000b\u0003cqCQaN\u0003A\u0002a\u0002")
public class TranslateWithinGroup
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
        return plan.transformAllExpressions((PartialFunction<Expression, Transformation<Expression>>)((Object)new scala.Serializable(this){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ TranslateWithinGroup $outer;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                boolean bl = false;
                WithinGroup withinGroup = null;
                A1 A1 = x1;
                if (A1 instanceof WithinGroup) {
                    bl = true;
                    withinGroup = (WithinGroup)A1;
                    Expression expression2 = withinGroup.expression();
                    Seq<SortOrder> sorts = withinGroup.order();
                    if (expression2 instanceof CallFunction) {
                        CallFunction callFunction = (CallFunction)expression2;
                        String string = callFunction.function_name();
                        Seq<Expression> args = callFunction.arguments();
                        if ("ARRAY_AGG".equals(string)) {
                            return (B1)this.$outer.ok(this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$TranslateWithinGroup$$sortArray((Expression)args.head(), sorts));
                        }
                    }
                }
                if (bl) {
                    Expression expression3 = withinGroup.expression();
                    Seq<SortOrder> sorts = withinGroup.order();
                    if (expression3 instanceof CallFunction) {
                        CallFunction callFunction = (CallFunction)expression3;
                        String string = callFunction.function_name();
                        Seq<Expression> args = callFunction.arguments();
                        if ("LISTAGG".equals(string)) {
                            return (B1)this.$outer.ok(new ArrayJoin(this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$TranslateWithinGroup$$sortArray((Expression)args.head(), sorts), (Expression)args.apply(true), ArrayJoin$.MODULE$.apply$default$3()));
                        }
                    }
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                CallFunction callFunction;
                String string;
                Expression expression2;
                boolean bl = false;
                WithinGroup withinGroup = null;
                Expression expression3 = x1;
                if (expression3 instanceof WithinGroup) {
                    CallFunction callFunction2;
                    String string2;
                    bl = true;
                    withinGroup = (WithinGroup)expression3;
                    Expression expression4 = withinGroup.expression();
                    if (expression4 instanceof CallFunction && "ARRAY_AGG".equals(string2 = (callFunction2 = (CallFunction)expression4).function_name())) {
                        return true;
                    }
                }
                return bl && (expression2 = withinGroup.expression()) instanceof CallFunction && "LISTAGG".equals(string = (callFunction = (CallFunction)expression2).function_name());
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
            }
        }));
    }

    public Expression com$databricks$labs$morpheus$transform$rules$snowflake$TranslateWithinGroup$$sortArray(Expression arg, Seq<SortOrder> sort) {
        if (sort.size() == 1 && this.sameReference(arg, ((SortOrder)sort.head()).expr())) {
            Option sortOrder = ((SortOrder)sort.head()).direction().isAscending() ? None$.MODULE$ : new Some<Expression>(Literal$.MODULE$.apply(BoxesRunTime.boxToBoolean(false)));
            return new SortArray(new CollectList(arg, CollectList$.MODULE$.apply$default$2()), sortOrder);
        }
        CreateNamedStruct namedStructFunc = new CreateNamedStruct(((TraversableLike)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("value")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)arg), Nil$.MODULE$))).$plus$plus(((TraversableLike)sort.zipWithIndex(Seq$.MODULE$.canBuildFrom())).flatMap((Function1<Tuple2, Seq> & Serializable & scala.Serializable)x0$1 -> {
            Tuple2 tuple2 = x0$1;
            if (tuple2 != null) {
                SortOrder s2 = (SortOrder)tuple2._1();
                int index = tuple2._2$mcI$sp();
                return new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply(new StringBuilder(8).append("sort_by_").append(index).toString())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)s2.expr()), Nil$.MODULE$));
            }
            throw new MatchError(tuple2);
        }, Seq$.MODULE$.canBuildFrom()), Seq$.MODULE$.canBuildFrom()));
        return new ArrayTransform(new ArraySort(new CollectList(namedStructFunc, CollectList$.MODULE$.apply$default$2()), new Some<Expression>(this.sortingLambda(sort))), new LambdaFunction(new Dot(new Id("s", Id$.MODULE$.apply$default$2()), new Id("value", Id$.MODULE$.apply$default$2())), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"s"), Nil$.MODULE$))), Nil$.MODULE$)));
    }

    private boolean sameReference(Expression left, Expression right) {
        Expression expression2;
        while ((expression2 = left) instanceof Distinct) {
            Expression e;
            Distinct distinct = (Distinct)expression2;
            left = e = distinct.expression();
        }
        Expression expression3 = expression2;
        Expression expression4 = right;
        return !(expression3 != null ? !expression3.equals(expression4) : expression4 != null);
    }

    private Expression sortingLambda(Seq<SortOrder> sort) {
        return new LambdaFunction(new Case(None$.MODULE$, ((TraversableLike)sort.zipWithIndex(Seq$.MODULE$.canBuildFrom())).flatMap((Function1<Tuple2, Seq> & Serializable & scala.Serializable)x0$1 -> {
            Tuple2 tuple2 = x0$1;
            if (tuple2 != null) {
                SortOrder s2 = (SortOrder)tuple2._1();
                int index = tuple2._2$mcI$sp();
                return new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new LessThan(new Dot(new Id("left", Id$.MODULE$.apply$default$2()), new Id(new StringBuilder(8).append("sort_by_").append(index).toString(), Id$.MODULE$.apply$default$2())), new Dot(new Id("right", Id$.MODULE$.apply$default$2()), new Id(new StringBuilder(8).append("sort_by_").append(index).toString(), Id$.MODULE$.apply$default$2()))), s2.direction().isAscending() ? Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(-1)) : Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(1)))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new GreaterThan(new Dot(new Id("left", Id$.MODULE$.apply$default$2()), new Id(new StringBuilder(8).append("sort_by_").append(index).toString(), Id$.MODULE$.apply$default$2())), new Dot(new Id("right", Id$.MODULE$.apply$default$2()), new Id(new StringBuilder(8).append("sort_by_").append(index).toString(), Id$.MODULE$.apply$default$2()))), s2.direction().isAscending() ? Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(1)) : Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(-1)))), Nil$.MODULE$));
            }
            throw new MatchError(tuple2);
        }, Seq$.MODULE$.canBuildFrom()), new Some<Expression>(Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(0)))), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"left"), Nil$.MODULE$))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"right"), Nil$.MODULE$))), Nil$.MODULE$)));
    }

    public TranslateWithinGroup() {
        TransformationConstructors.$init$(this);
    }
}

