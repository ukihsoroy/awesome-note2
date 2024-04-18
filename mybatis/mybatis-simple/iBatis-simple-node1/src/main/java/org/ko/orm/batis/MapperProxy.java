package org.ko.orm.batis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {

	private final SqlSession sqlSession;
	
//	private final Class<T> mapperInterface;

	public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
		this.sqlSession = sqlSession;
//		this.mapperInterface = mapperInterface;
	}



	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getDeclaringClass().getName().equals(CatMapperXml.nameSpace)) {
			String sql = CatMapperXml.methodSqlMapping.get(method.getName());
			System.out.println(String.format("SQL [ %s ]", sql, args[0]));
			return sqlSession.selectOne(sql, String.valueOf(args[0]));
		}
		return null;
	}

}
