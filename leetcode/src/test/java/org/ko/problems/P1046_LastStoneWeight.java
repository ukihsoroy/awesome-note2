package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class P1046_LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        if (stones.length == 0) return 0;
        List<Integer> st = new LinkedList<>();
        for (int stone : stones) {
            st.add(stone);
        }
        while (st.size() > 1) {
            Collections.sort(st);
            Integer integer = st.remove(st.size() - 1);
            Integer integer1 = st.remove(st.size() - 1);
            int value = Math.abs(integer - integer1);
            if (value != 0) {
                st.add(value);
            }
        }

        if (st.size() == 1) {
            return st.get(0);
        } else {
            return 0;
        }
    }

    public int lastStoneWeight1(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

    @Test
    public void test1() {
        int i = lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1});
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1});
        System.out.println(i);
    }
}
