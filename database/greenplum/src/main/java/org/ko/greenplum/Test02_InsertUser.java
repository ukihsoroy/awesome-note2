package org.ko.greenplum;

import org.ko.greenplum.helper.GreenplumHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * description: 插入用户数据 <br>
 * date: 5/5/2020 11:19 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Test02_InsertUser {

    public static void main(String[] args) throws SQLException {
        Connection db = GreenplumHelper.getInstance().getConnection();
        Statement statement = db.createStatement();
        int i = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (;;i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            statement.execute("INSERT INTO t_user(" +
                              "id, " +
                              "username, " +
                              "password, " +
                              "email, " +
                              "phone, " +
                              "gender, " +
                              "birthday, " +
                              "country, " +
                              "province, " +
                              "city, " +
                              "area) " +
                              "values("
                              + i //id
                              + ",'sigma" + i //username
                              + "', '123456'" //password
                              + ", 'ko.shen@hotmail.com'" //email
                              + ", '13604261402'" // phone
                              + "," + (i % 2) //gender
                              + ",'" + simpleDateFormat.format(calendar.getTime()) + "'" //birthday
                              + ", '国家'"
                              + ", '省份'"
                              + ", '城市'"
                              + ", '市区'"
                              + ")");
        }
    }
}
