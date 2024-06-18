package org.ko.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P605_CanPlaceFlowers {

    /**
     * 1,0,1,0,1,0,0,1,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i+=2) {
            if (flowerbed[i] == 0) {
                if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                    n --;
                } else {
                    i ++;
                }
            }
        }
        return n <= 0;
    }

    @Test
    public void test1() {
        int[] flowerbed = {1,0,0,0,1};
        boolean b = canPlaceFlowers(flowerbed, 1);
        Assertions.assertTrue(b);
    }

    @Test
    public void test2() {
        int[] flowerbed = {1,0,0,0,1};
        boolean b = canPlaceFlowers(flowerbed, 2);
        Assertions.assertFalse(b);
    }

    @Test
    public void test3() {
        int[] flowerbed = {1,0,0,0,1,0,0};
        boolean b = canPlaceFlowers(flowerbed, 2);
        Assertions.assertTrue(b);
    }

    @Test
    public void test4() {
        int[] flowerbed = {0,0,1,0,0,0,0,1,0,1,0,0,0,1,0,0,1,0,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0};
        boolean b = canPlaceFlowers(flowerbed, 2);
        Assertions.assertTrue(b);
    }
}
