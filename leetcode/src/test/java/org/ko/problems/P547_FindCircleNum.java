package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class P547_FindCircleNum {

    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }

    @Test
    public void test1() {
        int[][] s = {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(findCircleNum(s));
    }

}
