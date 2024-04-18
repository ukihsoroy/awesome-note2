package org.ko.problems;

import org.junit.jupiter.api.Test;

/**
 * 使用最小的力气爬楼梯
 */
public class P746_MinCostClimbingStairs {

    /**
     * 输入: cost = [10, 15, 20]
     * 输出: 15
     * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
     * 动态规划
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];

        dp[0] = dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[n];
    }

    @Test
    public void test1() {
        int[] t = new int[]{10, 15, 20};
        int i = minCostClimbingStairs(t);
        System.out.println(i);
    }
}
