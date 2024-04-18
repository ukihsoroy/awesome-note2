package org.ko.problems;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.*;

/**
 * 第18题，电话号码
 * 回溯算法
 * 递归
 */
public class P18_LetterCombinations {

    /**
     * 递归 + 深度优先搜索
     * @param digits
     * @return
     */
    public List<String> letterCombinationsDSF(String digits) {
        if (null == digits || digits.length() == 0) return new ArrayList<>();
        List<String> result = new ArrayList<>();
        deep(digits, result);
        return result;
    }


    /**
     * 队列 + 广度优先搜索
     */
    public List<String> letterCombinations(String digits) {
        if (null == digits || digits.length() == 0) return new ArrayList<>();

        char[] chars = digits.toCharArray();

        //初始化队列
        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        //通过空的字符串去match，第一组数据，以达到初始化的目的。
        for (char c : chars) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (String s : mapping(String.valueOf(c))) {
                    queue.offer(poll + s);
                }
            }
        }


        return new ArrayList<>(queue);
    }


    @Test
    public void test1() {
        for (String letterCombination : letterCombinationsDSF("23")) {
            System.out.println(letterCombination);
        }
    }

    @Test
    public void test2() {
        for (String letterCombination : letterCombinations("23")) {
            System.out.println(letterCombination);
        }
    }


    public void deep(String digits, List<String> value) {
        //判断长度
        if (digits.length() == 0) {
            value.add("");
            return;
        }
        //处理子问题
        List<String> result = new ArrayList<>();
        deep(digits.substring(1), result);

        //处理当前问题
        String target = digits.substring(0, 1);
        List<String> mapping = mapping(target);
        for (String s : mapping) {
            for (String s1 : result) {
                value.add(s + s1);
            }
        }
    }

    private List<String> mapping(String c) {
        List<String> value = new ArrayList<>();
        switch (c) {
            case "2":
                value.add("a");
                value.add("b");
                value.add("c");
                return value;
            case "3":
                value.add("d");
                value.add("e");
                value.add("f");
                return value;
            case "4":
                value.add("g");
                value.add("h");
                value.add("i");
                return value;
            case "5":
                value.add("j");
                value.add("k");
                value.add("l");
                return value;
            case "6":
                value.add("m");
                value.add("n");
                value.add("o");
                return value;
            case "7":
                value.add("p");
                value.add("q");
                value.add("r");
                value.add("s");
                return value;
            case "8":
                value.add("t");
                value.add("u");
                value.add("v");
                return value;
            case "9":
                value.add("w");
                value.add("x");
                value.add("y");
                value.add("z");
                return value;
        }
        return value;
    }

}
