package org.ko.problems;

import org.junit.jupiter.api.Test;

public class P189_Rotate {

    public int[] rotate(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            int t = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = t;
        }
        return nums;
    }

    @Test
    public void test1() {
        int[] nums = {1,2,3,4,5,6,7};
        int[] rotate = rotate(nums, 3);
        for (int i : rotate) {
            System.out.println(i);
        }
    }

}
