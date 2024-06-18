package io.ukihsoroy.schemagen.source.mysql;

public class MysqlConstants {

    public static final String INFORMATION_SCHEMA_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?";

    public static final String INFORMATION_SCHEMA_COLUMNS = "SELECT * FROM information_schema.columns WHERE table_schema = ? AND table_name= ? and COLUMN_NAME not in ('version', 'enable', 'create_user', 'gmt_create', 'modified_user', 'gmt_modified')";

    public static final String INFORMATION_SCHEMA_TABLE_DETAIL = "SELECT table_comment FROM information_schema.tables WHERE table_schema = ? and table_name = ?";

    public static final String COLUMN_NAME = "COLUMN_NAME";

    public static final String COLUMN_COMMENT = "COLUMN_COMMENT";

    public static final String DATA_TYPE = "DATA_TYPE";

    public static final String COLUMN_KEY = "COLUMN_KEY";

    public static final String PRI = "PRI";

    public static final String CHARACTER_MAXIMUM_LENGTH = "CHARACTER_MAXIMUM_LENGTH";

    public static final String NUMERIC_PRECISION = "NUMERIC_PRECISION";

    public static final String NUMERIC_SCALE = "NUMERIC_SCALE";

    public static final String TABLE_COMMENT = "TABLE_COMMENT";

}
