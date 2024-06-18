package org.ko.problems;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 动态规划例题
 */
public class P714_MaxProfit {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        //表示第i天不持有可以获得的最大利润
        dp[0][0] = 0;
        //表示第i天持有可获得的最大利润
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //今天不持有，昨天和今天都不持有的情况和昨天持有今天卖出买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            //今天持有, 昨天不持有，昨天不持有
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    @Test
    public void test1() {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        int i = maxProfit(prices, fee);
        Assertions.assertEquals(i, 8);
    }

}
