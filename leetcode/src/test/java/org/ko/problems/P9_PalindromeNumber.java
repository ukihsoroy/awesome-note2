package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class P9_PalindromeNumber {

    /**
     * 确定整数是否是回文。这样做没有额外的空间。
     * 负整数可以作为回文吗？（即，-1）

     如果您正在考虑将整数转换为字符串，请注意使用额外空间的限制。

     你也可以尝试颠倒一个整数。但是，如果已解决“反向整数”问题，则知道反转的整数可能会溢出。你将如何处理这种情况？

     有一个更通用的方法来解决这个问题。
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        else if (x == 0) return true;
        else {
            List<Integer> lists = new ArrayList<>();
            int y = x, z;
            while (y > 9) {
                z = y % 10;
                y = y / 10;
                lists.add(z);
            }
            lists.add(y);
            Integer sum = 0;
            for (Integer list : lists) {
                sum = sum * 10 + list;
            }

            return sum.equals(x);
        }
    }

    @Test public void test1() {
        assert !isPalindrome(123);
    }

    @Test public void test2() {
        assert isPalindrome(0);
    }

    @Test public void test3() {
        assert isPalindrome(121);
    }

    @Test public void test4() {
        assert !isPalindrome(-121);
    }

    @Test public void test5() {
        assert !isPalindrome(10);
    }
}
