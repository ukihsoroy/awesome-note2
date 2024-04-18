package org.ko.huawei;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 单词的压缩编码
 */
public class P820_MinimumLengthEncoding {

    /**
     * 长度
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        Set<String> contains = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                contains.remove(word.substring(i));
            }
        }

        int len = 0;
        for (String good : contains) {
            len += good.length() + 1;
        }

        return len;
    }

    @Test
    public void test1() {
        String[] words = new String[]{"time", "me", "bell"};
        int i = minimumLengthEncoding(words);
        System.out.println(i);
    }

}
