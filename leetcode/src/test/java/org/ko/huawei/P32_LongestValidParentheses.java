package org.ko.huawei;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 */
public class P32_LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        //System.out.println(stack);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            //如果是正序括号，直接放入数组
            if (s.charAt(i) == '(') stack.push(i);
            else {
                //如果是反括号，从数组去除
                stack.pop();
                //当数组中数字为空，放入栈中当前数字
                if (stack.isEmpty()) stack.push(i);
                else {
                    //如果不为空，则说明
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    @Test
    public void test1() {
        int i = longestValidParentheses(")()())");
        System.out.println(i);
        Assertions.assertEquals(i, 4);
    }

    @Test
    public void test2() {
        int i = longestValidParentheses("(()()(");
        System.out.println(i);
        Assertions.assertEquals(i, 4);
    }

    @Test
    public void test3() {
        int i = longestValidParentheses("(()())");
        System.out.println(i);
        Assertions.assertEquals(i, 6);
    }

    @Test
    public void test4() {
        int i = longestValidParentheses("()(()");
        System.out.println(i);
        Assertions.assertEquals(i, 2);
    }

}
