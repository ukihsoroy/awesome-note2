package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class P1018_PrefixesDivBy5 {

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<Boolean>();
        int prefix = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;
            list.add(prefix == 0);
        }
        return list;
    }

    @Test
    public void test1() {
        int[] input = {1,0,0,1,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,0,0,0,1};
        List<Boolean> booleans = prefixesDivBy5(input);
        for (Boolean aBoolean : booleans) {
            System.out.println(aBoolean);
        }
    }

    @Test
    public void test2() {
        System.out.println(2 << 2);
    }

}
