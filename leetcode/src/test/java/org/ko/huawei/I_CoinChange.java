package org.ko.huawei;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class I_CoinChange {

    public int coinChange(int[] coins, int amount) {
        List<Integer> coinList = new ArrayList<>();
        for (int coin : coins) {
            coinList.add(coin);
        }
        Collections.sort(coinList);
        int count = 0;
        int temp = amount; //余数
        for (int i = coinList.size() - 1; i >= 0; i--) {
            if (coinList.get(i) > amount) continue;
            count += temp / coinList.get(i);
            temp = temp % coinList.get(i);
        }
        return count;
    }

    @Test
    public void test1() {
        int[] coins = new int[]{1, 2, 5};
        int i = coinChange(coins, 11);
        System.out.println(i);
    }

    public int firstMissingPositive(int[] nums) {
        int[] count = new int[Integer.MAX_VALUE];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                count[nums[i]] = 1;
            }
        }
        count[0] = 1;
        int r = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 1) {
                r = i;
                break;
            }
        }
        return r;
    }

    @Test
    public void test2() {
        int i = firstMissingPositive(new int[]{1, 2, 0});
        System.out.println(i);
    }
}
