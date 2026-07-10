/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.parsers.snowflake;

import com.databricks.labs.morpheus.intermediate.CallFunction;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.parsers.ConversionStrategy;
import com.databricks.labs.morpheus.parsers.FunctionBuilder;
import com.databricks.labs.morpheus.parsers.FunctionDefinition;
import com.databricks.labs.morpheus.parsers.FunctionDefinition$;
import com.databricks.labs.morpheus.parsers.snowflake.SnowflakeFunctionConverters;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Serializable;
import scala.Some;
import scala.collection.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001=3A!\u0002\u0004\u0001'!)\u0001\u0004\u0001C\u00013!1A\u0004\u0001Q\u0001\nuAQ!\r\u0001\u0005BIBQ\u0001\u000f\u0001\u0005\u0002e\u0012\u0001d\u00158po\u001ad\u0017m[3Gk:\u001cG/[8o\u0005VLG\u000eZ3s\u0015\t9\u0001\"A\u0005t]><h\r\\1lK*\u0011\u0011BC\u0001\ba\u0006\u00148/\u001a:t\u0015\tYA\"\u0001\u0005n_J\u0004\b.Z;t\u0015\tia\"\u0001\u0003mC\n\u001c(BA\b\u0011\u0003)!\u0017\r^1ce&\u001c7n\u001d\u0006\u0002#\u0005\u00191m\\7\u0004\u0001M\u0011\u0001\u0001\u0006\t\u0003+Yi\u0011\u0001C\u0005\u0003/!\u0011qBR;oGRLwN\u001c\"vS2$WM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003i\u0001\"a\u0007\u0001\u000e\u0003\u0019\tQd\u00158po\u001ad\u0017m[3Gk:\u001cG/[8o\t\u00164\u0017N\\5uS>t\u0007K\u001a\t\u0005=\u0005\u001ac&D\u0001 \u0015\u0005\u0001\u0013!B:dC2\f\u0017B\u0001\u0012 \u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007C\u0001\u0013,\u001d\t)\u0013\u0006\u0005\u0002'?5\tqE\u0003\u0002)%\u00051AH]8pizJ!AK\u0010\u0002\rA\u0013X\rZ3g\u0013\taSF\u0001\u0004TiJLgn\u001a\u0006\u0003U}\u0001\"!F\u0018\n\u0005AB!A\u0005$v]\u000e$\u0018n\u001c8EK\u001aLg.\u001b;j_:\f!CZ;oGRLwN\u001c#fM&t\u0017\u000e^5p]R\u00111G\u000e\t\u0004=Qr\u0013BA\u001b \u0005\u0019y\u0005\u000f^5p]\")qg\u0001a\u0001G\u0005!a.Y7f\u0003]\t\u0007\u000f\u001d7z\u0007>tg/\u001a:tS>t7\u000b\u001e:bi\u0016<\u0017\u0010\u0006\u0003;\u0001\nk\u0005CA\u001e?\u001b\u0005a$BA\u001f\u000b\u00031Ig\u000e^3s[\u0016$\u0017.\u0019;f\u0013\tyDH\u0001\u0006FqB\u0014Xm]:j_:DQ!\u0011\u0003A\u00029\nQBZ;oGRLwN\\!sSRL\b\"B\"\u0005\u0001\u0004!\u0015\u0001B1sON\u00042!\u0012&;\u001d\t1\u0005J\u0004\u0002'\u000f&\t\u0001%\u0003\u0002J?\u00059\u0001/Y2lC\u001e,\u0017BA&M\u0005\r\u0019V-\u001d\u0006\u0003\u0013~AQA\u0014\u0003A\u0002\r\na!\u001b:OC6,\u0007")
public class SnowflakeFunctionBuilder
extends FunctionBuilder {
    private final PartialFunction<String, FunctionDefinition> SnowflakeFunctionDefinitionPf = new Serializable(null){
        public static final long serialVersionUID = 0L;

        public final <A1 extends String, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
            A1 A1 = x1;
            if ("ABS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ACOS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ACOSH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ADD_MONTHS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ALERT_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("ALL_USER_NAMES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("ANY_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("APPLICATION_JSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("APPROXIMATE_JACCARD_INDEX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("APPROXIMATE_SIMILARITY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("APPROX_COUNT_DISTINCT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("APPROX_PERCENTILE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("APPROX_PERCENTILE_ACCUMULATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("APPROX_PERCENTILE_COMBINE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("APPROX_PERCENTILE_ESTIMATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("APPROX_TOP_K".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("APPROX_TOP_K_ACCUMULATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("APPROX_TOP_K_COMBINE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("APPROX_TOP_K_ESTIMATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAYS_OVERLAP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAYS_TO_OBJECT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAYAGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("ARRAY_AGG"));
            }
            if ("ARRAY_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_APPEND".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_CAT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_COMPACT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_CONSTRUCT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, Integer.MAX_VALUE);
            }
            if ("ARRAY_CONSTRUCT_COMPACT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, Integer.MAX_VALUE);
            }
            if ("ARRAY_CONTAINS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_DISTINCT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_EXCEPT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_FLATTEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_GENERATE_RANGE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("ARRAY_INSERT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("ARRAY_INTERSECTION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_MAX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_MIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_POSITION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_PREPEND".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_REMOVE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_REMOVE_AT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_SIZE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_SLICE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("ARRAY_SORT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("ARRAY_TO_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ARRAY_UNION_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ARRAY_UNIQUE_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ASCII".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ASIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ASINH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_ARRAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_BOOLEAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_CHAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_DATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_DECIMAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("AS_DOUBLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_INTEGER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_NUMBER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("AS_OBJECT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_REAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_TIMESTAMP_LTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_TIMESTAMP_NTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_TIMESTAMP_TZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AS_VARCHAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ATAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ATAN2".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ATANH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("AUTOMATIC_CLUSTERING_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "TABLE_NAME"})));
            }
            if ("AUTO_REFRESH_REGISTRATION_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "OBJECT_TYPE", "OBJECT_NAME"})));
            }
            if ("AVG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BASE64_DECODE_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("BASE64_DECODE_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("BASE64_ENCODE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("BITAND".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("BITAND_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITMAP_BIT_POSITION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITMAP_BUCKET_NUMBER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITMAP_CONSTRUCT_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITMAP_COUNT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITMAP_OR_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITNOT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITOR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("BITOR_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BITSHIFTLEFT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("BITSHIFTRIGHT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("BITXOR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("BITXOR_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BIT_LENGTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BLANK_COUNT (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BLANK_PERCENT (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BOOLAND".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("BOOLAND_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BOOLNOT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BOOLOR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("BOOLOR_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BOOLXOR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("BOOLXOR_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("BUILD_SCOPED_FILE_URL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("BUILD_STAGE_FILE_URL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CAST , ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("CBRT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"STR"})), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DISABLE_AUTO_CONVERT"})));
            }
            if ("CEIL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("CHAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("CHARINDEX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("CHECK_JSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("CHECK_XML".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"STR"})), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DISABLE_AUTO_CONVERT"})));
            }
            if ("CHR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("COALESCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("COLLATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("COLLATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("COMPLETE (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("COMPLETE_TASK_GRAPHS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"RESULT_LIMIT", "ROOT_TASK_NAME", "ERROR_ONLY"})));
            }
            if ("COMPRESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("CONCAT , ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("CONCAT_WS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("CONDITIONAL_CHANGE_EVENT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("CONDITIONAL_TRUE_EVENT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("CONTAINS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("CONVERT_TIMEZONE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("COPY_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("CORR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("COS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("COSH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("COT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("COUNT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("COUNT_IF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("COUNT_TOKENS (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("COVAR_POP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("COVAR_SAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("CUME_DIST".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_ACCOUNT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_ACCOUNT_NAME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_AVAILABLE_ROLES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_CLIENT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_DATABASE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_DATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_IP_ADDRESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_ORGANIZATION_NAME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_REGION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_ROLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_ROLE_TYPE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_SCHEMA".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_SCHEMAS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_SECONDARY_ROLES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_SESSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_STATEMENT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_TASK_GRAPHS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"RESULT_LIMIT", "ROOT_TASK_NAME"})));
            }
            if ("CURRENT_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("CURRENT_TIMESTAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("CURRENT_TRANSACTION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_USER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_VERSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("CURRENT_WAREHOUSE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("DATABASE_REFRESH_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("DATABASE_REFRESH_PROGRESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("DATABASE_REFRESH_PROGRESS_BY_JOB".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("DATABASE_REPLICATION_USAGE_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "DATABASE_NAME"})));
            }
            if ("DATABASE_STORAGE_USAGE_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "DATABASE_NAME"})));
            }
            if ("DATA_METRIC_FUNCTION_REFERENCES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("DATA_METRIC_SCHEDULED_TIME (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("DATA_TRANSFER_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END"})));
            }
            if ("DATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TO_DATE"));
            }
            if ("DATEADD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("DATEDIFF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("DATEFROMPARTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("DATE_FROM_PARTS"));
            }
            if ("DATE_FROM_PARTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("DATE_FORMAT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("DATE_PART".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("DATE_TRUNC".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("DAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DAYNAME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DAYOFMONTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DAYOFWEEK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DAYOFWEEKISO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DAYOFYEAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DECODE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, Integer.MAX_VALUE);
            }
            if ("DECOMPRESS_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("DECOMPRESS_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("DECRYPT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 4);
            }
            if ("DECRYPT_RAW".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 6);
            }
            if ("DEGREES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DENSE_RANK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("DIV0".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("DIV0NULL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("DUPLICATE_COUNT (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("DYNAMIC_TABLES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("DYNAMIC_TABLE_GRAPH_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"AS_OF", "HISTORY_START", "HISTORY_END"})));
            }
            if ("DYNAMIC_TABLE_REFRESH_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATA_TIMESTAMP_START", "DATA_TIMESTAMP_END", "RESULT_LIMIT", "NAME", "NAME_PREFIX", "ERROR_ONLY"})));
            }
            if ("EDITDISTANCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("EMAIL_INTEGRATION_CONFIG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(5);
            }
            if ("EMBED_TEXT_1024 (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("EMBED_TEXT_768 (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ENCRYPT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 4);
            }
            if ("ENCRYPT_RAW".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 5);
            }
            if ("ENDSWITH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("EQUAL_NULL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("EXP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("EXPLAIN_JSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("EXTERNAL_FUNCTIONS_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "FUNCTION_SIGNATURE"})));
            }
            if ("EXTERNAL_TABLE_FILES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("EXTERNAL_TABLE_FILE_REGISTRATION_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("EXTRACT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("EXTRACT_ANSWER (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("EXTRACT_SEMANTIC_CATEGORIES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("FACTORIAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("FILTER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("FINETUNE ('CANCEL') (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("FINETUNE ('CREATE') (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("FINETUNE ('DESCRIBE') (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("FINETUNE ('SHOW') (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("FINETUNE (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("FIRST_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("FLATTEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"INPUT"})), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"PATH", "OUTER", "RECURSIVE", "MODE"})));
            }
            if ("FLOOR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("FRESHNESS (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("GENERATE_COLUMN_DESCRIPTION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("GENERATOR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"ROWCOUNT", "TIMELIMIT"})));
            }
            if ("GET".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("GETBIT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("GETDATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("GETVARIABLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("GET_ABSOLUTE_PATH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("GET_ANACONDA_PACKAGES_REPODATA".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("GET_CONDITION_QUERY_UUID".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("GET_DDL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("GET_IGNORE_CASE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("GET_OBJECT_REFERENCES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("GET_PATH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("GET_PRESIGNED_URL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("GET_QUERY_OPERATOR_STATS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("GET_RELATIVE_PATH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("GET_STAGE_LOCATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("GREATEST".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("GREATEST_IGNORE_NULLS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("GROUPING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("GROUPING_ID".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("H3_CELL_TO_BOUNDARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_CELL_TO_CHILDREN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_CELL_TO_CHILDREN_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_CELL_TO_PARENT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_CELL_TO_POINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_COMPACT_CELLS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_COMPACT_CELLS_STRINGS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_COVERAGE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_COVERAGE_STRINGS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_GET_RESOLUTION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_GRID_DISK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_GRID_DISTANCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_GRID_PATH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_INT_TO_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_IS_PENTAGON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_IS_VALID_CELL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_LATLNG_TO_CELL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("H3_LATLNG_TO_CELL_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("H3_POINT_TO_CELL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_POINT_TO_CELL_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_POLYGON_TO_CELLS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_POLYGON_TO_CELLS_STRINGS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_STRING_TO_INT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("H3_TRY_COVERAGE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_TRY_COVERAGE_STRINGS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_TRY_GRID_DISTANCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_TRY_GRID_PATH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_TRY_POLYGON_TO_CELLS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_TRY_POLYGON_TO_CELLS_STRINGS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_UNCOMPACT_CELLS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("H3_UNCOMPACT_CELLS_STRINGS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("HASH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("HASH_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("HAVERSINE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("HEX_DECODE_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("HEX_DECODE_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("HEX_ENCODE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("HLL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("HLL_ACCUMULATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("HLL_COMBINE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("HLL_ESTIMATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("HLL_EXPORT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("HLL_IMPORT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("HOUR / MINUTE / SECOND".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("HOUR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IFF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("IFNULL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ILIKE ANY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("INFER_SCHEMA".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("INITCAP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("INSERT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("INTEGRATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("INVOKER_ROLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("INVOKER_SHARE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("IS [ NOT ] DISTINCT FROM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("IS [ NOT ] NULL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("ISNULL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_ARRAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_BOOLEAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_CHAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_DATABASE_ROLE_IN_SESSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_DATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_DATE_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_DECIMAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_DOUBLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_GRANTED_TO_INVOKER_ROLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_INSTANCE_ROLE_IN_SESSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_INTEGER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_NULL_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_OBJECT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_REAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_ROLE_IN_SESSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_TIMESTAMP_LTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_TIMESTAMP_NTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_TIMESTAMP_TZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("IS_VARCHAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("JAROWINKLER_SIMILARITY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("JSON_EXTRACT_PATH_TEXT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("KURTOSIS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("LAG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("LAST_DAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("LAST_QUERY_ID".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("LAST_SUCCESSFUL_SCHEDULED_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("LAST_TRANSACTION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("LAST_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("LEAD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("LEAST".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("LEAST_IGNORE_NULLS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("LEFT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("LEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("LENGTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("LIKE ALL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("LIKE ANY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("LISTAGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("LN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("LOCALTIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("LOCALTIMESTAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("LOG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("LOGIN_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"TIME_RANGE_START", "TIME_RANGE_END", "RESULT_LIMIT"})));
            }
            if ("LOGIN_HISTORY_BY_USER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"USER_NAME", "TIME_RANGE_START", "TIME_RANGE_END", "RESULT_LIMIT"})));
            }
            if ("LOWER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("LPAD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("LTRIM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("MAP_CAT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("MAP_CONTAINS_KEY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("MAP_DELETE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("MAP_INSERT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 4);
            }
            if ("MAP_KEYS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MAP_PICK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("MAP_SIZE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MATERIALIZED_VIEW_REFRESH_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "MATERIALIZED_VIEW_NAME"})));
            }
            if ("MAX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MAX_BY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("MD5".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MD5_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MD5_HEX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MD5_NUMBER \u2014 ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MD5_NUMBER_LOWER64".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MD5_NUMBER_UPPER64".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MEDIAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MIN (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MINHASH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, Integer.MAX_VALUE);
            }
            if ("MINHASH_COMBINE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("MINUTE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MIN_BY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("MOD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("MODE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MONTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MONTHNAME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("MONTHS_BETWEEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("NETWORK_RULE_REFERENCES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("NEXT_DAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("NORMAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("NOTIFICATION_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("NTH_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("NTILE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("NULLIF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("NULLIFZERO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("NULL_COUNT (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("NULL_PERCENT (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("NVL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("IFNULL"));
            }
            if ("NVL2".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("OBJECT_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("OBJECT_CONSTRUCT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, Integer.MAX_VALUE);
            }
            if ("OBJECT_CONSTRUCT_KEEP_NULL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, Integer.MAX_VALUE);
            }
            if ("OBJECT_DELETE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, Integer.MAX_VALUE);
            }
            if ("OBJECT_INSERT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 4);
            }
            if ("OBJECT_KEYS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("OBJECT_PICK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, Integer.MAX_VALUE);
            }
            if ("OCTET_LENGTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("PARSE_IP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("PARSE_JSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("PARSE_URL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("PARSE_XML".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"STR"})), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DISABLE_AUTO_CONVERT"})));
            }
            if ("PERCENTILE_CONT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("PERCENTILE_DISC".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("PERCENT_RANK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("PI".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("PIPE_USAGE_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "PIPE_NAME"})));
            }
            if ("POLICY_CONTEXT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(7);
            }
            if ("POLICY_REFERENCES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"POLICY_NAME", "POLICY_KIND", "REF_ENTITY_NAME", "REF_ENTITY_DOMAIN"})));
            }
            if ("POSITION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("POW".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("POWER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("PREVIOUS_DAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("QUARTER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("QUERY_ACCELERATION_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("QUERY_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"END_TIME_RANGE_START", "END_TIME_RANGE_END", "RESULT_LIMIT"})));
            }
            if ("QUERY_HISTORY_BY_SESSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"SESSION_ID", "END_TIME_RANGE_START", "END_TIME_RANGE_END", "RESULT_LIMIT"})));
            }
            if ("QUERY_HISTORY_BY_USER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"USER_NAME", "END_TIME_RANGE_START", "END_TIME_RANGE_END", "RESULT_LIMIT"})));
            }
            if ("QUERY_HISTORY_BY_WAREHOUSE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"WAREHOUSE_NAME", "END_TIME_RANGE_START", "END_TIME_RANGE_END", "RESULT_LIMIT"})));
            }
            if ("RADIANS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("RANDOM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("RANDSTR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("RANK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("RATIO_TO_REPORT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("REGEXP_COUNT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 4);
            }
            if ("REGEXP_INSTR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 7);
            }
            if ("REGEXP_LIKE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("REGEXP_REPLACE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 6);
            }
            if ("REGEXP_SUBSTR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 6);
            }
            if ("REGEXP_SUBSTR_ALL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 6);
            }
            if ("REGR_AVGX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_AVGY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_COUNT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_INTERCEPT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_R2".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_SLOPE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_SXX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_SXY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_SYY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_VALX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REGR_VALY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REPEAT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("REPLACE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("REPLICATION_GROUP_REFRESH_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("REPLICATION_GROUP_REFRESH_PROGRESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("REPLICATION_GROUP_REFRESH_PROGRESS_BY_JOB".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("REPLICATION_GROUP_USAGE_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "REPLICATION_GROUP_NAME"})));
            }
            if ("REPLICATION_USAGE_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "DATABASE_NAME"})));
            }
            if ("REST_EVENT_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 4);
            }
            if ("RESULT_SCAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("REVERSE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("RIGHT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("RLIKE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("ROUND".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("ROW_COUNT (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("ROW_NUMBER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("RPAD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("RTRIM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("RTRIMMED_LENGTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SCHEDULED_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SEARCH_OPTIMIZATION_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "TABLE_NAME"})));
            }
            if ("SECOND".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SENTIMENT (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SEQ1".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("SEQ2".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("SEQ4".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("SEQ8".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("SERVERLESS_TASK_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "TASK_NAME"})));
            }
            if ("SHA1".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SHA1_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SHA1_HEX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SHA2".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("SHA2_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("SHA2_HEX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("SHOW_PYTHON_PACKAGES_DEPENDENCIES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SIGN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SINH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SKEW".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SOUNDEX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SOUNDEX_P123".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SPACE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SPLIT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SPLIT_PART".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SPLIT_TO_TABLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SQRT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SQUARE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("STAGE_DIRECTORY_FILE_REGISTRATION_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("STAGE_STORAGE_USAGE_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("STARTSWITH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("STDDEV (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("STDDEV".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("STDDEV, STDDEV_SAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("STDDEV_POP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("STDDEV_SAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("STRIP_NULL_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("STRTOK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("STRTOK_SPLIT_TO_TABLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("STRTOK_TO_ARRAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ST_AREA".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ASBINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ASEWKB".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ASEWKT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ASGEOJSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ASTEXT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ASWKB".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ASWKT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_AZIMUTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_BUFFER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_CENTROID".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_COLLECT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ST_CONTAINS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_COVEREDBY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_COVERS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_DIFFERENCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_DIMENSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_DISJOINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_DISTANCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_DWITHIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("ST_ENDPOINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_ENVELOPE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_GEOGFROMGEOHASH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ST_GEOGPOINTFROMGEOHASH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_GEOGRAPHYFROMWKB".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ST_GEOGRAPHYFROMWKT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ST_GEOHASH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ST_GEOMETRYFROMWKB".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("ST_GEOMETRYFROMWKT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("ST_GEOMFROMGEOHASH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("ST_GEOMPOINTFROMGEOHASH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_GEOM_POINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_HAUSDORFFDISTANCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_INTERSECTION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_INTERSECTION_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_INTERSECTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_ISVALID".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_LENGTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_MAKEGEOMPOINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_MAKELINE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_MAKEPOINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_MAKEPOLYGON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_MAKEPOLYGONORIENTED".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_NPOINTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_NUMPOINTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_PERIMETER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_POINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_POINTN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_POLYGON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_SETSRID".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_SIMPLIFY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("ST_SRID".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_STARTPOINT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_SYMDIFFERENCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_TRANSFORM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("ST_UNION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_UNION_AGG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_WITHIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("ST_X".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_XMAX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_XMIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_Y".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_YMAX".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ST_YMIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SUM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SUMMARIZE (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSDATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$ABORT_SESSION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$ABORT_TRANSACTION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$ADD_EVENT (for Snowflake Scripting)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$ALLOWLIST".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$ALLOWLIST_PRIVATELINK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$AUTHORIZE_PRIVATELINK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$AUTHORIZE_STAGE_PRIVATELINK_ACCESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$BEHAVIOR_CHANGE_BUNDLE_STATUS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$BLOCK_INTERNAL_STAGES_PUBLIC_ACCESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$CANCEL_ALL_QUERIES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$CANCEL_QUERY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$CLEANUP_DATABASE_ROLE_GRANTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$CLIENT_VERSION_INFO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$CLUSTERING_DEPTH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("SYSTEM$CLUSTERING_INFORMATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("SYSTEM$CLUSTERING_RATIO \u2014 ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("SYSTEM$CONVERT_PIPES_SQS_TO_SNS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$CREATE_BILLING_EVENT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 7);
            }
            if ("SYSTEM$CURRENT_USER_TASK_NAME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$DATABASE_REFRESH_HISTORY \u2014 ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$DATABASE_REFRESH_PROGRESS , SYSTEM$DATABASE_REFRESH_PROGRESS_BY_JOB \u2014 ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$DISABLE_BEHAVIOR_CHANGE_BUNDLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$DISABLE_DATABASE_REPLICATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$ENABLE_BEHAVIOR_CHANGE_BUNDLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$ESTIMATE_AUTOMATIC_CLUSTERING_COSTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$ESTIMATE_QUERY_ACCELERATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$ESTIMATE_SEARCH_OPTIMIZATION_COSTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("SYSTEM$EXPLAIN_JSON_TO_TEXT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$EXPLAIN_PLAN_JSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$EXTERNAL_TABLE_PIPE_STATUS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$FINISH_OAUTH_FLOW".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GENERATE_SAML_CSR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$GENERATE_SCIM_ACCESS_TOKEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$GET_AWS_SNS_IAM_POLICY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_CLASSIFICATION_RESULT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_CMK_AKV_CONSENT_URL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$GET_CMK_CONFIG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_CMK_INFO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$GET_CMK_KMS_KEY_POLICY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$GET_COMPUTE_POOL_STATUS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_DIRECTORY_TABLE_STATUS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 1);
            }
            if ("SYSTEM$GET_GCP_KMS_CMK_GRANT_ACCESS_CMD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$GET_ICEBERG_TABLE_INFORMATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_LOGIN_FAILURE_DETAILS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_PREDECESSOR_RETURN_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_PRIVATELINK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$GET_PRIVATELINK_AUTHORIZED_ENDPOINTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$GET_PRIVATELINK_CONFIG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$GET_SERVICE_LOGS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 4);
            }
            if ("SYSTEM$GET_SERVICE_STATUS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("SYSTEM$GET_SNOWFLAKE_PLATFORM_INFO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$GET_TAG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$GET_TAG_ALLOWED_VALUES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_TAG_ON_CURRENT_COLUMN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_TAG_ON_CURRENT_TABLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GET_TASK_GRAPH_CONFIG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$GLOBAL_ACCOUNT_SET_PARAMETER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$INTERNAL_STAGES_PUBLIC_ACCESS_STATUS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$IS_APPLICATION_INSTALLED_FROM_SAME_ACCOUNT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$IS_APPLICATION_SHARING_EVENTS_WITH_PROVIDER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$LAST_CHANGE_COMMIT_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$LINK_ACCOUNT_OBJECTS_BY_NAME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$LIST_APPLICATION_RESTRICTED_FEATURES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$LOG, SYSTEM$LOG_<level> (for Snowflake Scripting)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$MIGRATE_SAML_IDP_REGISTRATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("SYSTEM$PIPE_FORCE_RESUME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$PIPE_REBINDING_WITH_NOTIFICATION_CHANNEL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$PIPE_STATUS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$QUERY_REFERENCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("SYSTEM$REFERENCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 4);
            }
            if ("SYSTEM$REGISTER_CMK_INFO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(7);
            }
            if ("SYSTEM$REGISTRY_LIST_IMAGES \u2014 ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$REVOKE_PRIVATELINK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$REVOKE_STAGE_PRIVATELINK_ACCESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$SET_APPLICATION_RESTRICTED_FEATURE_ACCESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$SET_EVENT_SHARING_ACCOUNT_FOR_REGION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$SET_RETURN_VALUE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$SET_SPAN_ATTRIBUTES (for Snowflake Scripting)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$SHOW_ACTIVE_BEHAVIOR_CHANGE_BUNDLES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$SHOW_BUDGETS_IN_ACCOUNT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$SHOW_EVENT_SHARING_ACCOUNTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$SHOW_OAUTH_CLIENT_SECRETS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$SNOWPIPE_STREAMING_UPDATE_CHANNEL_OFFSET_TOKEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(5);
            }
            if ("SYSTEM$START_OAUTH_FLOW".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$STREAM_BACKLOG".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$STREAM_GET_TABLE_TIMESTAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$STREAM_HAS_DATA".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$TASK_DEPENDENTS_ENABLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$TASK_RUNTIME_INFO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$TYPEOF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$UNBLOCK_INTERNAL_STAGES_PUBLIC_ACCESS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$UNSET_EVENT_SHARING_ACCOUNT_FOR_REGION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("SYSTEM$USER_TASK_CANCEL_ONGOING_EXECUTIONS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$VALIDATE_STORAGE_INTEGRATION".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("SYSTEM$VERIFY_CMK_INFO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$VERIFY_EXTERNAL_OAUTH_TOKEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("SYSTEM$WAIT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("SYSTEM$WHITELIST \u2014 ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTEM$WHITELIST_PRIVATELINK \u2014 ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("SYSTIMESTAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("TAG_REFERENCES".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("TAG_REFERENCES_ALL_COLUMNS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("TAG_REFERENCES_WITH_LINEAGE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TANH".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TASK_DEPENDENTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("TASK_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"SCHEDULED_TIME_RANGE_START", "SCHEDULED_TIME_RANGE_END", "RESULT_LIMIT", "TASK_NAME", "ERROR_ONLY", "ROOT_TASK_ID"})));
            }
            if ("TEXT_HTML".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TEXT_PLAIN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TO_TIME"));
            }
            if ("TIMEADD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("DATEADD"));
            }
            if ("TIMEDIFF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("TIMESTAMPADD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("TIMESTAMPDIFF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("DATEDIFF"));
            }
            if ("TIMESTAMP_FROM_PARTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 8);
            }
            if ("TIMESTAMP_LTZ_FROM_PARTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(6, 7);
            }
            if ("TIMESTAMP_NTZ_FROM_PARTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 7);
            }
            if ("TIMESTAMP_TZ_FROM_PARTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(6, 7);
            }
            if ("TIME_FROM_PARTS".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 4);
            }
            if ("TIME_SLICE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 4);
            }
            if ("TOP_INSIGHTS (SNOWFLAKE.ML)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("TO_ARRAY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_BOOLEAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TO_CHAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TO_VARCHAR"));
            }
            if ("TO_DATE , DATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4, 5);
            }
            if ("TO_DATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_DECIMAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 4).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TO_NUMBER"));
            }
            if ("TO_DOUBLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_GEOGRAPHY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_GEOMETRY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("TO_JSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TO_NUMBER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 4);
            }
            if ("TO_NUMERIC".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 4).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TO_NUMBER"));
            }
            if ("TO_OBJECT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TO_QUERY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("TO_TIME , TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4, 5);
            }
            if ("TO_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_TIMESTAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_TIMESTAMP_LTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_TIMESTAMP_NTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_TIMESTAMP_TZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_VARCHAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TO_VARIANT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TO_XML".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TRANSFORM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("TRANSLATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("TRIM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRUNC".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TRUNCATE"));
            }
            if ("TRUNCATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_BASE64_DECODE_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_BASE64_DECODE_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_CAST".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("TRY_COMPLETE (SNOWFLAKE.CORTEX)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("TRY_DECRYPT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 4);
            }
            if ("TRY_DECRYPT_RAW".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3, 6);
            }
            if ("TRY_HEX_DECODE_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TRY_HEX_DECODE_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TRY_PARSE_JSON".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TRY_TO_BINARY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_BOOLEAN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("TRY_TO_DATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_DECIMAL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 4).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TRY_TO_NUMBER"));
            }
            if ("TRY_TO_DOUBLE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_GEOGRAPHY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_GEOMETRY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("TRY_TO_NUMBER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 4);
            }
            if ("TRY_TO_NUMERIC".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 4).withConversionStrategy(new SnowflakeFunctionConverters.SynonymOf("TRY_TO_NUMBER"));
            }
            if ("TRY_TO_TIME".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_TIMESTAMP / TRY_TO_TIMESTAMP_*".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("TRY_TO_TIMESTAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_TIMESTAMP_LTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_TIMESTAMP_NTZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TRY_TO_TIMESTAMP_TZ".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 2);
            }
            if ("TYPEOF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("UNICODE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("UNIFORM".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("UNIQUE_COUNT (system data metric function)".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("UPPER".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("UUID_STRING".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0, 2);
            }
            if ("VALIDATE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("VALIDATE_PIPE_LOAD".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("VARIANCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("VARIANCE_POP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("VARIANCE_SAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("VAR_POP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("VAR_SAMP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("VECTOR_COSINE_SIMILARITY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("VECTOR_INNER_PRODUCT".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("VECTOR_L2_DISTANCE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("WAREHOUSE_LOAD_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.symbolic((Set<String>)Predef$.MODULE$.Set().empty(), (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"DATE_RANGE_START", "DATE_RANGE_END", "WAREHOUSE_NAME"})));
            }
            if ("WAREHOUSE_METERING_HISTORY".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1, 3);
            }
            if ("WEEK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("WEEKISO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("WEEKOFYEAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("WIDTH_BUCKET".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(4);
            }
            if ("XMLGET".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("YEAR".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("YEAROFWEEK".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("YEAROFWEEKISO".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ZEROIFNULL".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(1);
            }
            if ("ZIPF".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(3);
            }
            if ("[ NOT ] BETWEEN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("[ NOT ] ILIKE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("[ NOT ] IN".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(0);
            }
            if ("[ NOT ] LIKE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            if ("[ NOT ] REGEXP".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2);
            }
            if ("[ NOT ] RLIKE".equals(A1)) {
                return (B1)FunctionDefinition$.MODULE$.standard(2, 3);
            }
            return function1.apply(x1);
        }

        public final boolean isDefinedAt(String x1) {
            String string = x1;
            if ("ABS".equals(string)) {
                return true;
            }
            if ("ACOS".equals(string)) {
                return true;
            }
            if ("ACOSH".equals(string)) {
                return true;
            }
            if ("ADD_MONTHS".equals(string)) {
                return true;
            }
            if ("ALERT_HISTORY".equals(string)) {
                return true;
            }
            if ("ALL_USER_NAMES".equals(string)) {
                return true;
            }
            if ("ANY_VALUE".equals(string)) {
                return true;
            }
            if ("APPLICATION_JSON".equals(string)) {
                return true;
            }
            if ("APPROXIMATE_JACCARD_INDEX".equals(string)) {
                return true;
            }
            if ("APPROXIMATE_SIMILARITY".equals(string)) {
                return true;
            }
            if ("APPROX_COUNT_DISTINCT".equals(string)) {
                return true;
            }
            if ("APPROX_PERCENTILE".equals(string)) {
                return true;
            }
            if ("APPROX_PERCENTILE_ACCUMULATE".equals(string)) {
                return true;
            }
            if ("APPROX_PERCENTILE_COMBINE".equals(string)) {
                return true;
            }
            if ("APPROX_PERCENTILE_ESTIMATE".equals(string)) {
                return true;
            }
            if ("APPROX_TOP_K".equals(string)) {
                return true;
            }
            if ("APPROX_TOP_K_ACCUMULATE".equals(string)) {
                return true;
            }
            if ("APPROX_TOP_K_COMBINE".equals(string)) {
                return true;
            }
            if ("APPROX_TOP_K_ESTIMATE".equals(string)) {
                return true;
            }
            if ("ARRAYS_OVERLAP".equals(string)) {
                return true;
            }
            if ("ARRAYS_TO_OBJECT".equals(string)) {
                return true;
            }
            if ("ARRAYAGG".equals(string)) {
                return true;
            }
            if ("ARRAY_AGG".equals(string)) {
                return true;
            }
            if ("ARRAY_APPEND".equals(string)) {
                return true;
            }
            if ("ARRAY_CAT".equals(string)) {
                return true;
            }
            if ("ARRAY_COMPACT".equals(string)) {
                return true;
            }
            if ("ARRAY_CONSTRUCT".equals(string)) {
                return true;
            }
            if ("ARRAY_CONSTRUCT_COMPACT".equals(string)) {
                return true;
            }
            if ("ARRAY_CONTAINS".equals(string)) {
                return true;
            }
            if ("ARRAY_DISTINCT".equals(string)) {
                return true;
            }
            if ("ARRAY_EXCEPT".equals(string)) {
                return true;
            }
            if ("ARRAY_FLATTEN".equals(string)) {
                return true;
            }
            if ("ARRAY_GENERATE_RANGE".equals(string)) {
                return true;
            }
            if ("ARRAY_INSERT".equals(string)) {
                return true;
            }
            if ("ARRAY_INTERSECTION".equals(string)) {
                return true;
            }
            if ("ARRAY_MAX".equals(string)) {
                return true;
            }
            if ("ARRAY_MIN".equals(string)) {
                return true;
            }
            if ("ARRAY_POSITION".equals(string)) {
                return true;
            }
            if ("ARRAY_PREPEND".equals(string)) {
                return true;
            }
            if ("ARRAY_REMOVE".equals(string)) {
                return true;
            }
            if ("ARRAY_REMOVE_AT".equals(string)) {
                return true;
            }
            if ("ARRAY_SIZE".equals(string)) {
                return true;
            }
            if ("ARRAY_SLICE".equals(string)) {
                return true;
            }
            if ("ARRAY_SORT".equals(string)) {
                return true;
            }
            if ("ARRAY_TO_STRING".equals(string)) {
                return true;
            }
            if ("ARRAY_UNION_AGG".equals(string)) {
                return true;
            }
            if ("ARRAY_UNIQUE_AGG".equals(string)) {
                return true;
            }
            if ("ASCII".equals(string)) {
                return true;
            }
            if ("ASIN".equals(string)) {
                return true;
            }
            if ("ASINH".equals(string)) {
                return true;
            }
            if ("AS_ARRAY".equals(string)) {
                return true;
            }
            if ("AS_BINARY".equals(string)) {
                return true;
            }
            if ("AS_BOOLEAN".equals(string)) {
                return true;
            }
            if ("AS_CHAR".equals(string)) {
                return true;
            }
            if ("AS_DATE".equals(string)) {
                return true;
            }
            if ("AS_DECIMAL".equals(string)) {
                return true;
            }
            if ("AS_DOUBLE".equals(string)) {
                return true;
            }
            if ("AS_INTEGER".equals(string)) {
                return true;
            }
            if ("AS_NUMBER".equals(string)) {
                return true;
            }
            if ("AS_OBJECT".equals(string)) {
                return true;
            }
            if ("AS_REAL".equals(string)) {
                return true;
            }
            if ("AS_TIME".equals(string)) {
                return true;
            }
            if ("AS_TIMESTAMP_LTZ".equals(string)) {
                return true;
            }
            if ("AS_TIMESTAMP_NTZ".equals(string)) {
                return true;
            }
            if ("AS_TIMESTAMP_TZ".equals(string)) {
                return true;
            }
            if ("AS_VARCHAR".equals(string)) {
                return true;
            }
            if ("ATAN".equals(string)) {
                return true;
            }
            if ("ATAN2".equals(string)) {
                return true;
            }
            if ("ATANH".equals(string)) {
                return true;
            }
            if ("AUTOMATIC_CLUSTERING_HISTORY".equals(string)) {
                return true;
            }
            if ("AUTO_REFRESH_REGISTRATION_HISTORY".equals(string)) {
                return true;
            }
            if ("AVG".equals(string)) {
                return true;
            }
            if ("BASE64_DECODE_BINARY".equals(string)) {
                return true;
            }
            if ("BASE64_DECODE_STRING".equals(string)) {
                return true;
            }
            if ("BASE64_ENCODE".equals(string)) {
                return true;
            }
            if ("BITAND".equals(string)) {
                return true;
            }
            if ("BITAND_AGG".equals(string)) {
                return true;
            }
            if ("BITMAP_BIT_POSITION".equals(string)) {
                return true;
            }
            if ("BITMAP_BUCKET_NUMBER".equals(string)) {
                return true;
            }
            if ("BITMAP_CONSTRUCT_AGG".equals(string)) {
                return true;
            }
            if ("BITMAP_COUNT".equals(string)) {
                return true;
            }
            if ("BITMAP_OR_AGG".equals(string)) {
                return true;
            }
            if ("BITNOT".equals(string)) {
                return true;
            }
            if ("BITOR".equals(string)) {
                return true;
            }
            if ("BITOR_AGG".equals(string)) {
                return true;
            }
            if ("BITSHIFTLEFT".equals(string)) {
                return true;
            }
            if ("BITSHIFTRIGHT".equals(string)) {
                return true;
            }
            if ("BITXOR".equals(string)) {
                return true;
            }
            if ("BITXOR_AGG".equals(string)) {
                return true;
            }
            if ("BIT_LENGTH".equals(string)) {
                return true;
            }
            if ("BLANK_COUNT (system data metric function)".equals(string)) {
                return true;
            }
            if ("BLANK_PERCENT (system data metric function)".equals(string)) {
                return true;
            }
            if ("BOOLAND".equals(string)) {
                return true;
            }
            if ("BOOLAND_AGG".equals(string)) {
                return true;
            }
            if ("BOOLNOT".equals(string)) {
                return true;
            }
            if ("BOOLOR".equals(string)) {
                return true;
            }
            if ("BOOLOR_AGG".equals(string)) {
                return true;
            }
            if ("BOOLXOR".equals(string)) {
                return true;
            }
            if ("BOOLXOR_AGG".equals(string)) {
                return true;
            }
            if ("BUILD_SCOPED_FILE_URL".equals(string)) {
                return true;
            }
            if ("BUILD_STAGE_FILE_URL".equals(string)) {
                return true;
            }
            if ("CAST , ".equals(string)) {
                return true;
            }
            if ("CBRT".equals(string)) {
                return true;
            }
            if ("CEIL".equals(string)) {
                return true;
            }
            if ("CHAR".equals(string)) {
                return true;
            }
            if ("CHARINDEX".equals(string)) {
                return true;
            }
            if ("CHECK_JSON".equals(string)) {
                return true;
            }
            if ("CHECK_XML".equals(string)) {
                return true;
            }
            if ("CHR".equals(string)) {
                return true;
            }
            if ("COALESCE".equals(string)) {
                return true;
            }
            if ("COLLATE".equals(string)) {
                return true;
            }
            if ("COLLATION".equals(string)) {
                return true;
            }
            if ("COMPLETE (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("COMPLETE_TASK_GRAPHS".equals(string)) {
                return true;
            }
            if ("COMPRESS".equals(string)) {
                return true;
            }
            if ("CONCAT , ".equals(string)) {
                return true;
            }
            if ("CONCAT_WS".equals(string)) {
                return true;
            }
            if ("CONDITIONAL_CHANGE_EVENT".equals(string)) {
                return true;
            }
            if ("CONDITIONAL_TRUE_EVENT".equals(string)) {
                return true;
            }
            if ("CONTAINS".equals(string)) {
                return true;
            }
            if ("CONVERT_TIMEZONE".equals(string)) {
                return true;
            }
            if ("COPY_HISTORY".equals(string)) {
                return true;
            }
            if ("CORR".equals(string)) {
                return true;
            }
            if ("COS".equals(string)) {
                return true;
            }
            if ("COSH".equals(string)) {
                return true;
            }
            if ("COT".equals(string)) {
                return true;
            }
            if ("COUNT".equals(string)) {
                return true;
            }
            if ("COUNT_IF".equals(string)) {
                return true;
            }
            if ("COUNT_TOKENS (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("COVAR_POP".equals(string)) {
                return true;
            }
            if ("COVAR_SAMP".equals(string)) {
                return true;
            }
            if ("CUME_DIST".equals(string)) {
                return true;
            }
            if ("CURRENT_ACCOUNT".equals(string)) {
                return true;
            }
            if ("CURRENT_ACCOUNT_NAME".equals(string)) {
                return true;
            }
            if ("CURRENT_AVAILABLE_ROLES".equals(string)) {
                return true;
            }
            if ("CURRENT_CLIENT".equals(string)) {
                return true;
            }
            if ("CURRENT_DATABASE".equals(string)) {
                return true;
            }
            if ("CURRENT_DATE".equals(string)) {
                return true;
            }
            if ("CURRENT_IP_ADDRESS".equals(string)) {
                return true;
            }
            if ("CURRENT_ORGANIZATION_NAME".equals(string)) {
                return true;
            }
            if ("CURRENT_REGION".equals(string)) {
                return true;
            }
            if ("CURRENT_ROLE".equals(string)) {
                return true;
            }
            if ("CURRENT_ROLE_TYPE".equals(string)) {
                return true;
            }
            if ("CURRENT_SCHEMA".equals(string)) {
                return true;
            }
            if ("CURRENT_SCHEMAS".equals(string)) {
                return true;
            }
            if ("CURRENT_SECONDARY_ROLES".equals(string)) {
                return true;
            }
            if ("CURRENT_SESSION".equals(string)) {
                return true;
            }
            if ("CURRENT_STATEMENT".equals(string)) {
                return true;
            }
            if ("CURRENT_TASK_GRAPHS".equals(string)) {
                return true;
            }
            if ("CURRENT_TIME".equals(string)) {
                return true;
            }
            if ("CURRENT_TIMESTAMP".equals(string)) {
                return true;
            }
            if ("CURRENT_TRANSACTION".equals(string)) {
                return true;
            }
            if ("CURRENT_USER".equals(string)) {
                return true;
            }
            if ("CURRENT_VERSION".equals(string)) {
                return true;
            }
            if ("CURRENT_WAREHOUSE".equals(string)) {
                return true;
            }
            if ("DATABASE_REFRESH_HISTORY".equals(string)) {
                return true;
            }
            if ("DATABASE_REFRESH_PROGRESS".equals(string)) {
                return true;
            }
            if ("DATABASE_REFRESH_PROGRESS_BY_JOB".equals(string)) {
                return true;
            }
            if ("DATABASE_REPLICATION_USAGE_HISTORY".equals(string)) {
                return true;
            }
            if ("DATABASE_STORAGE_USAGE_HISTORY".equals(string)) {
                return true;
            }
            if ("DATA_METRIC_FUNCTION_REFERENCES".equals(string)) {
                return true;
            }
            if ("DATA_METRIC_SCHEDULED_TIME (system data metric function)".equals(string)) {
                return true;
            }
            if ("DATA_TRANSFER_HISTORY".equals(string)) {
                return true;
            }
            if ("DATE".equals(string)) {
                return true;
            }
            if ("DATEADD".equals(string)) {
                return true;
            }
            if ("DATEDIFF".equals(string)) {
                return true;
            }
            if ("DATEFROMPARTS".equals(string)) {
                return true;
            }
            if ("DATE_FROM_PARTS".equals(string)) {
                return true;
            }
            if ("DATE_FORMAT".equals(string)) {
                return true;
            }
            if ("DATE_PART".equals(string)) {
                return true;
            }
            if ("DATE_TRUNC".equals(string)) {
                return true;
            }
            if ("DAY".equals(string)) {
                return true;
            }
            if ("DAYNAME".equals(string)) {
                return true;
            }
            if ("DAYOFMONTH".equals(string)) {
                return true;
            }
            if ("DAYOFWEEK".equals(string)) {
                return true;
            }
            if ("DAYOFWEEKISO".equals(string)) {
                return true;
            }
            if ("DAYOFYEAR".equals(string)) {
                return true;
            }
            if ("DECODE".equals(string)) {
                return true;
            }
            if ("DECOMPRESS_BINARY".equals(string)) {
                return true;
            }
            if ("DECOMPRESS_STRING".equals(string)) {
                return true;
            }
            if ("DECRYPT".equals(string)) {
                return true;
            }
            if ("DECRYPT_RAW".equals(string)) {
                return true;
            }
            if ("DEGREES".equals(string)) {
                return true;
            }
            if ("DENSE_RANK".equals(string)) {
                return true;
            }
            if ("DIV0".equals(string)) {
                return true;
            }
            if ("DIV0NULL".equals(string)) {
                return true;
            }
            if ("DUPLICATE_COUNT (system data metric function)".equals(string)) {
                return true;
            }
            if ("DYNAMIC_TABLES".equals(string)) {
                return true;
            }
            if ("DYNAMIC_TABLE_GRAPH_HISTORY".equals(string)) {
                return true;
            }
            if ("DYNAMIC_TABLE_REFRESH_HISTORY".equals(string)) {
                return true;
            }
            if ("EDITDISTANCE".equals(string)) {
                return true;
            }
            if ("EMAIL_INTEGRATION_CONFIG".equals(string)) {
                return true;
            }
            if ("EMBED_TEXT_1024 (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("EMBED_TEXT_768 (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("ENCRYPT".equals(string)) {
                return true;
            }
            if ("ENCRYPT_RAW".equals(string)) {
                return true;
            }
            if ("ENDSWITH".equals(string)) {
                return true;
            }
            if ("EQUAL_NULL".equals(string)) {
                return true;
            }
            if ("EXP".equals(string)) {
                return true;
            }
            if ("EXPLAIN_JSON".equals(string)) {
                return true;
            }
            if ("EXTERNAL_FUNCTIONS_HISTORY".equals(string)) {
                return true;
            }
            if ("EXTERNAL_TABLE_FILES".equals(string)) {
                return true;
            }
            if ("EXTERNAL_TABLE_FILE_REGISTRATION_HISTORY".equals(string)) {
                return true;
            }
            if ("EXTRACT".equals(string)) {
                return true;
            }
            if ("EXTRACT_ANSWER (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("EXTRACT_SEMANTIC_CATEGORIES".equals(string)) {
                return true;
            }
            if ("FACTORIAL".equals(string)) {
                return true;
            }
            if ("FILTER".equals(string)) {
                return true;
            }
            if ("FINETUNE ('CANCEL') (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("FINETUNE ('CREATE') (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("FINETUNE ('DESCRIBE') (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("FINETUNE ('SHOW') (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("FINETUNE (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("FIRST_VALUE".equals(string)) {
                return true;
            }
            if ("FLATTEN".equals(string)) {
                return true;
            }
            if ("FLOOR".equals(string)) {
                return true;
            }
            if ("FRESHNESS (system data metric function)".equals(string)) {
                return true;
            }
            if ("GENERATE_COLUMN_DESCRIPTION".equals(string)) {
                return true;
            }
            if ("GENERATOR".equals(string)) {
                return true;
            }
            if ("GET".equals(string)) {
                return true;
            }
            if ("GETBIT".equals(string)) {
                return true;
            }
            if ("GETDATE".equals(string)) {
                return true;
            }
            if ("GETVARIABLE".equals(string)) {
                return true;
            }
            if ("GET_ABSOLUTE_PATH".equals(string)) {
                return true;
            }
            if ("GET_ANACONDA_PACKAGES_REPODATA".equals(string)) {
                return true;
            }
            if ("GET_CONDITION_QUERY_UUID".equals(string)) {
                return true;
            }
            if ("GET_DDL".equals(string)) {
                return true;
            }
            if ("GET_IGNORE_CASE".equals(string)) {
                return true;
            }
            if ("GET_OBJECT_REFERENCES".equals(string)) {
                return true;
            }
            if ("GET_PATH".equals(string)) {
                return true;
            }
            if ("GET_PRESIGNED_URL".equals(string)) {
                return true;
            }
            if ("GET_QUERY_OPERATOR_STATS".equals(string)) {
                return true;
            }
            if ("GET_RELATIVE_PATH".equals(string)) {
                return true;
            }
            if ("GET_STAGE_LOCATION".equals(string)) {
                return true;
            }
            if ("GREATEST".equals(string)) {
                return true;
            }
            if ("GREATEST_IGNORE_NULLS".equals(string)) {
                return true;
            }
            if ("GROUPING".equals(string)) {
                return true;
            }
            if ("GROUPING_ID".equals(string)) {
                return true;
            }
            if ("H3_CELL_TO_BOUNDARY".equals(string)) {
                return true;
            }
            if ("H3_CELL_TO_CHILDREN".equals(string)) {
                return true;
            }
            if ("H3_CELL_TO_CHILDREN_STRING".equals(string)) {
                return true;
            }
            if ("H3_CELL_TO_PARENT".equals(string)) {
                return true;
            }
            if ("H3_CELL_TO_POINT".equals(string)) {
                return true;
            }
            if ("H3_COMPACT_CELLS".equals(string)) {
                return true;
            }
            if ("H3_COMPACT_CELLS_STRINGS".equals(string)) {
                return true;
            }
            if ("H3_COVERAGE".equals(string)) {
                return true;
            }
            if ("H3_COVERAGE_STRINGS".equals(string)) {
                return true;
            }
            if ("H3_GET_RESOLUTION".equals(string)) {
                return true;
            }
            if ("H3_GRID_DISK".equals(string)) {
                return true;
            }
            if ("H3_GRID_DISTANCE".equals(string)) {
                return true;
            }
            if ("H3_GRID_PATH".equals(string)) {
                return true;
            }
            if ("H3_INT_TO_STRING".equals(string)) {
                return true;
            }
            if ("H3_IS_PENTAGON".equals(string)) {
                return true;
            }
            if ("H3_IS_VALID_CELL".equals(string)) {
                return true;
            }
            if ("H3_LATLNG_TO_CELL".equals(string)) {
                return true;
            }
            if ("H3_LATLNG_TO_CELL_STRING".equals(string)) {
                return true;
            }
            if ("H3_POINT_TO_CELL".equals(string)) {
                return true;
            }
            if ("H3_POINT_TO_CELL_STRING".equals(string)) {
                return true;
            }
            if ("H3_POLYGON_TO_CELLS".equals(string)) {
                return true;
            }
            if ("H3_POLYGON_TO_CELLS_STRINGS".equals(string)) {
                return true;
            }
            if ("H3_STRING_TO_INT".equals(string)) {
                return true;
            }
            if ("H3_TRY_COVERAGE".equals(string)) {
                return true;
            }
            if ("H3_TRY_COVERAGE_STRINGS".equals(string)) {
                return true;
            }
            if ("H3_TRY_GRID_DISTANCE".equals(string)) {
                return true;
            }
            if ("H3_TRY_GRID_PATH".equals(string)) {
                return true;
            }
            if ("H3_TRY_POLYGON_TO_CELLS".equals(string)) {
                return true;
            }
            if ("H3_TRY_POLYGON_TO_CELLS_STRINGS".equals(string)) {
                return true;
            }
            if ("H3_UNCOMPACT_CELLS".equals(string)) {
                return true;
            }
            if ("H3_UNCOMPACT_CELLS_STRINGS".equals(string)) {
                return true;
            }
            if ("HASH".equals(string)) {
                return true;
            }
            if ("HASH_AGG".equals(string)) {
                return true;
            }
            if ("HAVERSINE".equals(string)) {
                return true;
            }
            if ("HEX_DECODE_BINARY".equals(string)) {
                return true;
            }
            if ("HEX_DECODE_STRING".equals(string)) {
                return true;
            }
            if ("HEX_ENCODE".equals(string)) {
                return true;
            }
            if ("HLL".equals(string)) {
                return true;
            }
            if ("HLL_ACCUMULATE".equals(string)) {
                return true;
            }
            if ("HLL_COMBINE".equals(string)) {
                return true;
            }
            if ("HLL_ESTIMATE".equals(string)) {
                return true;
            }
            if ("HLL_EXPORT".equals(string)) {
                return true;
            }
            if ("HLL_IMPORT".equals(string)) {
                return true;
            }
            if ("HOUR / MINUTE / SECOND".equals(string)) {
                return true;
            }
            if ("HOUR".equals(string)) {
                return true;
            }
            if ("IFF".equals(string)) {
                return true;
            }
            if ("IFNULL".equals(string)) {
                return true;
            }
            if ("ILIKE ANY".equals(string)) {
                return true;
            }
            if ("INFER_SCHEMA".equals(string)) {
                return true;
            }
            if ("INITCAP".equals(string)) {
                return true;
            }
            if ("INSERT".equals(string)) {
                return true;
            }
            if ("INTEGRATION".equals(string)) {
                return true;
            }
            if ("INVOKER_ROLE".equals(string)) {
                return true;
            }
            if ("INVOKER_SHARE".equals(string)) {
                return true;
            }
            if ("IS [ NOT ] DISTINCT FROM".equals(string)) {
                return true;
            }
            if ("IS [ NOT ] NULL".equals(string)) {
                return true;
            }
            if ("ISNULL".equals(string)) {
                return true;
            }
            if ("IS_ARRAY".equals(string)) {
                return true;
            }
            if ("IS_BINARY".equals(string)) {
                return true;
            }
            if ("IS_BOOLEAN".equals(string)) {
                return true;
            }
            if ("IS_CHAR".equals(string)) {
                return true;
            }
            if ("IS_DATABASE_ROLE_IN_SESSION".equals(string)) {
                return true;
            }
            if ("IS_DATE".equals(string)) {
                return true;
            }
            if ("IS_DATE_VALUE".equals(string)) {
                return true;
            }
            if ("IS_DECIMAL".equals(string)) {
                return true;
            }
            if ("IS_DOUBLE".equals(string)) {
                return true;
            }
            if ("IS_GRANTED_TO_INVOKER_ROLE".equals(string)) {
                return true;
            }
            if ("IS_INSTANCE_ROLE_IN_SESSION".equals(string)) {
                return true;
            }
            if ("IS_INTEGER".equals(string)) {
                return true;
            }
            if ("IS_NULL_VALUE".equals(string)) {
                return true;
            }
            if ("IS_OBJECT".equals(string)) {
                return true;
            }
            if ("IS_REAL".equals(string)) {
                return true;
            }
            if ("IS_ROLE_IN_SESSION".equals(string)) {
                return true;
            }
            if ("IS_TIME".equals(string)) {
                return true;
            }
            if ("IS_TIMESTAMP_LTZ".equals(string)) {
                return true;
            }
            if ("IS_TIMESTAMP_NTZ".equals(string)) {
                return true;
            }
            if ("IS_TIMESTAMP_TZ".equals(string)) {
                return true;
            }
            if ("IS_VARCHAR".equals(string)) {
                return true;
            }
            if ("JAROWINKLER_SIMILARITY".equals(string)) {
                return true;
            }
            if ("JSON_EXTRACT_PATH_TEXT".equals(string)) {
                return true;
            }
            if ("KURTOSIS".equals(string)) {
                return true;
            }
            if ("LAG".equals(string)) {
                return true;
            }
            if ("LAST_DAY".equals(string)) {
                return true;
            }
            if ("LAST_QUERY_ID".equals(string)) {
                return true;
            }
            if ("LAST_SUCCESSFUL_SCHEDULED_TIME".equals(string)) {
                return true;
            }
            if ("LAST_TRANSACTION".equals(string)) {
                return true;
            }
            if ("LAST_VALUE".equals(string)) {
                return true;
            }
            if ("LEAD".equals(string)) {
                return true;
            }
            if ("LEAST".equals(string)) {
                return true;
            }
            if ("LEAST_IGNORE_NULLS".equals(string)) {
                return true;
            }
            if ("LEFT".equals(string)) {
                return true;
            }
            if ("LEN".equals(string)) {
                return true;
            }
            if ("LENGTH".equals(string)) {
                return true;
            }
            if ("LIKE ALL".equals(string)) {
                return true;
            }
            if ("LIKE ANY".equals(string)) {
                return true;
            }
            if ("LISTAGG".equals(string)) {
                return true;
            }
            if ("LN".equals(string)) {
                return true;
            }
            if ("LOCALTIME".equals(string)) {
                return true;
            }
            if ("LOCALTIMESTAMP".equals(string)) {
                return true;
            }
            if ("LOG".equals(string)) {
                return true;
            }
            if ("LOGIN_HISTORY".equals(string)) {
                return true;
            }
            if ("LOGIN_HISTORY_BY_USER".equals(string)) {
                return true;
            }
            if ("LOWER".equals(string)) {
                return true;
            }
            if ("LPAD".equals(string)) {
                return true;
            }
            if ("LTRIM".equals(string)) {
                return true;
            }
            if ("MAP_CAT".equals(string)) {
                return true;
            }
            if ("MAP_CONTAINS_KEY".equals(string)) {
                return true;
            }
            if ("MAP_DELETE".equals(string)) {
                return true;
            }
            if ("MAP_INSERT".equals(string)) {
                return true;
            }
            if ("MAP_KEYS".equals(string)) {
                return true;
            }
            if ("MAP_PICK".equals(string)) {
                return true;
            }
            if ("MAP_SIZE".equals(string)) {
                return true;
            }
            if ("MATERIALIZED_VIEW_REFRESH_HISTORY".equals(string)) {
                return true;
            }
            if ("MAX".equals(string)) {
                return true;
            }
            if ("MAX_BY".equals(string)) {
                return true;
            }
            if ("MD5".equals(string)) {
                return true;
            }
            if ("MD5_BINARY".equals(string)) {
                return true;
            }
            if ("MD5_HEX".equals(string)) {
                return true;
            }
            if ("MD5_NUMBER \u2014 ".equals(string)) {
                return true;
            }
            if ("MD5_NUMBER_LOWER64".equals(string)) {
                return true;
            }
            if ("MD5_NUMBER_UPPER64".equals(string)) {
                return true;
            }
            if ("MEDIAN".equals(string)) {
                return true;
            }
            if ("MIN (system data metric function)".equals(string)) {
                return true;
            }
            if ("MIN".equals(string)) {
                return true;
            }
            if ("MINHASH".equals(string)) {
                return true;
            }
            if ("MINHASH_COMBINE".equals(string)) {
                return true;
            }
            if ("MINUTE".equals(string)) {
                return true;
            }
            if ("MIN_BY".equals(string)) {
                return true;
            }
            if ("MOD".equals(string)) {
                return true;
            }
            if ("MODE".equals(string)) {
                return true;
            }
            if ("MONTH".equals(string)) {
                return true;
            }
            if ("MONTHNAME".equals(string)) {
                return true;
            }
            if ("MONTHS_BETWEEN".equals(string)) {
                return true;
            }
            if ("NETWORK_RULE_REFERENCES".equals(string)) {
                return true;
            }
            if ("NEXT_DAY".equals(string)) {
                return true;
            }
            if ("NORMAL".equals(string)) {
                return true;
            }
            if ("NOTIFICATION_HISTORY".equals(string)) {
                return true;
            }
            if ("NTH_VALUE".equals(string)) {
                return true;
            }
            if ("NTILE".equals(string)) {
                return true;
            }
            if ("NULLIF".equals(string)) {
                return true;
            }
            if ("NULLIFZERO".equals(string)) {
                return true;
            }
            if ("NULL_COUNT (system data metric function)".equals(string)) {
                return true;
            }
            if ("NULL_PERCENT (system data metric function)".equals(string)) {
                return true;
            }
            if ("NVL".equals(string)) {
                return true;
            }
            if ("NVL2".equals(string)) {
                return true;
            }
            if ("OBJECT_AGG".equals(string)) {
                return true;
            }
            if ("OBJECT_CONSTRUCT".equals(string)) {
                return true;
            }
            if ("OBJECT_CONSTRUCT_KEEP_NULL".equals(string)) {
                return true;
            }
            if ("OBJECT_DELETE".equals(string)) {
                return true;
            }
            if ("OBJECT_INSERT".equals(string)) {
                return true;
            }
            if ("OBJECT_KEYS".equals(string)) {
                return true;
            }
            if ("OBJECT_PICK".equals(string)) {
                return true;
            }
            if ("OCTET_LENGTH".equals(string)) {
                return true;
            }
            if ("PARSE_IP".equals(string)) {
                return true;
            }
            if ("PARSE_JSON".equals(string)) {
                return true;
            }
            if ("PARSE_URL".equals(string)) {
                return true;
            }
            if ("PARSE_XML".equals(string)) {
                return true;
            }
            if ("PERCENTILE_CONT".equals(string)) {
                return true;
            }
            if ("PERCENTILE_DISC".equals(string)) {
                return true;
            }
            if ("PERCENT_RANK".equals(string)) {
                return true;
            }
            if ("PI".equals(string)) {
                return true;
            }
            if ("PIPE_USAGE_HISTORY".equals(string)) {
                return true;
            }
            if ("POLICY_CONTEXT".equals(string)) {
                return true;
            }
            if ("POLICY_REFERENCES".equals(string)) {
                return true;
            }
            if ("POSITION".equals(string)) {
                return true;
            }
            if ("POW".equals(string)) {
                return true;
            }
            if ("POWER".equals(string)) {
                return true;
            }
            if ("PREVIOUS_DAY".equals(string)) {
                return true;
            }
            if ("QUARTER".equals(string)) {
                return true;
            }
            if ("QUERY_ACCELERATION_HISTORY".equals(string)) {
                return true;
            }
            if ("QUERY_HISTORY".equals(string)) {
                return true;
            }
            if ("QUERY_HISTORY_BY_SESSION".equals(string)) {
                return true;
            }
            if ("QUERY_HISTORY_BY_USER".equals(string)) {
                return true;
            }
            if ("QUERY_HISTORY_BY_WAREHOUSE".equals(string)) {
                return true;
            }
            if ("RADIANS".equals(string)) {
                return true;
            }
            if ("RANDOM".equals(string)) {
                return true;
            }
            if ("RANDSTR".equals(string)) {
                return true;
            }
            if ("RANK".equals(string)) {
                return true;
            }
            if ("RATIO_TO_REPORT".equals(string)) {
                return true;
            }
            if ("REGEXP_COUNT".equals(string)) {
                return true;
            }
            if ("REGEXP_INSTR".equals(string)) {
                return true;
            }
            if ("REGEXP_LIKE".equals(string)) {
                return true;
            }
            if ("REGEXP_REPLACE".equals(string)) {
                return true;
            }
            if ("REGEXP_SUBSTR".equals(string)) {
                return true;
            }
            if ("REGEXP_SUBSTR_ALL".equals(string)) {
                return true;
            }
            if ("REGR_AVGX".equals(string)) {
                return true;
            }
            if ("REGR_AVGY".equals(string)) {
                return true;
            }
            if ("REGR_COUNT".equals(string)) {
                return true;
            }
            if ("REGR_INTERCEPT".equals(string)) {
                return true;
            }
            if ("REGR_R2".equals(string)) {
                return true;
            }
            if ("REGR_SLOPE".equals(string)) {
                return true;
            }
            if ("REGR_SXX".equals(string)) {
                return true;
            }
            if ("REGR_SXY".equals(string)) {
                return true;
            }
            if ("REGR_SYY".equals(string)) {
                return true;
            }
            if ("REGR_VALX".equals(string)) {
                return true;
            }
            if ("REGR_VALY".equals(string)) {
                return true;
            }
            if ("REPEAT".equals(string)) {
                return true;
            }
            if ("REPLACE".equals(string)) {
                return true;
            }
            if ("REPLICATION_GROUP_REFRESH_HISTORY".equals(string)) {
                return true;
            }
            if ("REPLICATION_GROUP_REFRESH_PROGRESS".equals(string)) {
                return true;
            }
            if ("REPLICATION_GROUP_REFRESH_PROGRESS_BY_JOB".equals(string)) {
                return true;
            }
            if ("REPLICATION_GROUP_USAGE_HISTORY".equals(string)) {
                return true;
            }
            if ("REPLICATION_USAGE_HISTORY".equals(string)) {
                return true;
            }
            if ("REST_EVENT_HISTORY".equals(string)) {
                return true;
            }
            if ("RESULT_SCAN".equals(string)) {
                return true;
            }
            if ("REVERSE".equals(string)) {
                return true;
            }
            if ("RIGHT".equals(string)) {
                return true;
            }
            if ("RLIKE".equals(string)) {
                return true;
            }
            if ("ROUND".equals(string)) {
                return true;
            }
            if ("ROW_COUNT (system data metric function)".equals(string)) {
                return true;
            }
            if ("ROW_NUMBER".equals(string)) {
                return true;
            }
            if ("RPAD".equals(string)) {
                return true;
            }
            if ("RTRIM".equals(string)) {
                return true;
            }
            if ("RTRIMMED_LENGTH".equals(string)) {
                return true;
            }
            if ("SCHEDULED_TIME".equals(string)) {
                return true;
            }
            if ("SEARCH_OPTIMIZATION_HISTORY".equals(string)) {
                return true;
            }
            if ("SECOND".equals(string)) {
                return true;
            }
            if ("SENTIMENT (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("SEQ1".equals(string)) {
                return true;
            }
            if ("SEQ2".equals(string)) {
                return true;
            }
            if ("SEQ4".equals(string)) {
                return true;
            }
            if ("SEQ8".equals(string)) {
                return true;
            }
            if ("SERVERLESS_TASK_HISTORY".equals(string)) {
                return true;
            }
            if ("SHA1".equals(string)) {
                return true;
            }
            if ("SHA1_BINARY".equals(string)) {
                return true;
            }
            if ("SHA1_HEX".equals(string)) {
                return true;
            }
            if ("SHA2".equals(string)) {
                return true;
            }
            if ("SHA2_BINARY".equals(string)) {
                return true;
            }
            if ("SHA2_HEX".equals(string)) {
                return true;
            }
            if ("SHOW_PYTHON_PACKAGES_DEPENDENCIES".equals(string)) {
                return true;
            }
            if ("SIGN".equals(string)) {
                return true;
            }
            if ("SIN".equals(string)) {
                return true;
            }
            if ("SINH".equals(string)) {
                return true;
            }
            if ("SKEW".equals(string)) {
                return true;
            }
            if ("SOUNDEX".equals(string)) {
                return true;
            }
            if ("SOUNDEX_P123".equals(string)) {
                return true;
            }
            if ("SPACE".equals(string)) {
                return true;
            }
            if ("SPLIT".equals(string)) {
                return true;
            }
            if ("SPLIT_PART".equals(string)) {
                return true;
            }
            if ("SPLIT_TO_TABLE".equals(string)) {
                return true;
            }
            if ("SQRT".equals(string)) {
                return true;
            }
            if ("SQUARE".equals(string)) {
                return true;
            }
            if ("STAGE_DIRECTORY_FILE_REGISTRATION_HISTORY".equals(string)) {
                return true;
            }
            if ("STAGE_STORAGE_USAGE_HISTORY".equals(string)) {
                return true;
            }
            if ("STARTSWITH".equals(string)) {
                return true;
            }
            if ("STDDEV (system data metric function)".equals(string)) {
                return true;
            }
            if ("STDDEV".equals(string)) {
                return true;
            }
            if ("STDDEV, STDDEV_SAMP".equals(string)) {
                return true;
            }
            if ("STDDEV_POP".equals(string)) {
                return true;
            }
            if ("STDDEV_SAMP".equals(string)) {
                return true;
            }
            if ("STRIP_NULL_VALUE".equals(string)) {
                return true;
            }
            if ("STRTOK".equals(string)) {
                return true;
            }
            if ("STRTOK_SPLIT_TO_TABLE".equals(string)) {
                return true;
            }
            if ("STRTOK_TO_ARRAY".equals(string)) {
                return true;
            }
            if ("ST_AREA".equals(string)) {
                return true;
            }
            if ("ST_ASBINARY".equals(string)) {
                return true;
            }
            if ("ST_ASEWKB".equals(string)) {
                return true;
            }
            if ("ST_ASEWKT".equals(string)) {
                return true;
            }
            if ("ST_ASGEOJSON".equals(string)) {
                return true;
            }
            if ("ST_ASTEXT".equals(string)) {
                return true;
            }
            if ("ST_ASWKB".equals(string)) {
                return true;
            }
            if ("ST_ASWKT".equals(string)) {
                return true;
            }
            if ("ST_AZIMUTH".equals(string)) {
                return true;
            }
            if ("ST_BUFFER".equals(string)) {
                return true;
            }
            if ("ST_CENTROID".equals(string)) {
                return true;
            }
            if ("ST_COLLECT".equals(string)) {
                return true;
            }
            if ("ST_CONTAINS".equals(string)) {
                return true;
            }
            if ("ST_COVEREDBY".equals(string)) {
                return true;
            }
            if ("ST_COVERS".equals(string)) {
                return true;
            }
            if ("ST_DIFFERENCE".equals(string)) {
                return true;
            }
            if ("ST_DIMENSION".equals(string)) {
                return true;
            }
            if ("ST_DISJOINT".equals(string)) {
                return true;
            }
            if ("ST_DISTANCE".equals(string)) {
                return true;
            }
            if ("ST_DWITHIN".equals(string)) {
                return true;
            }
            if ("ST_ENDPOINT".equals(string)) {
                return true;
            }
            if ("ST_ENVELOPE".equals(string)) {
                return true;
            }
            if ("ST_GEOGFROMGEOHASH".equals(string)) {
                return true;
            }
            if ("ST_GEOGPOINTFROMGEOHASH".equals(string)) {
                return true;
            }
            if ("ST_GEOGRAPHYFROMWKB".equals(string)) {
                return true;
            }
            if ("ST_GEOGRAPHYFROMWKT".equals(string)) {
                return true;
            }
            if ("ST_GEOHASH".equals(string)) {
                return true;
            }
            if ("ST_GEOMETRYFROMWKB".equals(string)) {
                return true;
            }
            if ("ST_GEOMETRYFROMWKT".equals(string)) {
                return true;
            }
            if ("ST_GEOMFROMGEOHASH".equals(string)) {
                return true;
            }
            if ("ST_GEOMPOINTFROMGEOHASH".equals(string)) {
                return true;
            }
            if ("ST_GEOM_POINT".equals(string)) {
                return true;
            }
            if ("ST_HAUSDORFFDISTANCE".equals(string)) {
                return true;
            }
            if ("ST_INTERSECTION".equals(string)) {
                return true;
            }
            if ("ST_INTERSECTION_AGG".equals(string)) {
                return true;
            }
            if ("ST_INTERSECTS".equals(string)) {
                return true;
            }
            if ("ST_ISVALID".equals(string)) {
                return true;
            }
            if ("ST_LENGTH".equals(string)) {
                return true;
            }
            if ("ST_MAKEGEOMPOINT".equals(string)) {
                return true;
            }
            if ("ST_MAKELINE".equals(string)) {
                return true;
            }
            if ("ST_MAKEPOINT".equals(string)) {
                return true;
            }
            if ("ST_MAKEPOLYGON".equals(string)) {
                return true;
            }
            if ("ST_MAKEPOLYGONORIENTED".equals(string)) {
                return true;
            }
            if ("ST_NPOINTS".equals(string)) {
                return true;
            }
            if ("ST_NUMPOINTS".equals(string)) {
                return true;
            }
            if ("ST_PERIMETER".equals(string)) {
                return true;
            }
            if ("ST_POINT".equals(string)) {
                return true;
            }
            if ("ST_POINTN".equals(string)) {
                return true;
            }
            if ("ST_POLYGON".equals(string)) {
                return true;
            }
            if ("ST_SETSRID".equals(string)) {
                return true;
            }
            if ("ST_SIMPLIFY".equals(string)) {
                return true;
            }
            if ("ST_SRID".equals(string)) {
                return true;
            }
            if ("ST_STARTPOINT".equals(string)) {
                return true;
            }
            if ("ST_SYMDIFFERENCE".equals(string)) {
                return true;
            }
            if ("ST_TRANSFORM".equals(string)) {
                return true;
            }
            if ("ST_UNION".equals(string)) {
                return true;
            }
            if ("ST_UNION_AGG".equals(string)) {
                return true;
            }
            if ("ST_WITHIN".equals(string)) {
                return true;
            }
            if ("ST_X".equals(string)) {
                return true;
            }
            if ("ST_XMAX".equals(string)) {
                return true;
            }
            if ("ST_XMIN".equals(string)) {
                return true;
            }
            if ("ST_Y".equals(string)) {
                return true;
            }
            if ("ST_YMAX".equals(string)) {
                return true;
            }
            if ("ST_YMIN".equals(string)) {
                return true;
            }
            if ("SUM".equals(string)) {
                return true;
            }
            if ("SUMMARIZE (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("SYSDATE".equals(string)) {
                return true;
            }
            if ("SYSTEM$ABORT_SESSION".equals(string)) {
                return true;
            }
            if ("SYSTEM$ABORT_TRANSACTION".equals(string)) {
                return true;
            }
            if ("SYSTEM$ADD_EVENT (for Snowflake Scripting)".equals(string)) {
                return true;
            }
            if ("SYSTEM$ALLOWLIST".equals(string)) {
                return true;
            }
            if ("SYSTEM$ALLOWLIST_PRIVATELINK".equals(string)) {
                return true;
            }
            if ("SYSTEM$AUTHORIZE_PRIVATELINK".equals(string)) {
                return true;
            }
            if ("SYSTEM$AUTHORIZE_STAGE_PRIVATELINK_ACCESS".equals(string)) {
                return true;
            }
            if ("SYSTEM$BEHAVIOR_CHANGE_BUNDLE_STATUS".equals(string)) {
                return true;
            }
            if ("SYSTEM$BLOCK_INTERNAL_STAGES_PUBLIC_ACCESS".equals(string)) {
                return true;
            }
            if ("SYSTEM$CANCEL_ALL_QUERIES".equals(string)) {
                return true;
            }
            if ("SYSTEM$CANCEL_QUERY".equals(string)) {
                return true;
            }
            if ("SYSTEM$CLEANUP_DATABASE_ROLE_GRANTS".equals(string)) {
                return true;
            }
            if ("SYSTEM$CLIENT_VERSION_INFO".equals(string)) {
                return true;
            }
            if ("SYSTEM$CLUSTERING_DEPTH".equals(string)) {
                return true;
            }
            if ("SYSTEM$CLUSTERING_INFORMATION".equals(string)) {
                return true;
            }
            if ("SYSTEM$CLUSTERING_RATIO \u2014 ".equals(string)) {
                return true;
            }
            if ("SYSTEM$CONVERT_PIPES_SQS_TO_SNS".equals(string)) {
                return true;
            }
            if ("SYSTEM$CREATE_BILLING_EVENT".equals(string)) {
                return true;
            }
            if ("SYSTEM$CURRENT_USER_TASK_NAME".equals(string)) {
                return true;
            }
            if ("SYSTEM$DATABASE_REFRESH_HISTORY \u2014 ".equals(string)) {
                return true;
            }
            if ("SYSTEM$DATABASE_REFRESH_PROGRESS , SYSTEM$DATABASE_REFRESH_PROGRESS_BY_JOB \u2014 ".equals(string)) {
                return true;
            }
            if ("SYSTEM$DISABLE_BEHAVIOR_CHANGE_BUNDLE".equals(string)) {
                return true;
            }
            if ("SYSTEM$DISABLE_DATABASE_REPLICATION".equals(string)) {
                return true;
            }
            if ("SYSTEM$ENABLE_BEHAVIOR_CHANGE_BUNDLE".equals(string)) {
                return true;
            }
            if ("SYSTEM$ESTIMATE_AUTOMATIC_CLUSTERING_COSTS".equals(string)) {
                return true;
            }
            if ("SYSTEM$ESTIMATE_QUERY_ACCELERATION".equals(string)) {
                return true;
            }
            if ("SYSTEM$ESTIMATE_SEARCH_OPTIMIZATION_COSTS".equals(string)) {
                return true;
            }
            if ("SYSTEM$EXPLAIN_JSON_TO_TEXT".equals(string)) {
                return true;
            }
            if ("SYSTEM$EXPLAIN_PLAN_JSON".equals(string)) {
                return true;
            }
            if ("SYSTEM$EXTERNAL_TABLE_PIPE_STATUS".equals(string)) {
                return true;
            }
            if ("SYSTEM$FINISH_OAUTH_FLOW".equals(string)) {
                return true;
            }
            if ("SYSTEM$GENERATE_SAML_CSR".equals(string)) {
                return true;
            }
            if ("SYSTEM$GENERATE_SCIM_ACCESS_TOKEN".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_AWS_SNS_IAM_POLICY".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_CLASSIFICATION_RESULT".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_CMK_AKV_CONSENT_URL".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_CMK_CONFIG".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_CMK_INFO".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_CMK_KMS_KEY_POLICY".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_COMPUTE_POOL_STATUS".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_DIRECTORY_TABLE_STATUS".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_GCP_KMS_CMK_GRANT_ACCESS_CMD".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_ICEBERG_TABLE_INFORMATION".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_LOGIN_FAILURE_DETAILS".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_PREDECESSOR_RETURN_VALUE".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_PRIVATELINK".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_PRIVATELINK_AUTHORIZED_ENDPOINTS".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_PRIVATELINK_CONFIG".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_SERVICE_LOGS".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_SERVICE_STATUS".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_SNOWFLAKE_PLATFORM_INFO".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_TAG".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_TAG_ALLOWED_VALUES".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_TAG_ON_CURRENT_COLUMN".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_TAG_ON_CURRENT_TABLE".equals(string)) {
                return true;
            }
            if ("SYSTEM$GET_TASK_GRAPH_CONFIG".equals(string)) {
                return true;
            }
            if ("SYSTEM$GLOBAL_ACCOUNT_SET_PARAMETER".equals(string)) {
                return true;
            }
            if ("SYSTEM$INTERNAL_STAGES_PUBLIC_ACCESS_STATUS".equals(string)) {
                return true;
            }
            if ("SYSTEM$IS_APPLICATION_INSTALLED_FROM_SAME_ACCOUNT".equals(string)) {
                return true;
            }
            if ("SYSTEM$IS_APPLICATION_SHARING_EVENTS_WITH_PROVIDER".equals(string)) {
                return true;
            }
            if ("SYSTEM$LAST_CHANGE_COMMIT_TIME".equals(string)) {
                return true;
            }
            if ("SYSTEM$LINK_ACCOUNT_OBJECTS_BY_NAME".equals(string)) {
                return true;
            }
            if ("SYSTEM$LIST_APPLICATION_RESTRICTED_FEATURES".equals(string)) {
                return true;
            }
            if ("SYSTEM$LOG, SYSTEM$LOG_<level> (for Snowflake Scripting)".equals(string)) {
                return true;
            }
            if ("SYSTEM$MIGRATE_SAML_IDP_REGISTRATION".equals(string)) {
                return true;
            }
            if ("SYSTEM$PIPE_FORCE_RESUME".equals(string)) {
                return true;
            }
            if ("SYSTEM$PIPE_REBINDING_WITH_NOTIFICATION_CHANNEL".equals(string)) {
                return true;
            }
            if ("SYSTEM$PIPE_STATUS".equals(string)) {
                return true;
            }
            if ("SYSTEM$QUERY_REFERENCE".equals(string)) {
                return true;
            }
            if ("SYSTEM$REFERENCE".equals(string)) {
                return true;
            }
            if ("SYSTEM$REGISTER_CMK_INFO".equals(string)) {
                return true;
            }
            if ("SYSTEM$REGISTRY_LIST_IMAGES \u2014 ".equals(string)) {
                return true;
            }
            if ("SYSTEM$REVOKE_PRIVATELINK".equals(string)) {
                return true;
            }
            if ("SYSTEM$REVOKE_STAGE_PRIVATELINK_ACCESS".equals(string)) {
                return true;
            }
            if ("SYSTEM$SET_APPLICATION_RESTRICTED_FEATURE_ACCESS".equals(string)) {
                return true;
            }
            if ("SYSTEM$SET_EVENT_SHARING_ACCOUNT_FOR_REGION".equals(string)) {
                return true;
            }
            if ("SYSTEM$SET_RETURN_VALUE".equals(string)) {
                return true;
            }
            if ("SYSTEM$SET_SPAN_ATTRIBUTES (for Snowflake Scripting)".equals(string)) {
                return true;
            }
            if ("SYSTEM$SHOW_ACTIVE_BEHAVIOR_CHANGE_BUNDLES".equals(string)) {
                return true;
            }
            if ("SYSTEM$SHOW_BUDGETS_IN_ACCOUNT".equals(string)) {
                return true;
            }
            if ("SYSTEM$SHOW_EVENT_SHARING_ACCOUNTS".equals(string)) {
                return true;
            }
            if ("SYSTEM$SHOW_OAUTH_CLIENT_SECRETS".equals(string)) {
                return true;
            }
            if ("SYSTEM$SNOWPIPE_STREAMING_UPDATE_CHANNEL_OFFSET_TOKEN".equals(string)) {
                return true;
            }
            if ("SYSTEM$START_OAUTH_FLOW".equals(string)) {
                return true;
            }
            if ("SYSTEM$STREAM_BACKLOG".equals(string)) {
                return true;
            }
            if ("SYSTEM$STREAM_GET_TABLE_TIMESTAMP".equals(string)) {
                return true;
            }
            if ("SYSTEM$STREAM_HAS_DATA".equals(string)) {
                return true;
            }
            if ("SYSTEM$TASK_DEPENDENTS_ENABLE".equals(string)) {
                return true;
            }
            if ("SYSTEM$TASK_RUNTIME_INFO".equals(string)) {
                return true;
            }
            if ("SYSTEM$TYPEOF".equals(string)) {
                return true;
            }
            if ("SYSTEM$UNBLOCK_INTERNAL_STAGES_PUBLIC_ACCESS".equals(string)) {
                return true;
            }
            if ("SYSTEM$UNSET_EVENT_SHARING_ACCOUNT_FOR_REGION".equals(string)) {
                return true;
            }
            if ("SYSTEM$USER_TASK_CANCEL_ONGOING_EXECUTIONS".equals(string)) {
                return true;
            }
            if ("SYSTEM$VALIDATE_STORAGE_INTEGRATION".equals(string)) {
                return true;
            }
            if ("SYSTEM$VERIFY_CMK_INFO".equals(string)) {
                return true;
            }
            if ("SYSTEM$VERIFY_EXTERNAL_OAUTH_TOKEN".equals(string)) {
                return true;
            }
            if ("SYSTEM$WAIT".equals(string)) {
                return true;
            }
            if ("SYSTEM$WHITELIST \u2014 ".equals(string)) {
                return true;
            }
            if ("SYSTEM$WHITELIST_PRIVATELINK \u2014 ".equals(string)) {
                return true;
            }
            if ("SYSTIMESTAMP".equals(string)) {
                return true;
            }
            if ("TAG_REFERENCES".equals(string)) {
                return true;
            }
            if ("TAG_REFERENCES_ALL_COLUMNS".equals(string)) {
                return true;
            }
            if ("TAG_REFERENCES_WITH_LINEAGE".equals(string)) {
                return true;
            }
            if ("TAN".equals(string)) {
                return true;
            }
            if ("TANH".equals(string)) {
                return true;
            }
            if ("TASK_DEPENDENTS".equals(string)) {
                return true;
            }
            if ("TASK_HISTORY".equals(string)) {
                return true;
            }
            if ("TEXT_HTML".equals(string)) {
                return true;
            }
            if ("TEXT_PLAIN".equals(string)) {
                return true;
            }
            if ("TIME".equals(string)) {
                return true;
            }
            if ("TIMEADD".equals(string)) {
                return true;
            }
            if ("TIMEDIFF".equals(string)) {
                return true;
            }
            if ("TIMESTAMPADD".equals(string)) {
                return true;
            }
            if ("TIMESTAMPDIFF".equals(string)) {
                return true;
            }
            if ("TIMESTAMP_FROM_PARTS".equals(string)) {
                return true;
            }
            if ("TIMESTAMP_LTZ_FROM_PARTS".equals(string)) {
                return true;
            }
            if ("TIMESTAMP_NTZ_FROM_PARTS".equals(string)) {
                return true;
            }
            if ("TIMESTAMP_TZ_FROM_PARTS".equals(string)) {
                return true;
            }
            if ("TIME_FROM_PARTS".equals(string)) {
                return true;
            }
            if ("TIME_SLICE".equals(string)) {
                return true;
            }
            if ("TOP_INSIGHTS (SNOWFLAKE.ML)".equals(string)) {
                return true;
            }
            if ("TO_ARRAY".equals(string)) {
                return true;
            }
            if ("TO_BINARY".equals(string)) {
                return true;
            }
            if ("TO_BOOLEAN".equals(string)) {
                return true;
            }
            if ("TO_CHAR".equals(string)) {
                return true;
            }
            if ("TO_DATE , DATE".equals(string)) {
                return true;
            }
            if ("TO_DATE".equals(string)) {
                return true;
            }
            if ("TO_DECIMAL".equals(string)) {
                return true;
            }
            if ("TO_DOUBLE".equals(string)) {
                return true;
            }
            if ("TO_GEOGRAPHY".equals(string)) {
                return true;
            }
            if ("TO_GEOMETRY".equals(string)) {
                return true;
            }
            if ("TO_JSON".equals(string)) {
                return true;
            }
            if ("TO_NUMBER".equals(string)) {
                return true;
            }
            if ("TO_NUMERIC".equals(string)) {
                return true;
            }
            if ("TO_OBJECT".equals(string)) {
                return true;
            }
            if ("TO_QUERY".equals(string)) {
                return true;
            }
            if ("TO_TIME , TIME".equals(string)) {
                return true;
            }
            if ("TO_TIME".equals(string)) {
                return true;
            }
            if ("TO_TIMESTAMP".equals(string)) {
                return true;
            }
            if ("TO_TIMESTAMP_LTZ".equals(string)) {
                return true;
            }
            if ("TO_TIMESTAMP_NTZ".equals(string)) {
                return true;
            }
            if ("TO_TIMESTAMP_TZ".equals(string)) {
                return true;
            }
            if ("TO_VARCHAR".equals(string)) {
                return true;
            }
            if ("TO_VARIANT".equals(string)) {
                return true;
            }
            if ("TO_XML".equals(string)) {
                return true;
            }
            if ("TRANSFORM".equals(string)) {
                return true;
            }
            if ("TRANSLATE".equals(string)) {
                return true;
            }
            if ("TRIM".equals(string)) {
                return true;
            }
            if ("TRUNC".equals(string)) {
                return true;
            }
            if ("TRUNCATE".equals(string)) {
                return true;
            }
            if ("TRY_BASE64_DECODE_BINARY".equals(string)) {
                return true;
            }
            if ("TRY_BASE64_DECODE_STRING".equals(string)) {
                return true;
            }
            if ("TRY_CAST".equals(string)) {
                return true;
            }
            if ("TRY_COMPLETE (SNOWFLAKE.CORTEX)".equals(string)) {
                return true;
            }
            if ("TRY_DECRYPT".equals(string)) {
                return true;
            }
            if ("TRY_DECRYPT_RAW".equals(string)) {
                return true;
            }
            if ("TRY_HEX_DECODE_BINARY".equals(string)) {
                return true;
            }
            if ("TRY_HEX_DECODE_STRING".equals(string)) {
                return true;
            }
            if ("TRY_PARSE_JSON".equals(string)) {
                return true;
            }
            if ("TRY_TO_BINARY".equals(string)) {
                return true;
            }
            if ("TRY_TO_BOOLEAN".equals(string)) {
                return true;
            }
            if ("TRY_TO_DATE".equals(string)) {
                return true;
            }
            if ("TRY_TO_DECIMAL".equals(string)) {
                return true;
            }
            if ("TRY_TO_DOUBLE".equals(string)) {
                return true;
            }
            if ("TRY_TO_GEOGRAPHY".equals(string)) {
                return true;
            }
            if ("TRY_TO_GEOMETRY".equals(string)) {
                return true;
            }
            if ("TRY_TO_NUMBER".equals(string)) {
                return true;
            }
            if ("TRY_TO_NUMERIC".equals(string)) {
                return true;
            }
            if ("TRY_TO_TIME".equals(string)) {
                return true;
            }
            if ("TRY_TO_TIMESTAMP / TRY_TO_TIMESTAMP_*".equals(string)) {
                return true;
            }
            if ("TRY_TO_TIMESTAMP".equals(string)) {
                return true;
            }
            if ("TRY_TO_TIMESTAMP_LTZ".equals(string)) {
                return true;
            }
            if ("TRY_TO_TIMESTAMP_NTZ".equals(string)) {
                return true;
            }
            if ("TRY_TO_TIMESTAMP_TZ".equals(string)) {
                return true;
            }
            if ("TYPEOF".equals(string)) {
                return true;
            }
            if ("UNICODE".equals(string)) {
                return true;
            }
            if ("UNIFORM".equals(string)) {
                return true;
            }
            if ("UNIQUE_COUNT (system data metric function)".equals(string)) {
                return true;
            }
            if ("UPPER".equals(string)) {
                return true;
            }
            if ("UUID_STRING".equals(string)) {
                return true;
            }
            if ("VALIDATE".equals(string)) {
                return true;
            }
            if ("VALIDATE_PIPE_LOAD".equals(string)) {
                return true;
            }
            if ("VARIANCE".equals(string)) {
                return true;
            }
            if ("VARIANCE_POP".equals(string)) {
                return true;
            }
            if ("VARIANCE_SAMP".equals(string)) {
                return true;
            }
            if ("VAR_POP".equals(string)) {
                return true;
            }
            if ("VAR_SAMP".equals(string)) {
                return true;
            }
            if ("VECTOR_COSINE_SIMILARITY".equals(string)) {
                return true;
            }
            if ("VECTOR_INNER_PRODUCT".equals(string)) {
                return true;
            }
            if ("VECTOR_L2_DISTANCE".equals(string)) {
                return true;
            }
            if ("WAREHOUSE_LOAD_HISTORY".equals(string)) {
                return true;
            }
            if ("WAREHOUSE_METERING_HISTORY".equals(string)) {
                return true;
            }
            if ("WEEK".equals(string)) {
                return true;
            }
            if ("WEEKISO".equals(string)) {
                return true;
            }
            if ("WEEKOFYEAR".equals(string)) {
                return true;
            }
            if ("WIDTH_BUCKET".equals(string)) {
                return true;
            }
            if ("XMLGET".equals(string)) {
                return true;
            }
            if ("YEAR".equals(string)) {
                return true;
            }
            if ("YEAROFWEEK".equals(string)) {
                return true;
            }
            if ("YEAROFWEEKISO".equals(string)) {
                return true;
            }
            if ("ZEROIFNULL".equals(string)) {
                return true;
            }
            if ("ZIPF".equals(string)) {
                return true;
            }
            if ("[ NOT ] BETWEEN".equals(string)) {
                return true;
            }
            if ("[ NOT ] ILIKE".equals(string)) {
                return true;
            }
            if ("[ NOT ] IN".equals(string)) {
                return true;
            }
            if ("[ NOT ] LIKE".equals(string)) {
                return true;
            }
            if ("[ NOT ] REGEXP".equals(string)) {
                return true;
            }
            return "[ NOT ] RLIKE".equals(string);
        }
    };

    @Override
    public Option<FunctionDefinition> functionDefinition(String name) {
        return this.SnowflakeFunctionDefinitionPf.orElse(this.commonFunctionsPf()).lift().apply(name.toUpperCase());
    }

    @Override
    public Expression applyConversionStrategy(FunctionDefinition functionArity, Seq<Expression> args, String irName) {
        Option<ConversionStrategy> option = functionArity.conversionStrategy();
        if (option instanceof Some) {
            Some some = (Some)option;
            ConversionStrategy strategy = (ConversionStrategy)some.value();
            return strategy.convert(irName, args);
        }
        return new CallFunction(irName, args);
    }
}

