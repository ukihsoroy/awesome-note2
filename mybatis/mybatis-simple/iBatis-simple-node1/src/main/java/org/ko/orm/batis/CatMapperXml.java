package org.ko.orm.batis;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
public class CatMapperXml {
	
	public static final String nameSpace = "org.ko.orm.batis.CatMapper";
	
	public static final Map<String, String> methodSqlMapping = new HashMap<String, String>();
	
	static {
		methodSqlMapping.put("selectByPrimaryKey", "SELECT * FROM cat WHERE id = %d");
	}
}
