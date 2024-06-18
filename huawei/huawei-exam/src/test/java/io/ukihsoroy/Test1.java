package io.ukihsoroy;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 5
 * 95 88 83 64 100
 * 2
 */
public class Test1 {

    @Test
    public void test1() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int count = in.nextInt();
            String number = in.next();
            int value = in.nextInt();
            if (count < 1 || value == 0 || number.length() == 0) {
                System.out.println(-1);
                return;
            }
            String[] s = number.split(" ");
            List<Integer> integers = new ArrayList<>();
            List<Integer> last = new ArrayList<>();
            for (int i = 0; i < number.length(); i++) {
                char c = number.charAt(i);
                StringBuilder buffer = new StringBuilder();
                if (c == ' ') {
                    integers.add(Integer.parseInt(buffer.toString()));
                } else {
                    buffer.append(c);
                }
                if (integers.size() == 1024) {
                    Collections.sort(integers);
                    for (int j = 0; j < value; j++) {
                        last.add(integers.get(j));
                        last.add(integers.get(1023 - j));
                    }
                    integers.clear();
                }
            }

            last.addAll(integers);
            if (last.size() < value * 2) {
                System.out.println(-1);
            } else {
                Collections.sort(last);
                if (last.get(last.size() - 1) > 1000) {
                    System.out.println(-1);
                } else {
                    int sum = 0;
                    for (int i = 0; i < value; i++) {
                        sum += last.get(i);
                        sum += last.get(last.size() - 1 - i);
                    }
                    System.out.println(sum);
                }
            }
        }
    }

    @Test
    public void test2() {
        /**
         * 5
         * 3 2 3 4 2
         * 2
         */
        executor("95 88 83 64 100", 2);
    }

    @Test
    public void test3() {
        int[] num = new int[Integer.MAX_VALUE];
        num[Integer.MAX_VALUE] = 1;
    }

    public void executor(String number, int value) {
        String[] s = number.split(" ");
        List<Integer> integers = new ArrayList<>();
        List<Integer> last = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (c == ' ' && buffer.length() > 0) {
                integers.add(Integer.parseInt(buffer.toString()));
                buffer = new StringBuilder();
            } else {
                buffer.append(c);
            }
            if (integers.size() == 1024) {
                Collections.sort(integers);
                for (int j = 0; j < value; j++) {
                    last.add(integers.get(j));
                    last.add(integers.get(1023 - j));
                }
                integers.clear();
            }
        }

        if (buffer.length() != 0) {
            integers.add(Integer.parseInt(buffer.toString()));
        }

        last.addAll(integers);
        if (last.size() < value * 2) {
            System.out.println(-1);
        } else {
            Collections.sort(last);
            if (last.get(last.size() - 1) > 1000) {
                System.out.println(-1);
            } else {
                int sum = 0;
                for (int i = 0; i < value; i++) {
                    sum += last.get(i);
                    sum += last.get(last.size() - 1 - i);
                }
                System.out.println(sum);
            }
        }
    }


    /**
     * // 本题为考试单行多行输入输出规范示例，无需提交，不计分。
     * import java.util.Scanner;
     * import java.util.*;
     * import java.util.stream.Collectors;
     * public class Main {
     *     public static void main(String[] args) {
     *         Scanner in = new Scanner(System.in);
     *         int count = in.nextInt();
     *         String number = in.next();
     *         int value = in.nextInt();
     *         if (count < 1 || value == 0 || number.length() == 0) {
     *             System.out.println(-1);
     *             return;
     *         }
     *         String[] s = number.split(" ");
     *         List<Integer> collect = Arrays.stream(s).distinct().map(Integer::parseInt).collect(Collectors.toList());
     *         if (collect.size() < value * 2) {
     *             System.out.println(-1);
     *         } else {
     *             Collections.sort(collect);
     *             if (collect.get(collect.size() - 1) > 1000) {
     *                 System.out.println(-1);
     *             } else {
     *                 int sum = 0;
     *                 for (int i = 0; i < value; i++) {
     *                     sum += collect.get(i);
     *                     sum += collect.get(collect.size() - 1 - i);
     *                 }
     *                 System.out.println(sum);
     *             }
     *         }
     *     }
     * }
     */
}
