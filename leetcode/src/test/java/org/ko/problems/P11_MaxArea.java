package org.ko.problems;

/**
 * description: Problem11_MaxArea <br>
 * date: 4/3/2020 12:46 <br>
 * @link https://leetcode-cn.com/problems/container-with-most-water/
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class P11_MaxArea {

    /**
     * 通过移动短边来计算最大的空间
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}
