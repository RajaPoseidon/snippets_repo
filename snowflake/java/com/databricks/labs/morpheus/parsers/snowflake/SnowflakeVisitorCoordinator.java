/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.parsers.snowflake;

import com.databricks.labs.morpheus.parsers.VisitorCoordinator;
import com.databricks.labs.morpheus.parsers.snowflake.SnowflakeDataTypeBuilder;
import com.databricks.labs.morpheus.parsers.snowflake.SnowflakeFunctionBuilder;
import com.databricks.labs.morpheus.parsers.usql.RecognizerConfiguration;
import org.antlr.v4.runtime.Vocabulary;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes="\u0006\u0001a3A!\u0003\u0006\u0001/!IA\u0004\u0001B\u0001B\u0003%Qd\t\u0005\nI\u0001\u0011\t\u0011)A\u0005KEB\u0011B\r\u0001\u0003\u0002\u0003\u0006Ia\r#\t\u000b\u0015\u0003A\u0011\u0001$\t\u000f1\u0003!\u0019!C!\u001b\"1\u0011\u000b\u0001Q\u0001\n9CqA\u0015\u0001C\u0002\u0013\u00053\u000b\u0003\u0004X\u0001\u0001\u0006I\u0001\u0016\u0002\u001c':|wO\u001a7bW\u00164\u0016n]5u_J\u001cun\u001c:eS:\fGo\u001c:\u000b\u0005-a\u0011!C:o_^4G.Y6f\u0015\tia\"A\u0004qCJ\u001cXM]:\u000b\u0005=\u0001\u0012\u0001C7peBDW-^:\u000b\u0005E\u0011\u0012\u0001\u00027bENT!a\u0005\u000b\u0002\u0015\u0011\fG/\u00192sS\u000e\\7OC\u0001\u0016\u0003\r\u0019w.\\\u0002\u0001'\t\u0001\u0001\u0004\u0005\u0002\u001a55\tA\"\u0003\u0002\u001c\u0019\t\u0011b+[:ji>\u00148i\\8sI&t\u0017\r^8s\u0003\u0019\u0019wN\u001c4jOB\u0011a$I\u0007\u0002?)\u0011\u0001\u0005D\u0001\u0005kN\fH.\u0003\u0002#?\t9\"+Z2pO:L'0\u001a:D_:4\u0017nZ;sCRLwN\\\u0005\u00039i\t1\u0002]1sg\u0016\u0014hk\\2bEB\u0011aeL\u0007\u0002O)\u0011\u0001&K\u0001\beVtG/[7f\u0015\tQ3&\u0001\u0002wi)\u0011A&L\u0001\u0006C:$HN\u001d\u0006\u0002]\u0005\u0019qN]4\n\u0005A:#A\u0003,pG\u0006\u0014W\u000f\\1ss&\u0011AEG\u0001\neVdWMT1nKN\u00042\u0001N\u001c:\u001b\u0005)$\"\u0001\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005a*$!B!se\u0006L\bC\u0001\u001eB\u001d\tYt\b\u0005\u0002=k5\tQH\u0003\u0002?-\u00051AH]8pizJ!\u0001Q\u001b\u0002\rA\u0013X\rZ3g\u0013\t\u00115I\u0001\u0004TiJLgn\u001a\u0006\u0003\u0001VJ!A\r\u000e\u0002\rqJg.\u001b;?)\u00119\u0015JS&\u0011\u0005!\u0003Q\"\u0001\u0006\t\u000bq!\u0001\u0019A\u000f\t\u000b\u0011\"\u0001\u0019A\u0013\t\u000bI\"\u0001\u0019A\u001a\u0002\u001f\u0011\fG/\u0019+za\u0016\u0014U/\u001b7eKJ,\u0012A\u0014\t\u0003\u0011>K!\u0001\u0015\u0006\u00031Mswn\u001e4mC.,G)\u0019;b)f\u0004XMQ;jY\u0012,'/\u0001\teCR\fG+\u001f9f\u0005VLG\u000eZ3sA\u0005ya-\u001e8di&|gNQ;jY\u0012,'/F\u0001U!\tAU+\u0003\u0002W\u0015\tA2K\\8xM2\f7.\u001a$v]\u000e$\u0018n\u001c8Ck&dG-\u001a:\u0002!\u0019,hn\u0019;j_:\u0014U/\u001b7eKJ\u0004\u0003")
public class SnowflakeVisitorCoordinator
extends VisitorCoordinator {
    private final SnowflakeDataTypeBuilder dataTypeBuilder = new SnowflakeDataTypeBuilder();
    private final SnowflakeFunctionBuilder functionBuilder = new SnowflakeFunctionBuilder();

    @Override
    public SnowflakeDataTypeBuilder dataTypeBuilder() {
        return this.dataTypeBuilder;
    }

    @Override
    public SnowflakeFunctionBuilder functionBuilder() {
        return this.functionBuilder;
    }

    public SnowflakeVisitorCoordinator(RecognizerConfiguration config, Vocabulary parserVocab, String[] ruleNames) {
        super(config, parserVocab, ruleNames);
    }
}

