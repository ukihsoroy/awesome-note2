package org.ko.problems;

import org.junit.jupiter.api.Test;

public class P316_RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        //
        boolean[] vis = new boolean[26];

        //
        int[] num = new int[26];


        for (int i = 0; i < s.length(); i++) {
            //记录每个位置的数量
            num[s.charAt(i) - 'a']++;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                //
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    //
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }

    @Test
    public void test1() {
        String bcabc = removeDuplicateLetters("bcabc");
        System.out.println(bcabc);
    }



}
