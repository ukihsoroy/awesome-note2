package org.ko.problems;

import org.junit.jupiter.api.Test;

/**
 * 重新格式化电话号码
 */
public class P1694_ReformatNumber {

    public String reformatNumber(String s) {
        String s1 = s.replace("-", "").replace(" ", "");
        StringBuilder builder = new StringBuilder();
        int o = s1.length() % 3;
        if (o == 1) {
            o = 4;
        }

        int before = s1.length() - o;
        for (int i = 0; i < before; i+=3) {
            builder.append(s1, i, i + 3).append("-");
        }
        if (o == 4) {
            builder.append(s1, before, before + 2).append("-").append(s1.substring(before+2));
        } else if (o == 0) {
            builder.delete(builder.length() - 1, builder.length());
        } else {
            builder.append(s1.substring(before));
        }

        return builder.toString();
    }

    @Test
    public void test1() {
        String s = reformatNumber("1-23-45 6");
        System.out.println(s);
    }

    @Test
    public void test2() {
        String s = reformatNumber("123 4-567");
        System.out.println(s);
    }

    @Test
    public void test3() {
        String s = reformatNumber("123 4-5678");
        System.out.println(s);
    }

    @Test
    public void test4() {
        String s = reformatNumber("12");
        System.out.println(s);
    }

    @Test
    public void test5() {
        String s = reformatNumber("--17-5 229 35-39475 ");
        System.out.println(s);
    }

}
