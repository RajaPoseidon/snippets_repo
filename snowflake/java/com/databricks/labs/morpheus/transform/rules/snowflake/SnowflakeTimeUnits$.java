/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.errors.UnsupportedDateTimePart;
import com.databricks.labs.morpheus.errors.UnsupportedDateTimePart$;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.Fn;
import com.databricks.labs.morpheus.intermediate.IRHelpers;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.StringLiteral$;
import com.databricks.labs.morpheus.statistics.TranspileStatistics;
import com.databricks.labs.morpheus.transform.PartialResult;
import com.databricks.labs.morpheus.transform.PartialResult$;
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
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Tuple2;
import scala.collection.SetLike;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;

public final class SnowflakeTimeUnits$
implements IRHelpers,
TransformationConstructors {
    public static SnowflakeTimeUnits$ MODULE$;
    private final Map<Set<String>, String> dateOrTimeParts;

    static {
        new SnowflakeTimeUnits$();
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

    private Option<String> findDateOrTimePart(String part) {
        return this.dateOrTimeParts.find((Function1<Tuple2, Object> & Serializable & scala.Serializable)x$1 -> BoxesRunTime.boxToBoolean(SnowflakeTimeUnits$.$anonfun$findDateOrTimePart$1(part, x$1))).map((Function1<Tuple2, String> & Serializable & scala.Serializable)x$2 -> (String)x$2._2());
    }

    public Transformation<String> translateDateOrTimePart(Expression input) {
        String part;
        Id id;
        String part2;
        Expression expression2 = input;
        if (expression2 instanceof Id && this.findDateOrTimePart(part2 = (id = (Id)expression2).id()).nonEmpty()) {
            return this.ok(this.findDateOrTimePart(part2).get());
        }
        Option<String> option = StringLiteral$.MODULE$.unapply(expression2);
        if (!option.isEmpty() && this.findDateOrTimePart(part = option.get()).nonEmpty()) {
            return this.ok(this.findDateOrTimePart(part).get());
        }
        return this.lift(new PartialResult<String>("???", new UnsupportedDateTimePart(expression2, UnsupportedDateTimePart$.MODULE$.apply$default$2()), PartialResult$.MODULE$.apply$default$3()));
    }

    public static final /* synthetic */ boolean $anonfun$findDateOrTimePart$1(String part$1, Tuple2 x$1) {
        return ((SetLike)x$1._1()).contains(part$1.toUpperCase());
    }

    private SnowflakeTimeUnits$() {
        MODULE$ = this;
        IRHelpers.$init$(this);
        TransformationConstructors.$init$(this);
        this.dateOrTimeParts = (Map)Predef$.MODULE$.Map().apply(Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[]{Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"YEAR", "Y", "YY", "YYY", "YYYY", "YR", "YEARS", "YRS"}))), "year"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"MONTH", "MM", "MON", "MONS", "MONTHS"}))), "month"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DAY", "D", "DD", "DAYS", "DAYOFMONTH"}))), "day"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DAYOFWEEK", "WEEKDAY", "DOW", "DW"}))), "dayofweek"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DAYOFWEEKISO", "WEEKDAY_ISO", "DOW_ISO", "DW_ISO"}))), "dayofweekiso"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DAYOFYEAR", "YEARDAY", "DOY", "DY"}))), "dayofyear"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"WEEK", "W", "WK", "WEEKOFYEAR", "WOY", "WY"}))), "week"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"WEEKISO", "WEEK_ISO", "WEEKOFYEARISO", "WEEKOFYEAR_ISO"}))), "weekiso"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"QUARTER", "Q", "QTR", "QTRS", "QUARTERS"}))), "quarter"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"YEAROFWEEK"}))), "yearofweek"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"YEAROFWEEKISO"}))), "yearofweekiso"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"HOUR", "H", "HH", "HR", "HOURS", "HRS"}))), "hour"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"MINUTE", "M", "MI", "MIN", "MINUTES", "MINS"}))), "minute"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"SECOND", "S", "SEC", "SECONDS", "SECS"}))), "second"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"MILLISECOND", "MS", "MSEC", "MILLISECONDS"}))), "millisecond"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"MICROSECOND", "US", "USEC", "MICROSECONDS"}))), "microsecond"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"NANOSECOND", "NS", "NSEC", "NANOSEC", "NSECOND", "NANOSECONDS", "NANOSECS", "NSECONDS"}))), "nanosecond"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"EPOCH_SECOND", "EPOCH", "EPOCH_SECONDS"}))), "epoch_second"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"EPOCH_MILLISECOND", "EPOCH_MILLISECONDS"}))), "epoch_millisecond"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"EPOCH_MICROSECOND", "EPOCH_MICROSECONDS"}))), "epoch_microsecond"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"EPOCH_NANOSECOND", "EPOCH_NANOSECONDS"}))), "epoch_nanosecond"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"TIMEZONE_HOUR", "TZH"}))), "timezone_hour"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc(Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"TIMEZONE_MINUTE", "TZM"}))), "timezone_minute")}));
    }
}

