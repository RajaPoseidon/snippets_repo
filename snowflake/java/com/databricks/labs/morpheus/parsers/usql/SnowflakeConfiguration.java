/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.parsers.usql;

import com.databricks.labs.morpheus.parsers.usql.Dialects;
import com.databricks.labs.morpheus.parsers.usql.RecognizerConfiguration;

public class SnowflakeConfiguration
extends RecognizerConfiguration {
    public SnowflakeConfiguration() {
        this.dialect = Dialects.SNOWFLAKE;
        this.lexArrow = true;
        this.lexAssoc = true;
        this.lexSingleOpTokens = true;
        this.lexAmpersand = true;
        this.lexBraces = true;
        this.lexBrackets = true;
        this.lexQPlaceholders = true;
        this.lexTilde = true;
        this.lexDParameters = true;
        this.lexSnowStrings = true;
        this.lexRawDollarStrings = true;
        this.lexCAssign = true;
        this.lexDoubleQId = true;
    }
}

