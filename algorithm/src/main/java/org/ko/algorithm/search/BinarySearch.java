package org.ko.algorithm.search;

/**
 * 二分查找法
 */
public class BinarySearch {


    /**
     * 二分查找法，在有序的数组中查找target
     * 如果找到target,则返回对应的索引
     * 如果没有找到，则返回-1
     * @return
     */
    public static int search (int[] args, int target) {
        //在arr[l...r]之间查找target
        int l = 0; int r = args.length - 1;
        while (l <= r) {
//          int mid = (l + r) / 2;          有可能溢出int型的取值范围
            int mid = l + (r - l) / 2;      //这句话晚了思想20年
            if (args[mid] == target) {
                return mid;
            }
            if (target < args[mid]) {
                //在args[l...mid]之中查找target
                r = mid -1;
            } else {
                //在args[mid + 1....r]之中查找target
                l = mid + 1;
            }
        }
        return -1;
    }

    //TODO 用递归法实现二分查找法

    public static int floor (int[] args, int target) {
        return -1;
    }

    public  static int ceil (int[] args, int target) {
        return -1;
    }
}
