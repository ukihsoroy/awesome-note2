package org.ko.huawei;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 跳跃游戏
 * 1. 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 2. 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 3. 判断你是否能够到达最后一个位置。
 */
public class P55_CanJump {

    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(i + nums[i], cover);
            if (cover >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test1() {
        int[] t = new int[]{2,3,1,1,4};
        Assertions.assertTrue(canJump(t));
    }

    @Test
    public void test2() {
        int[] t = new int[]{3,2,1,0,4};
        Assertions.assertFalse(canJump(t));
    }
}
