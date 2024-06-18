package org.ko.problems;

import org.junit.jupiter.api.Test;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */
public class P4_MedianOfTwoSortedArrays {

    /**
     * 有两个大小为m和n的排序数组nums1和nums2。
     * 找到两个排序数组的中位数。整体运行时间复杂度应该是O（log（m + n））。
     * @param nums1 数组1
     * @param nums2 数组2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] ary = new int[len];
        int i = 0, l1 = 0, l2 = 0;
        //归并
        while (l1 < nums1.length && l2 < nums2.length) {
            if (nums1[l1] < nums2[l2]) {
                ary[i++] = nums1[l1++];
            } else {
                ary[i++] = nums2[l2++];
            }
        }
        while (l1 < nums1.length) {
            ary[i++] = nums1[l1++];
        }
        while (l2 < nums2.length) {
            ary[i++] = nums2[l2++];
        }
        if (ary.length % 2 == 1) {
            return ary[ary.length / 2];
        }
        return (ary[(ary.length / 2) - 1] + ary[ary.length / 2]) / 2.0;
    }


    /**
     * Test
     */
    @Test public void test1 () {
        System.out.println(5/2);
    }

    /**
     * case
     */
    @Test public void case1 () {
        int[] nums1 = new int[] {0, 2, 3, 5, 9};
        int[] nums2 = new int[] {1, 4, 7, 8, 10};
        double r = findMedianSortedArrays(nums1, nums2);
        System.out.println(r);
        assert r == 4.5;
    }

    @Test public void case2 () {
        int[] nums1 = new int[] {};
        int[] nums2 = new int[] {1, 4};
        double r = findMedianSortedArrays(nums1, nums2);
        System.out.println(r);
        assert r == 2.5;
    }

    @Test public void case3 () {
        int[] nums1 = new int[] {1};
        int[] nums2 = new int[] {1, 4, 5, 7};
        double r = findMedianSortedArrays(nums1, nums2);
        System.out.println(r);
        assert r == 4;
    }

    @Test public void case4 () {
        int[] nums1 = new int[] {1, 5, 8, 10, 1559};
        int[] nums2 = new int[] {1, 4, 5, 7};
        double r = findMedianSortedArrays(nums1, nums2);
        System.out.println(r);
        assert r == 5;
    }

}
