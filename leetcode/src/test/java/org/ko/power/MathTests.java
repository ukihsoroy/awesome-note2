package org.ko.power;

import org.junit.jupiter.api.Test;

/**
 * description: 数据问题，没事练着玩 <br>
 * date: 2020/2/22 12:08 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class MathTests {


    /**
     * 2.0 - 9999 有多少个7（不是带有7的数字）
     */
    @Test public void test1() {
        int j = 0;
        for (int i = 0; i < 10000;  i ++) {
            if (String.valueOf(i).contains("7")) {
                System.out.println(i);
                for (char c : String.valueOf(i).toCharArray()) {
                    if (c == '7') {
                        j ++;
                    }
                }
            }
        }
        System.out.println(j); //4000
    }

}
