package org.ko.huawei;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 根据身高重建队列
 */
public class P406_ReconstructQueue {

    /**
     *根据身高重建队列
     */
    public int[][] reconstructQueue(int[][] people) {
        //先排序[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        //[7,0][7,1][6,1][5,0][5,2][4,4]
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
            for (int[] an : ans) {
                System.out.print("[");
                for (int i : an) {
                    System.out.print(i + ",");
                }
                System.out.print("]");
            }
            System.out.println();
        }
        return ans.toArray(new int[ans.size()][]);
    }

    @Test
    public void test1() {
        int[][] ints = reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        System.out.println(ints);
    }
}
