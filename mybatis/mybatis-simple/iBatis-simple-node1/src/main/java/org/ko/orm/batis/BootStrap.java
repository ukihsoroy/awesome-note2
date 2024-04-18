package org.ko.orm.batis;

public class BootStrap {

	public static void start () {
		SqlSession session = new SqlSession();
		CatMapper cm = session.getMapper(CatMapper.class);
		Cat cat = cm.selectByPrimaryKey(1);
		System.out.println(cat.getName());
	}
	
	public static void main (String[] args) {
		 start ();
	}
}
