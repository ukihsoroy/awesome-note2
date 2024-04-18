package org.ko.algorithm.sort;

import org.junit.Test;
import org.ko.algorithm.util.Helper;

public class SelectionSort {

    @Test
    public void sort() {

        int[] ary = generatorArray(100);

        //1. 第一个元素
        for (int i = 0; i < ary.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < ary.length; j ++) {
                if (ary[j] < ary[minIndex]) {
                    minIndex = j;
                }
            }
            Helper.swap(ary, i, minIndex);
        }

        for (int i = 0; i < ary.length; i++) {
            System.out.println(ary[i]);
        }


    }



    public static void swap(int i, int j, int[] ary) {
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }


    public static int[] generatorArray (int n) {
        int[] ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = (int)(Math.random() * n);
        }
        return ary;
    }
}
