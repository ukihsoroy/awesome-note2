package org.ko.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class P290_WordPattern {

    public boolean wordPattern(String pattern, String s) {
        if (s.length() == 0 || pattern.length() == 0) return false;

        char[] chars = pattern.toCharArray();
        String[] s1 = s.split(" ");

        if (chars.length != s1.length) {
            return false;
        }

        StringBuilder builder = new StringBuilder();
        Map<String, String> mapping1 = new HashMap<>();
        Map<String, String> mapping2 = new HashMap<>();


        for (int i = 0; i < chars.length; i++) {
            String key = s1[i];
            String value = String.valueOf(chars[i]);
            if (mapping1.containsKey(key)) {
                builder.append(mapping1.get(key));
            } else {
                if (mapping2.containsKey(value) && !mapping2.get(value).equals(key)) {
                    return false;
                }
                builder.append(value);
                mapping1.put(key, value);
                mapping2.put(value, key);
            }
        }

        return pattern.equals(builder.toString());
    }

    @Test
    public void test1() {
        Assertions.assertTrue(wordPattern("abba", "dog cat cat dog"));
    }

    @Test
    public void test2() {
        Assertions.assertFalse(wordPattern("abba", "dog dog dog dog"));
    }

}
