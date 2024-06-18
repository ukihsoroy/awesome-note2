package org.ko.huawei;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 * []() / [()] = true
 * [(]) = false
 */
public class P20_isValid {

    public boolean isValid(String s) {
        if (s.length() == 0) return true;

        char[] cs = s.toCharArray();

        if (cs.length % 2 == 1) return false;

        Map<Character, Character> pairs = new HashMap<Character, Character>(){{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Stack<Character> stack = new Stack<>();
        for (char c : cs) {
            if (pairs.containsKey(c)) {
                //如果栈是空的 说明有的反括号先入栈
                if (stack.isEmpty() || !pairs.get(c).equals(stack.peek())) {
                    return false;
                }
                stack.pop();
            } else {
              stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    @Test
    public void test1() {
        Assertions.assertTrue(isValid("[]"));
    }

    @Test
    public void test2() {
        Assertions.assertFalse(isValid("[(])"));
    }

    @Test
    public void test3() {
        Assertions.assertTrue(isValid("[]()"));
    }

    @Test
    public void test4() {
        Assertions.assertTrue(isValid(""));
    }

    @Test
    public void test5() {
        Assertions.assertFalse(isValid("(("));
    }

    @Test
    public void test6() {
        Assertions.assertFalse(isValid(")("));
    }

}
