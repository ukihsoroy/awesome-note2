package io.ukihsoroy.schemagen.bean;

public class Column {

    public Column(String columnName, String propertyName, String columnType, String propertyType, String odpsType, boolean primaryKey, Integer length, String comment) {
        this.columnName = columnName;
        this.propertyName = propertyName;
        this.columnType = columnType;
        this.propertyType = propertyType;
        this.odpsType = odpsType;
        this.primaryKey = primaryKey;
        this.length = length;
        this.comment = comment;
    }

    private String columnName;
    private String propertyName;
    private String columnType;
    private String propertyType;
    private String odpsType;
    private boolean primaryKey;
    private Integer length;
    private String comment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getOdpsType() {
        return odpsType;
    }

    public void setOdpsType(String odpsType) {
        this.odpsType = odpsType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
