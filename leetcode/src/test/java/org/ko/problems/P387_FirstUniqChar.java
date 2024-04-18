package org.ko.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 387, 找到字符串中最先出现的唯一字符
 */
public class P387_FirstUniqChar {

    public int firstUniqChar(String s) {
        int[] contains = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            contains[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (contains[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test1() {
        int leetcode = firstUniqChar("leetcode");
        Assertions.assertEquals(leetcode, 0);
    }

    @Test
    public void test2() {
        int leetcode = firstUniqChar("loveleetcode");
        Assertions.assertEquals(leetcode, 2);
    }
}
