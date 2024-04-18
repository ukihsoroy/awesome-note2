package org.ko.problems;

import java.util.Arrays;

/**
 * description: Problem16_ThreeSumClosest <br>
 * date: 4/7/2020 20:19 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class P16_ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }

}
