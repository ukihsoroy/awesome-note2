package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 */
public class P5_LongestPalindromicSubstring {

    /**
     * 输入字符串, 返回相同字母最长的字符串
     * @example #{longestPalindrome
     *     @Input abcdaba
     *     @Output abcda
     * }
     * @param s 给定字符串 可以假定s最长为1000
     * @return
     */
    public String longestPalindromeT1(String s) {
        char[] ary = s.toCharArray();
        //#1. 创建Char容器
        int[] contain = new int[256];
        int l = 0, r = 0, i = 0, j = 0, len = 0;
        //#2. 填充数组-1
        Arrays.fill(contain, -1);
        while (r < ary.length) {
            //#3. 查找当前char对应数组中的值-是否已经存在过 不存在-1
            int index = contain[ary[r]];
            if (index != -1 && r - index + 1 >= len) {
                i = index;
                j = r + 1;
                len = r - index + 1;
            }
            contain[ary[r]] = r;
            r++;
        }
        return s.substring(i, j);
    }

    public String longestPalindromeT2(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    private int start = 0;
    private int length = 1;
    private char[] ary = null;
    private int aryLen = 0;

    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        aryLen = s.length();
        ary = s.toCharArray();

        for (int i = 0; i < aryLen; i++) {
            int r = i + 1;
            while (r < aryLen && ary[i] == ary[r]) {
                r++;
            }
            search(i, r - 1);
            i = r - 1;
        }
        return s.substring(start, start + length);
    }

    public void search(int left, int right) {
        int l = left - 1;
        int r = right + 1;

        for (;l >= 0 && r < aryLen; l--, r++) {
            if (ary[l] != ary[r]) {
                break;
            }
        }

        if (r - l - 1 > length) {
            start = l + 1;
            length = r - start;
        }
    }

    /**
     * test
     */
    @Test public void test1 () {
        char c = 'r';
        System.out.println((int)c);
    }

    /**
     * case
     */
    @Test public void case1 () {
        String s = "abcbdefgaseq";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "abcbdefga".equals(r);
    }

    @Test public void case2 () {
        String s = "abababab";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "aba".equals(r) || "bab".equals(r);
    }

    @Test public void case3 () {
        String s = "abcdaba";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "abcda".equals(r) || "bcdab".equals(r);
    }

    @Test public void case4 () {
        String s = "abcdefgaseq";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "abcdefga".equals(r);
    }

    @Test public void case5 () {
        String s = "ababca";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "abca".equals(r);
    }

    @Test public void case6 () {
        String s = "cbbd";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "bb".equals(r);
    }

    @Test public void case7 () {
        String s = "a";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "".equals(r);
    }

    @Test public void case8 () {
        String s = "ab";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "".equals(r);
    }

    @Test public void case9 () {
        String s = "ccc";
        String r = longestPalindrome(s);
        System.out.println(r);
        assert "ccc".equals(r);
    }

}
