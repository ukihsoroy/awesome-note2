package org.ko.greenplum;

import org.ko.greenplum.helper.GreenplumHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description: 插入商品数据 <br>
 * date: 5/5/2020 11:19 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Test02_InsertGoods {

    public static void main(String[] args) throws SQLException {
        Connection db = GreenplumHelper.getInstance().getConnection();
        Statement statement = db.createStatement();
        int i = 51920;
        for (;;i++) {
            statement.execute("INSERT INTO t_goods(id, name, stock) values(" + i + ", '商品" + i + "', " + i + ")");
        }
    }
}
