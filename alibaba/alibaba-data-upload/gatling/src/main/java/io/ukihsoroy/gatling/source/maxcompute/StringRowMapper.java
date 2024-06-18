package io.ukihsoroy.gatling.source.maxcompute;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据转成1行字符串，不带类型
 * @author K.O
 */
public class StringRowMapper implements RowMapper<String> {
    @Override
    public String mapRow(ResultSet rs, int i) throws SQLException {
        int count = rs.getMetaData().getColumnCount();
        System.out.println(count);
        StringBuilder line = new StringBuilder("|");
        for(int index = 1; index <= count; index ++) {
            line.append(rs.getString(index)).append("|");
        }
        return line.toString();
    }
}
