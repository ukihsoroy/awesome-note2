package org.ko.problems;

import org.junit.jupiter.api.Test;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 */
public class P7_ReverseInteger {

    /**
     * 给定一个32位有符号整数, 返回反转数字
     * 假设我们正在处理一个只能保存32位有符号整数范围内的整数的环境。出于此问题的目的，假设您的函数在反转整数溢出时返回0
     * @param x number
     * @return reverse number
     * @example #{
     *     @Import 321  -123  120
     *     @Output 123  -321  21
     * }
     */
    public int reverse (int x) {
        int output = 0;
        while (true) {
            if (x == 0) {
                return x;
            }
            output = output * 10 + x % 10;
            if ((x /= 10) == 0) {
                return output;
            }
            if (output > 214748364 || output < -214748364) {
                return 0;
            }
        }
    }

    public int reverse1(int x) {
        String s = String.valueOf(x);
        String r = "";
        if (s.indexOf("-") == 0) {
            s = s.substring(1) + "-";
        }
        char[] ary = s.toCharArray();
        for (int i = 0; i < ary.length; i++) {
            r = ary[i] + r;
        }
        Integer i = 0;
        try {
            i = Integer.parseInt(r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    /**
     * test
     */
    @Test public void test1 () {
        String s = String.valueOf(-1);
        System.out.println(s);
    }

    /**
     * case
     */
    @Test public void case1 () {
        Integer r = reverse(-123);
        System.out.println(r);
        assert r.equals(-321);
    }

    @Test public void case2 () {
        Integer r = reverse(-210);
        System.out.println(r);
        assert r.equals(-12);
    }

    @Test public void case3 () {
        Integer r = reverse(1534236469);
        System.out.println(r);
        assert r.equals(0);
    }
}
