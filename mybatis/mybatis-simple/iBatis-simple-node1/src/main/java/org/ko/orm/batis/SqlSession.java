package org.ko.orm.batis;

import java.lang.reflect.Proxy;

public class SqlSession {

	private Executor executor= new SimpleExecutor();
	
	//TODO configuration
	
	public <T> T selectOne (String statement, Object parameter) {
		return executor.query(statement, parameter);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getMapper (Class<T> clazz) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MapperProxy<T>(this, clazz));
	}
}
