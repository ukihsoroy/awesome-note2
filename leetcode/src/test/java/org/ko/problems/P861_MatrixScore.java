package org.ko.problems;

import org.junit.jupiter.api.Test;

public class P861_MatrixScore {

    /**
     * 我怕是个傻子吧。
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length;

        //计算所有的列，首位都为1的情况
        int ret = m * (1 << (n - 1));

        for (int j = 1; j < n; j++) {
            int nOnes = 0;

            //遍历列
            for (int i = 0; i < m; i++) {
                //当首位为1的时候，直接计数，如果不为1则翻转
                if (A[i][0] == 1) {
                    nOnes += A[i][j];
                } else {
                    nOnes += (1 - A[i][j]); // 如果这一行进行了行反转，则该元素的实际取值为 1 - A[i][j]
                }
            }

            //如果全为1和目前情况相减，得出是翻转和不翻转最优情况
            int k = Math.max(nOnes, m - nOnes);
            ret += k * (1 << (n - j - 1));
        }
        return ret;
    }


    @Test
    public void test1() {
        int[][] T = new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        int i = matrixScore(T);
        System.out.println(i);
    }
}
