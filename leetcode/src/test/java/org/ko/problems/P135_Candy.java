package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class P135_Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candyCount = new int[n];
        Arrays.fill(candyCount, 1);
        for (int i = 1; i < n; i++) {
            while (ratings[i] > ratings[i - 1] && candyCount[i] <= candyCount[i - 1]) {
                candyCount[i]++;
            }
        }
        for (int i = n - 1; i > 0 ; i--) {
            while (ratings[i] < ratings[i - 1] && candyCount[i] >= candyCount[i - 1]) {
                candyCount[i - 1]++;
            }
        }
        return Arrays.stream(candyCount).sum();
    }

    @Test
    public void test1() {
        int[] credit = new int[]{1,0,2};
        System.out.println(candy(credit));
    }

    @Test
    public void test2() {
        int[] credit = new int[]{1,2,2};
        System.out.println(candy(credit));
    }

    @Test
    public void test3() {
        int[] credit = new int[]{1,3,2,2,1};
        System.out.println(candy(credit));
    }

    @Test
    public void test4() {
        int[] credit = new int[]{1,2,87,87,87,2,1};
        System.out.println(candy(credit));
    }
}
