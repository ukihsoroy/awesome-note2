package org.ko.problems;

import org.junit.jupiter.api.Test;

/**
 * description: Problem13_RomanToInt <br>
 * date: 4/3/2020 12:54 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class P13_RomanToInt {

    /**
     * roman字符即是前面比后面大则相加，后面比前面大则相减，所以查看的时候多向后看一位
     * 在代码实现上，可以往后看多一位，对比当前位与后一位的大小关系，从而确定当前位是加还是减法。当没有下一位时，做加法即可。
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    @Test
    public void test1() {
        Integer s = romanToInt("IIIIV");
        System.out.println(s);
    }
}
