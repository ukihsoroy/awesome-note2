package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class P228_SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder(String.valueOf(nums[i]));
            if (i + 1 < n && nums[i + 1] - nums[i] == 1) {
                while(i + 1 < n && nums[i + 1] - nums[i] == 1) {
                    i++;
                }
                builder.append("->").append(nums[i]);
            }
            result.add(builder.toString());
        }
        return result;
    }

    @Test
    public void test1() {
        int[] input = {0,2,3,4,6,8,9};
        System.out.println(summaryRanges(input));
    }

    @Test
    public void test2() {
        StringBuilder builder = new StringBuilder(-1);
    }
}
