package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class P830_LargeGroupPositions {

    public List<List<Integer>> largeGroupPositions(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            List<Integer> integers = new ArrayList<>();
            int start = i - 1;
            integers.add(start);
            while (i < n && chars[i] == chars[i - 1]) {
                i++;
            }
            integers.add(i - 1);
            if (i - start > 2) {
                res.add(integers);
            }
        }
        return res;
    }

    @Test
    public void test1() {
        String s = "abbxxxxzyy";
        List<List<Integer>> lists = largeGroupPositions(s);
        System.out.println(lists);
    }

    @Test
    public void test2() {
        String s = "abc";
        List<List<Integer>> lists = largeGroupPositions(s);
        System.out.println(lists);
    }

    @Test
    public void test3() {
        String s = "abcdddeeeeaabbbcd"; //[[3,5],[6,9],[12,14]]
        List<List<Integer>> lists = largeGroupPositions(s);
        System.out.println(lists);
    }
}
