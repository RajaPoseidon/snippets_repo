/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.MorpheusError;
import com.databricks.labs.morpheus.generators.GeneratorContext;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.statistics.TranspileStatistics;
import com.databricks.labs.morpheus.transform.Phase;
import com.databricks.labs.morpheus.transform.Result;
import com.databricks.labs.morpheus.transform.Transformation;
import com.databricks.labs.morpheus.transform.TranspilerConfig;
import com.databricks.labs.morpheus.transform.TranspilerState;
import com.databricks.labs.morpheus.transform.WorkflowStage;
import com.databricks.labs.morpheus.transform.rules.snowflake.SnowflakeTimeUnits$;
import com.fasterxml.jackson.annotation.JsonIgnore;
import scala.Function1;
import scala.PartialFunction;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Nothing$;

@ScalaSignature(bytes="\u0006\u0001m;QAB\u0004\t\u0002Y1Q\u0001G\u0004\t\u0002eAQAK\u0001\u0005\u0002-Ba\u0001L\u0001!\u0002\u0013i\u0003\"\u0002!\u0002\t\u0013\t\u0005\"B)\u0002\t\u0003\u0011\u0016AE*o_^4G.Y6f)&lW-\u00168jiNT!\u0001C\u0005\u0002\u0013Mtwn\u001e4mC.,'B\u0001\u0006\f\u0003\u0015\u0011X\u000f\\3t\u0015\taQ\"A\u0005ue\u0006t7OZ8s[*\u0011abD\u0001\t[>\u0014\b\u000f[3vg*\u0011\u0001#E\u0001\u0005Y\u0006\u00147O\u0003\u0002\u0013'\u0005QA-\u0019;bEJL7m[:\u000b\u0003Q\t1aY8n\u0007\u0001\u0001\"aF\u0001\u000e\u0003\u001d\u0011!c\u00158po\u001ad\u0017m[3US6,WK\\5ugN!\u0011A\u0007\u0011'!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005\u0019\te.\u001f*fMB\u0011\u0011\u0005J\u0007\u0002E)\u00111%D\u0001\rS:$XM]7fI&\fG/Z\u0005\u0003K\t\u0012\u0011\"\u0013*IK2\u0004XM]:\u0011\u0005\u001dBS\"A\u0006\n\u0005%Z!A\u0007+sC:\u001chm\u001c:nCRLwN\\\"p]N$(/^2u_J\u001c\u0018A\u0002\u001fj]&$h\bF\u0001\u0017\u0003=!\u0017\r^3PeRKW.\u001a)beR\u001c\b\u0003\u0002\u00184kaj\u0011a\f\u0006\u0003aE\n\u0011\"[7nkR\f'\r\\3\u000b\u0005Ib\u0012AC2pY2,7\r^5p]&\u0011Ag\f\u0002\u0004\u001b\u0006\u0004\bc\u0001\u00187q%\u0011qg\f\u0002\u0004'\u0016$\bCA\u001d?\u001b\u0005Q$BA\u001e=\u0003\u0011a\u0017M\\4\u000b\u0003u\nAA[1wC&\u0011qH\u000f\u0002\u0007'R\u0014\u0018N\\4\u0002%\u0019Lg\u000e\u001a#bi\u0016|%\u000fV5nKB\u000b'\u000f\u001e\u000b\u0003\u0005>\u00032aG\"F\u0013\t!ED\u0001\u0004PaRLwN\u001c\t\u0003\r6s!aR&\u0011\u0005!cR\"A%\u000b\u0005)+\u0012A\u0002\u001fs_>$h(\u0003\u0002M9\u00051\u0001K]3eK\u001aL!a\u0010(\u000b\u00051c\u0002\"\u0002)\u0005\u0001\u0004)\u0015\u0001\u00029beR\fq\u0003\u001e:b]Nd\u0017\r^3ECR,wJ\u001d+j[\u0016\u0004\u0016M\u001d;\u0015\u0005M3\u0006cA\u0014U\u000b&\u0011Qk\u0003\u0002\u000f)J\fgn\u001d4pe6\fG/[8o\u0011\u00159V\u00011\u0001Y\u0003\u0015Ig\u000e];u!\t\t\u0013,\u0003\u0002[E\tQQ\t\u001f9sKN\u001c\u0018n\u001c8")
public final class SnowflakeTimeUnits {
    public static Transformation<String> translateDateOrTimePart(Expression expression2) {
        return SnowflakeTimeUnits$.MODULE$.translateDateOrTimePart(expression2);
    }

    public static Transformation<String> print(String string) {
        return SnowflakeTimeUnits$.MODULE$.print(string);
    }

    public static <Out> Transformation<Out> withGenCtx(Function1<GeneratorContext, Transformation<Out>> function1) {
        return SnowflakeTimeUnits$.MODULE$.withGenCtx(function1);
    }

    @JsonIgnore
    public static Transformation<TranspilerState> getState() {
        return SnowflakeTimeUnits$.MODULE$.getState();
    }

    public static <A> Transformation<A> withState(Function1<TranspilerState, Transformation<A>> function1) {
        return SnowflakeTimeUnits$.MODULE$.withState(function1);
    }

    public static Transformation<BoxedUnit> updateStats(Function1<TranspileStatistics, TranspileStatistics> function1) {
        return SnowflakeTimeUnits$.MODULE$.updateStats(function1);
    }

    public static Transformation<BoxedUnit> updatePhase(PartialFunction<Phase, Phase> partialFunction) {
        return SnowflakeTimeUnits$.MODULE$.updatePhase(partialFunction);
    }

    public static Transformation<BoxedUnit> setPhase(Phase phase) {
        return SnowflakeTimeUnits$.MODULE$.setPhase(phase);
    }

    public static <X> Transformation<X> getFromPhase(PartialFunction<Phase, X> partialFunction) {
        return SnowflakeTimeUnits$.MODULE$.getFromPhase(partialFunction);
    }

    @JsonIgnore
    public static Transformation<Phase> getCurrentPhase() {
        return SnowflakeTimeUnits$.MODULE$.getCurrentPhase();
    }

    @JsonIgnore
    public static Transformation<TranspilerConfig> getConfig() {
        return SnowflakeTimeUnits$.MODULE$.getConfig();
    }

    public static <X> Transformation<X> lift(Result<X> result2) {
        return SnowflakeTimeUnits$.MODULE$.lift(result2);
    }

    public static Transformation<Nothing$> ko(WorkflowStage workflowStage, MorpheusError morpheusError) {
        return SnowflakeTimeUnits$.MODULE$.ko(workflowStage, morpheusError);
    }

    public static <A> Transformation<A> ok(A a) {
        return SnowflakeTimeUnits$.MODULE$.ok(a);
    }
}

