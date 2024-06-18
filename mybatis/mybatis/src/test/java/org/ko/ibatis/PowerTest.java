package org.ko.ibatis;

import org.junit.Test;

public class PowerTest {

    private static final Integer ZERO = 0;

    @Test
    public void test1 () {
        String result = "{song:zhi}";

        boolean flag = ZERO.equals(result.indexOf("{")) && new Integer(result.length() - 1).equals(result.lastIndexOf("}"));

        System.out.println(result.indexOf("{"));
        System.out.println(result.lastIndexOf("}"));
        System.out.println(result.length());
        System.out.println(flag);
    }
}
