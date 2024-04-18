package org.ko.algorithm.util;

public final class Helper {

    public static void swap (int[] ary, int i, int j) {
        int r = ary[i];
        ary[i] = ary[j];
        ary[j] = r;
    }
}
