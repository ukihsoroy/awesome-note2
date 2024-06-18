package org.ko.greenplum;

import org.ko.greenplum.helper.GreenplumHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description: 插入订单数据 <br>
 * date: 5/5/2020 11:19 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Test02_InsertOrder {

    public static void main(String[] args) throws SQLException {
        Connection db = GreenplumHelper.getInstance().getConnection();
        Statement statement = db.createStatement();
        int i = 2;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (;;i++) {
            if (i % 10000 == 0) calendar.add(Calendar.DAY_OF_MONTH, 1);
            statement.execute("INSERT INTO t_order(id, goods_id, user_id, count, time) " +
                              "values(" + i + ", " + i + ", " + i + "," + i + ",'" + simpleDateFormat.format(calendar.getTime()) + "')");
        }
    }
}
