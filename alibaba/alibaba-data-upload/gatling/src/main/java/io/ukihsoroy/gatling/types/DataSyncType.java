package io.ukihsoroy.gatling.types;

/**
 * 数据同步类别
 * @author K.O
 */
public enum DataSyncType {

    /**
     * 数据同步
     */
    STREAMX("streamx"),
    DATAX("datax"),
    JDBC("jdbc"),
    SQL("sql")
    ;

    DataSyncType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
