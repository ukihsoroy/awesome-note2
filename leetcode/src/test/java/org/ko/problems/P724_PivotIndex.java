package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class P724_PivotIndex {

    public int pivotIndex(int[] nums) {
        if (nums.length < 3) return -1;
        int left = 0;
        int right = Arrays.stream(nums).sum() - left;
        for (int i = 0; i < nums.length; i++) {
            if (left == right - nums[i]) {
                return i;
            } else {
                left += nums[i];
                right -= nums[i];
            }

        }
        return -1;
    }

    @Test
    public void test1() {
        int[] ary = {-1,-1,0,1,0,-1};
        System.out.println(pivotIndex(ary));
    }

}
