package org.ko.orm.batis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SimpleExecutor implements Executor{

	@SuppressWarnings("unchecked")
	public <E> E query(String sql, Object parameter) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			JdbcUtils jdbc = JdbcUtils.getInstance();
			conn = jdbc.getConnection();
			ps = conn.prepareStatement(String.format(sql, Integer.parseInt(String.valueOf(parameter))));
			rs = ps.executeQuery();
			Cat cat = new Cat();
			while (rs.next()) {
				cat.setId(rs.getInt(1));
				cat.setName(rs.getString(2));
			}
			return (E) cat;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		
		return null;
	}

}
