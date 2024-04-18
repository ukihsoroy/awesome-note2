package org.ko.orm.batis;

public interface Executor {
	<E> E query(String statement, Object parameter);
}
