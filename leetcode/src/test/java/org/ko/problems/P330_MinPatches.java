package org.ko.problems;

import org.junit.jupiter.api.Test;

public class P330_MinPatches {

    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

    @Test
    public void test1() {
        int i = minPatches(new int[]{1, 3}, 6);
        System.out.println(i);
    }

}
