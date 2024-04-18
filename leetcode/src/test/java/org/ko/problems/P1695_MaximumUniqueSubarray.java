package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 1695. 删除子数组的最大得分
 */
public class P1695_MaximumUniqueSubarray {

    public int maximumUniqueSubarray(int[] nums) {
        if (null == nums) return 0;
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;
        for (int num : nums) {
            if (queue.size() > 0 && queue.peek().equals(num)) {
                int target = queue.stream().mapToInt(Integer::intValue).sum();
                sum = Math.max(sum, target);
                queue.poll();
            }
            queue.offer(num);
        }
        sum = Math.max(sum, queue.stream().mapToInt(Integer::intValue).sum());
        return sum;
    }

    @Test
    public void test1() {
        int[] t1 = new int[]{5,2,1,2,5,2,1,2,5};
        int i = maximumUniqueSubarray(t1);
        System.out.println(i);
    }

}

