package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P455_FindContentChildren {

    public int findContentChildren(int[] g, int[] s) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i : s) {
            queue.offer(i);
        }
        int sum = 0;
        for (int i = 0; i < g.length; i++) {
            while (queue.size() != 0) {
                int target = queue.poll();
                if (g[i] <= target) {
                    sum++;
                    break;
                }
            }
        }
        return sum;
    }

    @Test
    public void test1() {
        int contentChildren = findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1});
        System.out.println( contentChildren);
    }

}
