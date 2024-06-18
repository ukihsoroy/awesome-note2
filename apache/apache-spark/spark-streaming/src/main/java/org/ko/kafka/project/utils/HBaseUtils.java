package org.ko.kafka.project.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * HBase操作工具类
 */
public class HBaseUtils {

    private static Connection connection =null;

    private HBaseUtils() {
        Configuration configuration = HBaseConfiguration.create();

        configuration.set("hbase.zookeeper.quorum", "master");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.master", "master");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HBaseUtils instance = null;

    public static synchronized HBaseUtils getInstance() {
        if (null == instance) {
            instance = new HBaseUtils();
        }
        return instance;
    }

    /**
     * 根据表明获取到HTable实例
     * @param tableName
     * @return
     */
    public Table getTable (String tableName) {
        try {
            return connection.getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加一条记录到HBase表
     * @param tableName HBase表名
     * @param rowKey HBase表的RowKey
     * @param cf HBase的ColumnFamily
     * @param column HBase表的列
     * @param value 写入HBase表的值
     */
    public void put (String tableName,
                    String rowKey,
                    String cf,
                    String column,
                    String value) {
        Table table = getTable(tableName);
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(column), Bytes.toBytes(value));
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Table table = HBaseUtils.getInstance().getTable("course_clickcount");
        System.out.println(table.getName().getNameAsString());
        try {
//            Put put = new Put("11".getBytes());
//            put.addColumn("info".getBytes(), "name".getBytes(), "K.O".getBytes());
//            table.put(put);
            table.get(new Get("first".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tableName = "course_clickcount";
        String rowKey = "20181111_08";
        String cf = "info";
        String column = "click_count";
        String value = "2";
        HBaseUtils.getInstance().put(tableName, rowKey, cf, column, value);
    }
}
