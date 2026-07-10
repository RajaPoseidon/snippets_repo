/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.BooleanLiteral$;
import com.databricks.labs.morpheus.intermediate.CallFunction;
import com.databricks.labs.morpheus.intermediate.Dot;
import com.databricks.labs.morpheus.intermediate.Explode;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Fn;
import com.databricks.labs.morpheus.intermediate.IRHelpers;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.Id$;
import com.databricks.labs.morpheus.intermediate.Join;
import com.databricks.labs.morpheus.intermediate.JoinDataType;
import com.databricks.labs.morpheus.intermediate.JoinType;
import com.databricks.labs.morpheus.intermediate.Lateral;
import com.databricks.labs.morpheus.intermediate.Lateral$;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.NamedArgumentExpression;
import com.databricks.labs.morpheus.intermediate.PosExplode;
import com.databricks.labs.morpheus.intermediate.Rule;
import com.databricks.labs.morpheus.intermediate.SubqueryAlias;
import com.databricks.labs.morpheus.intermediate.TableFunction;
import com.databricks.labs.morpheus.intermediate.VariantExplode;
import com.databricks.labs.morpheus.intermediate.VariantExplodeOuter;
import com.databricks.labs.morpheus.intermediate.VariantType$;
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
import java.lang.invoke.LambdaMetafactory;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Some;
import scala.Tuple3;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.TraversableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001!4Aa\u0002\u0005\u0001/!)\u0001\u0006\u0001C\u0001S!1A\u0006\u0001Q\u0001\n5BQa\u0010\u0001\u0005B\u0001CQA\u0012\u0001\u0005\n\u001dCQ!\u0014\u0001\u0005\n9CQA\u0015\u0001\u0005\nM\u00131D\u00127biR,g\u000eT1uKJ\fGNV5foR{W\t\u001f9m_\u0012,'BA\u0005\u000b\u0003%\u0019hn\\<gY\u0006\\WM\u0003\u0002\f\u0019\u0005)!/\u001e7fg*\u0011QBD\u0001\niJ\fgn\u001d4pe6T!a\u0004\t\u0002\u00115|'\u000f\u001d5fkNT!!\u0005\n\u0002\t1\f'm\u001d\u0006\u0003'Q\t!\u0002Z1uC\n\u0014\u0018nY6t\u0015\u0005)\u0012aA2p[\u000e\u00011\u0003\u0002\u0001\u0019C\u0011\u00022!\u0007\u000f\u001f\u001b\u0005Q\"BA\u000e\u000f\u00031Ig\u000e^3s[\u0016$\u0017.\u0019;f\u0013\ti\"D\u0001\u0003Sk2,\u0007CA\r \u0013\t\u0001#DA\u0006M_\u001eL7-\u00197QY\u0006t\u0007CA\r#\u0013\t\u0019#DA\u0005J%\"+G\u000e]3sgB\u0011QEJ\u0007\u0002\u0019%\u0011q\u0005\u0004\u0002\u001b)J\fgn\u001d4pe6\fG/[8o\u0007>t7\u000f\u001e:vGR|'o]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003)\u0002\"a\u000b\u0001\u000e\u0003!\taC\u0012'B)R+ejX(V)B+FkX\"P\u0019Vkej\u0015\t\u0004]U:T\"A\u0018\u000b\u0005A\n\u0014!C5n[V$\u0018M\u00197f\u0015\t\u00114'\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001N\u0001\u0006g\u000e\fG.Y\u0005\u0003m=\u00121aU3u!\tAT(D\u0001:\u0015\tQ4(\u0001\u0003mC:<'\"\u0001\u001f\u0002\t)\fg/Y\u0005\u0003}e\u0012aa\u0015;sS:<\u0017!B1qa2LHCA!E!\r)#IH\u0005\u0003\u00072\u0011a\u0002\u0016:b]N4wN]7bi&|g\u000eC\u0003F\u0007\u0001\u0007a$\u0001\u0003qY\u0006t\u0017\u0001E5t\u0019\u0006$XM]1m\r2\fG\u000f^3o)\tAE\n\u0005\u0002J\u00156\t1'\u0003\u0002Lg\t9!i\\8mK\u0006t\u0007\"B#\u0005\u0001\u0004q\u0012a\u0005;sC:\u001cH.\u0019;f!>\u001cX\t\u001f9m_\u0012,Gc\u0001\u0010P!\")Q)\u0002a\u0001=!)\u0011+\u0002a\u0001=\u0005qA.\u0019;fe\u0006dg\t\\1ui\u0016t\u0017aB4fi\u001ac\u0017m\u001a\u000b\u0004\u0011R3\u0007\"B+\u0007\u0001\u00041\u0016!\u00028b[\u0016$\u0007\u0003B,_C\u000et!\u0001\u0017/\u0011\u0005e\u001bT\"\u0001.\u000b\u0005m3\u0012A\u0002\u001fs_>$h(\u0003\u0002^g\u00051\u0001K]3eK\u001aL!a\u00181\u0003\u00075\u000b\u0007O\u0003\u0002^gA\u0011qKY\u0005\u0003}\u0001\u0004\"!\u00073\n\u0005\u0015T\"AC#yaJ,7o]5p]\")qM\u0002a\u0001C\u0006Aa\r\\1h\u001d\u0006lW\r")
public class FlattenLateralViewToExplode
extends Rule<LogicalPlan>
implements IRHelpers,
TransformationConstructors {
    private final Set<String> FLATTEN_OUTPUT_COLUMNS;

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
        return plan.transform(new scala.Serializable(this, plan){
            public static final long serialVersionUID = 0L;
            private final /* synthetic */ FlattenLateralViewToExplode $outer;
            private final LogicalPlan plan$1;

            public final <A1 extends LogicalPlan, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                boolean bl = false;
                Join join = null;
                A1 A1 = x1;
                if (A1 instanceof Join) {
                    bl = true;
                    join = (Join)A1;
                    if (this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$isLateralFlatten(join.left())) {
                        return (B1)this.$outer.ok(join.copy(this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$translatePosExplode(this.plan$1, join.left()), join.copy$default$2(), join.copy$default$3(), join.copy$default$4(), join.copy$default$5(), join.copy$default$6()));
                    }
                }
                if (bl && this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$isLateralFlatten(join.right())) {
                    LogicalPlan x$1 = this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$translatePosExplode(this.plan$1, join.right());
                    LogicalPlan x$2 = join.copy$default$1();
                    Option<Expression> x$3 = join.copy$default$3();
                    JoinType x$4 = join.copy$default$4();
                    Seq<String> x$5 = join.copy$default$5();
                    JoinDataType x$6 = join.copy$default$6();
                    return (B1)this.$outer.ok(join.copy(x$2, x$1, x$3, x$4, x$5, x$6));
                }
                if (this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$isLateralFlatten(A1)) {
                    return (B1)this.$outer.ok(this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$translatePosExplode(this.plan$1, A1));
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(LogicalPlan x1) {
                boolean bl = false;
                Join join = null;
                LogicalPlan logicalPlan2 = x1;
                if (logicalPlan2 instanceof Join) {
                    bl = true;
                    join = (Join)logicalPlan2;
                    if (this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$isLateralFlatten(join.left())) {
                        return true;
                    }
                }
                if (bl && this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$isLateralFlatten(join.right())) {
                    return true;
                }
                return this.$outer.com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$isLateralFlatten(logicalPlan2);
            }
            {
                if ($outer == null) {
                    throw null;
                }
                this.$outer = $outer;
                this.plan$1 = plan$1;
            }
        });
    }

    public boolean com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$isLateralFlatten(LogicalPlan plan) {
        CallFunction callFunction;
        String string;
        TableFunction tableFunction;
        Expression expression2;
        Lateral lateral;
        LogicalPlan logicalPlan2;
        SubqueryAlias subqueryAlias;
        LogicalPlan logicalPlan3;
        LogicalPlan logicalPlan4 = plan;
        return logicalPlan4 instanceof SubqueryAlias && (logicalPlan3 = (subqueryAlias = (SubqueryAlias)logicalPlan4).child()) instanceof Lateral && (logicalPlan2 = (lateral = (Lateral)logicalPlan3).expr()) instanceof TableFunction && (expression2 = (tableFunction = (TableFunction)logicalPlan2).functionCall()) instanceof CallFunction && "FLATTEN".equals(string = (callFunction = (CallFunction)expression2).function_name());
    }

    /*
     * Unable to fully structure code
     */
    public LogicalPlan com$databricks$labs$morpheus$transform$rules$snowflake$FlattenLateralViewToExplode$$translatePosExplode(LogicalPlan plan, LogicalPlan lateralFlatten) {
        block7: {
            block6: {
                var6_3 = lateralFlatten;
                if (!(var6_3 instanceof SubqueryAlias)) break block6;
                var7_4 = (SubqueryAlias)var6_3;
                var8_5 = var7_4.child();
                id = var7_4.alias();
                colNames = var7_4.columnNames();
                if (var8_5 instanceof Lateral && (var12_9 = (var11_8 = (Lateral)var8_5).expr()) instanceof TableFunction && (var14_11 = (var13_10 = (TableFunction)var12_9).functionCall()) instanceof CallFunction) break block7;
            }
            throw new MatchError(var6_3);
        }
        var15_12 = (CallFunction)var14_11;
        args = var15_12.arguments();
        var5_14 = new Tuple3<Seq<Expression>, Expression, Seq<Id>>(args, id, colNames);
        args = var5_14._1();
        id = var5_14._2();
        colNames = var5_14._3();
        named = ((TraversableOnce)args.collect(new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                if (A1 instanceof NamedArgumentExpression) {
                    NamedArgumentExpression namedArgumentExpression = (NamedArgumentExpression)A1;
                    String key = namedArgumentExpression.key();
                    Expression value = namedArgumentExpression.value();
                    return (B1)Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(key.toUpperCase()), value);
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                return expression2 instanceof NamedArgumentExpression;
            }
        }, Seq$.MODULE$.canBuildFrom())).toMap(Predef$.MODULE$.$conforms());
        exprs = plan.expressions();
        flattenOutputReferencedColumns = (Set)this.FLATTEN_OUTPUT_COLUMNS.filter((Function1<String, Object> & Serializable & scala.Serializable)LambdaMetafactory.altMetafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, $anonfun$translatePosExplode$1$adapted(scala.collection.Seq com.databricks.labs.morpheus.intermediate.Expression java.lang.String ), (Ljava/lang/String;)Ljava/lang/Object;)(exprs, (Expression)id));
        input = (Expression)named.apply("INPUT");
        outer = this.getFlag(named, "OUTER");
        if (flattenOutputReferencedColumns.contains("index")) {
            return new SubqueryAlias(new Lateral(new TableFunction(new PosExplode(input)), outer, true), id, flattenOutputReferencedColumns.toSeq().map((Function1<String, Id> & Serializable & scala.Serializable)LambdaMetafactory.altMetafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, $anonfun$translatePosExplode$4(java.lang.String ), (Ljava/lang/String;)Lcom/databricks/labs/morpheus/intermediate/Id;)(), Seq$.MODULE$.canBuildFrom()));
        }
        var26_23 = false;
        var27_24 = null;
        var28_25 = input.dataType();
        if (!VariantType$.MODULE$.equals(var28_25)) ** GOTO lbl-1000
        var26_23 = true;
        var27_24 = var28_25;
        if (outer) {
            v0 = new Lateral(new TableFunction(new VariantExplodeOuter(input)), false, Lateral$.MODULE$.apply$default$3());
        } else lbl-1000:
        // 2 sources

        {
            v0 = var26_23 != false ? new Lateral(new TableFunction(new VariantExplode(input)), false, Lateral$.MODULE$.apply$default$3()) : new Lateral(new TableFunction(new Explode(input)), outer, Lateral$.MODULE$.apply$default$3());
        }
        translated = v0;
        return new SubqueryAlias(translated, id, colNames);
    }

    private boolean getFlag(Map<String, Expression> named, String flagName) {
        Some some;
        Expression expression2;
        Option<Object> option;
        Option option2 = named.get(flagName);
        if (option2 instanceof Some && !(option = BooleanLiteral$.MODULE$.unapply(expression2 = (Expression)(some = (Some)option2).value())).isEmpty()) {
            boolean value = BoxesRunTime.unboxToBoolean(option.get());
            return value;
        }
        return false;
    }

    public static final /* synthetic */ boolean $anonfun$translatePosExplode$3(Expression id$1, String col$1, Expression x0$1) {
        Expression expression2 = x0$1;
        if (expression2 instanceof Dot) {
            Dot dot = (Dot)expression2;
            Expression x = dot.left();
            Expression expression3 = dot.right();
            if (expression3 instanceof Id) {
                Id id = (Id)expression3;
                String c = id.id();
                boolean bl = id.isQuoted();
                if (!bl) {
                    Expression expression4 = x;
                    Expression expression5 = id$1;
                    return !(expression4 != null ? !expression4.equals(expression5) : expression5 != null) && c.equalsIgnoreCase(col$1);
                }
            }
        }
        return false;
    }

    public static final /* synthetic */ boolean $anonfun$translatePosExplode$2(Expression id$1, String col$1, Expression x$2) {
        return x$2.find((Function1<Expression, Object> & Serializable & scala.Serializable)x0$1 -> BoxesRunTime.boxToBoolean(FlattenLateralViewToExplode.$anonfun$translatePosExplode$3(id$1, col$1, x0$1))).isDefined();
    }

    public static final /* synthetic */ Id $anonfun$translatePosExplode$4(String x$3) {
        return new Id(x$3, Id$.MODULE$.apply$default$2());
    }

    public FlattenLateralViewToExplode() {
        IRHelpers.$init$(this);
        TransformationConstructors.$init$(this);
        this.FLATTEN_OUTPUT_COLUMNS = (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"seq", "key", "path", "index", "value", "this"}));
    }

    public static final /* synthetic */ Object $anonfun$translatePosExplode$1$adapted(Seq exprs$1, Expression id$1, String col) {
        return BoxesRunTime.boxToBoolean(exprs$1.exists((Function1<Expression, Object> & Serializable & scala.Serializable)x$2 -> BoxesRunTime.boxToBoolean(FlattenLateralViewToExplode.$anonfun$translatePosExplode$2(id$1, col, x$2))));
    }
}

