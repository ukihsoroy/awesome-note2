package io.ukihsoroy.schemagen.source.mysql;

import java.util.HashMap;
import java.util.Map;

/**
 * @author K.O
 */
public class MysqlConverterOdpsTypeHandler {

    private static final Map<String, String> container = new HashMap<String, String>();

    static {
        container.put("varchar", "STRING");
        container.put("char", "STRING");
        container.put("text", "STRING");
        container.put("int", "INT");
        container.put("tinyint", "TINYINT");
        container.put("smallint", "SMALLINT");
        container.put("mediumint", "INT");
        container.put("bigint", "BIGINT");
        container.put("float", "FLOAT");
        container.put("double", "DOUBLE");
        container.put("decimal", "DECIMAL");
        container.put("numeric", "DECIMAL");
        container.put("date", "DATE");
        container.put("datetime", "DATETIME");
        container.put("timestamp", "TIMESTAMP");
        container.put("time", "TIME");
        container.put("json", "JSON");
    }

    public static String format(String key) {
        return container.get(key);
    }

}
