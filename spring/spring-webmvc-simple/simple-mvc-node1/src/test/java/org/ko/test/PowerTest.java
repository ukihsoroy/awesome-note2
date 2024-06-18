package org.ko.test;


import org.junit.jupiter.api.Test;

public class PowerTest {
	
	@Test
	public void test1 () {
		String a = replaceTo("org.ko");
		System.out.println(a);
	}
	
	private String replaceTo(String path) {  
        return path.replaceAll("\\.", "/");  
    }  
	
	@Test public void test2 () {
		String a = "";
		System.out.println(a.trim());
		System.out.println(a != null && a.trim() != "");
	}
}
