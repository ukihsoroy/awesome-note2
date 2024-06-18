package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class P1_TwoSum {

    /**
     * @param ary int数组, 取数组中两数相加等于sum
     * @param sum 给定值
     * @return 返回ary数组两数索引
     * 数组中只有一种结果, 每个数只能用一次
     */
    public int[] towSumON2 (int[] ary, int sum) {
        for (int i = 0; i < ary.length; i++) {
            for (int j = i + 1; j < ary.length; j++) {
                if (ary[i] + ary[j] == sum) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * O(n)级别
     */
    public int[] towSumON (int[] ary, int sum) {
        Map<Integer, Integer> contain = new HashMap<>();
        for (int i = 0; i < ary.length; i++) {
            int target = sum - ary[i];
            if (contain.containsKey(target)) {
                return new int[]{contain.get(target), i};
            }
            contain.put(ary[i], i);
        }
        return null;
    }


    /**
     * ------------------------------------------
     * case
     */

    @Test public void case1 () {
        int[] ary = new int[]{1, 2, 3, 4, 5};
        int sum = 9;
        int[] r = new int[]{3, 4};

        int[] r1 = towSumON2(ary, sum);
        int[] r2 = towSumON(ary, sum);

        assert r[0] == r1[0] && r[1] == r1[1];
        assert r[0] == r2[0] && r[1] == r2[1];
    }


}
