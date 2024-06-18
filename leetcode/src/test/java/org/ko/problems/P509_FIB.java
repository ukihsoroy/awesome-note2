package org.ko.problems;

import org.junit.jupiter.api.Test;

public class P509_FIB {

    public int fib(int n) {
        int[] fib = new int[n + 1];
        fib[0] = 0;
        if (n > 0) fib[1] = 1;

        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    public int fib1(int n) {
        if (n < 2) return n;
        int q, p = 1, r = 1;
        for (int i = 2; i < n; i++) {
            q = p; //0
            p = r; //1
            r = p + q; //2
        }
        return r;
    }

    @Test
    public void test1() {
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(5));
        System.out.println(fib(6));
        System.out.println(fib(0));
    }

    @Test
    public void test2() {
        System.out.println(fib1(1));
        System.out.println(fib1(2));
        System.out.println(fib1(3));
        System.out.println(fib1(4));
        System.out.println(fib1(5));
        System.out.println(fib1(6));
        System.out.println(fib1(0));
    }

}
