package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角形
 */
public class P118_YangHuiTriangle {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> lines = new ArrayList<>();

        for (int i = 0; i < numRows; i ++) {
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j <= i; j ++) {
                if (j == 0 || j == i) {
                    line.add(1);
                } else {
                    line.add(lines.get(i - 1).get(j) + lines.get(i - 1).get(j - 1));
                }
            }
            lines.add(line);
        }

        return lines;
    }

    @Test
    public void test1() {
        List<List<Integer>> lists = generate(5);
        for (List<Integer> list : lists) {
            list.forEach(s -> System.out.print(s + ","));
            System.out.println();
        }
    }

}
