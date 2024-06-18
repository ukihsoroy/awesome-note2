package org.ko.huawei;

/**
 * 最大正方形面积
 * 使用动态规划
 */
public class P221_MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;

        //特判
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }

        //初始化dp数组，动态规划数组
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        //左，上，左上三个元素的值取最小的值+1
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }

                    //dp[i, j]和最大值比较取最大值
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

}
