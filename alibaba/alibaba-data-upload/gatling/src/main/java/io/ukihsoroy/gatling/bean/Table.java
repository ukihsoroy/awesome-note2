package io.ukihsoroy.gatling.bean;

import java.util.List;

/**
 * description: Table <br>
 * date: 2020/5/29 23:24 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Table {

    private String name;

    private String entityName;

    private String comment;

    List<Column> columns;

    public Table(String name, String entityName, String comment, List<Column> columns) {
        this.name = name;
        this.entityName = entityName;
        this.comment = comment;
        this.columns = columns;
    }

    public Table() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
