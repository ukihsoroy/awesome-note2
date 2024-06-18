package org.ko.huawei;


import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 每日温度
 * 返回比当前天数大的数字之间间隔
 */
public class P739_DailyTemperatures {


    public int[] dailyTemperatures(int[] T) {
        int[] index = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int num = 0;
            for (int j = i + 1; j < T.length; j ++) {
                num++;
                if (T[j] > T[i]) {
                    break;
                }
                if (j == T.length - 1) {
                    num = 0;
                }
            }
            index[i] = num;
        }
        return index;
    }

    /**
     * 单调栈解法
     * @param T
     * @return
     */
    public int[] dailyTemperatures1(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            //当前温度大于栈顶的温度，则讲栈中元素出栈，并记录下标
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    @Test
    public void test1() {
        int[] temp = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        for (int i : dailyTemperatures(temp)) {
            System.out.println(i);
        }
    }

}
