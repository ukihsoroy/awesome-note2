package io.ukihsoroy;

import org.junit.Test;

public class Test2 {

    public void executor(String s, String l) {
        if (s.length() > 100 || l.length() > 500000 || s.length() == 0 || l.length() == 0) {
            System.out.println(-1);
        }

        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = index; j < l.length(); j++) {
                if (s.charAt(i) == l.charAt(j)) {
                    index = j;
                    break;
                }
                if (j == l.length() - 1) {
                    index = -1;
                }
            }
            if (index == -1) break;
        }

        System.out.println(index == 0 ? -1 : index);

    }

    /**
     * ace
     * abcde
     */
    @Test
    public void test1() {
        executor("ace", "abcde");
    }

    /**
     * fgh
     * abcde
     */
    @Test
    public void test2() {
        executor("fgh", "abcde");
    }

}
