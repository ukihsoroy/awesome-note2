package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 问题239，滑动窗口最大值
 */
public class P239_MaxSlidingWindow {

    /**
     * 暴力解法
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        List<Integer> maxSliding = new ArrayList<>();
        for (int i = 0; i + k <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(nums[j], max);
            }
            maxSliding.add(max);
        }
        int[] res = new int[maxSliding.size()];
        for (int i = 0; i < maxSliding.size(); i++) {
            res[i] = maxSliding.get(i);
        }
        return res;
    }

    /**
     * 单调栈解法
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    @Test
    public void test1() {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] slidingWindow = maxSlidingWindow(nums, k);
        for (int i : slidingWindow) {
            System.out.println(i);
        }
    }

    @Test
    public void test2() {
        int[] nums = {1,-1};
        int k = 1;
        int[] slidingWindow = maxSlidingWindow(nums, k);
        for (int i : slidingWindow) {
            System.out.println(i);
        }
    }

    @Test
    public void test3() {
        int[] nums = {1};
        int k = 1;
        int[] slidingWindow = maxSlidingWindow(nums, k);
        for (int i : slidingWindow) {
            System.out.println(i);
        }
    }

    @Test
    public void test4() {
        int[] nums = {9, 11};
        int k = 2;
        int[] slidingWindow = maxSlidingWindow(nums, k);
        for (int i : slidingWindow) {
            System.out.println(i);
        }
    }

    @Test
    public void test5() {
        int[] nums = {4, -2};
        int k = 2;
        int[] slidingWindow = maxSlidingWindow(nums, k);
        for (int i : slidingWindow) {
            System.out.println(i);
        }
    }
}
