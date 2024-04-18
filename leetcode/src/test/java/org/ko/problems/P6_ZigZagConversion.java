package org.ko.problems;

import org.junit.jupiter.api.Test;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 */
public class P6_ZigZagConversion {

    /**
     * 讲PAHNAPLSIIGYIR 以Z自行输出
     * @param s 输入参数
     * @param numRows 行数
     * @return 返回转换后的
     * @eaxmple #{
     *     @Input PAYPALISHIRING
     *     @Output
     *      P   A   H   N
     *      A P L S I I G
     *      Y   I   R
     * }
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        StringBuilder[] ary = new StringBuilder[numRows];
        //创建每一列
        for (int i = 0; i < ary.length; i++) {
            ary[i] = new StringBuilder("");
        }
        //定义偏移量值
        int offset = 1;
        //行数索引
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            ary[index].append(s.charAt(i));
            //当从第一行还是时, 正相加
            if (index == 0) offset = 1;
            //从结尾后退是, 负相加
            if (index == numRows - 1) offset = -1;
            index += offset;
        }
        //拼接结果
        String r = "";
        for (int i = 0; i < ary.length; i++) {
            r += ary[i].toString();
        }
        return r;
    }

    /**
     * case
     */
    @Test public void case1 () {
        String r = convert("PAYPALISHIRING", 3);
        System.out.println(r);
        assert "PAHNAPLSIIGYIR".equals(r);
    }

    @Test public void case2 () {
        String r = convert("PAYPALISHIRING", 4);
        System.out.println(r);
        assert "PINALSIGYAHRPI".equals(r);
    }
}
