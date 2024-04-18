package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class P2_AddTwoNumbers {


    /**
     * 输入两个链表倒序, 返回相加后的和的链表倒序
     * @param l1 数字倒序
     * @param l2 数字倒序
     * @example #{
     *     @Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     *     @Output: 7 -> 0 -> 8
     *     @Explanation: 342 + 465 = 807.
     * }
     * @return 相加后数字倒序链表
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        BigDecimal sum = BigDecimal.ZERO;
        int i = 0, j = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum = sum.add(BigDecimal.valueOf(l1.val * Math.pow(10, i++)));
                l1 = l1.next;
            }
            if (l2 != null) {
                sum = sum.add(BigDecimal.valueOf(l2.val * Math.pow(10, j++)));
                l2 = l2.next;
            }
        }
        ListNode r1 = null;
        ListNode r2;
        String v = String.valueOf(sum);
        String v1 = v.substring(0, v.indexOf("."));
        System.out.println("l1 + l2 = " + v1);
        while (v1.length() > 0) {
            String v2 = v1.substring(0, 1);
            v1 = v1.substring(1);
            if (r1 == null) {
                r1 = new ListNode(new Integer(v2));
            } else {
                r2 = new ListNode(new Integer(v2));
                r2.next = r1;
                r1 = r2;
            }
        }
        return r1;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = 0, y = 0;
            if (l1 != null) x = l1.val;
            if (l2 != null) y = l2.val;
            int sum = carry + x + y;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        return dummy.next;
    }


    public class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * case-----------------------------
     */
    @Test public void case1 () {
        //543
        ListNode i1 = new ListNode(3);
        ListNode i2 = new ListNode(4);
        ListNode i3 = new ListNode(5);

        i2.next = i1;
        i3.next = i2;

        //852
        ListNode j1 = new ListNode(2);
        ListNode j2 = new ListNode(5);
        ListNode j3 = new ListNode(8);

        j2.next = j1;
        j3.next = j2;

        //345 + 258 = 603
        ListNode r1 = addTwoNumbers1(i3, j3);
        ListNode r2 = addTwoNumbers2(i3, j3);
        assert r1.val == 3 && r1.next.val == 0 && r1.next.next.val == 6;
        assert r2.val == 3 && r2.next.val == 0 && r2.next.next.val == 6;
        System.out.println(r1);
        System.out.println(r2);
    }

    @Test public void case2 () {
        //3
        ListNode i1 = new ListNode(3);

        //852
        ListNode j1 = new ListNode(2);
        ListNode j2 = new ListNode(5);
        ListNode j3 = new ListNode(8);

        j2.next = j1;
        j3.next = j2;
        //3 + 258 = 261
        ListNode r1 = addTwoNumbers1(i1, j3);
        ListNode r2 = addTwoNumbers2(i1, j3);
        assert r1.val == 1 && r1.next.val == 6 && r1.next.next.val == 2;
        assert r2.val == 1 && r2.next.val == 6 && r2.next.next.val == 2;
        System.out.println(r1);
        System.out.println(r2);
    }

    @Test public void case3 () {
        //9
        ListNode i1 = new ListNode(9);

        //199
        ListNode j1 = new ListNode(9);
        ListNode j2 = new ListNode(9);
        ListNode j3 = new ListNode(1);

        j2.next = j1;
        j3.next = j2;
        //9 + 991 = 1000
        ListNode r1 = addTwoNumbers1(i1, j3);
        ListNode r2 = addTwoNumbers2(i1, j3);
        assert r1.val == 0 && r1.next.val == 0 && r1.next.next.val == 0 && r1.next.next.next.val == 1 ;
        assert r2.val == 0 && r2.next.val == 0 && r2.next.next.val == 0 && r2.next.next.next.val == 1 ;
        System.out.println(r1);
    }

    @Test public void case4 () {
        //9
        ListNode i1 = new ListNode(9);

        //199
        ListNode j1 = new ListNode(9);
        ListNode j2 = new ListNode(9);
        ListNode j3 = new ListNode(9);
        ListNode j4 = new ListNode(9);
        ListNode j5 = new ListNode(9);
        ListNode j6 = new ListNode(9);
        ListNode j7 = new ListNode(9);
        ListNode j8 = new ListNode(9);
        ListNode j9 = new ListNode(9);
        ListNode j10 = new ListNode(1);

        j2.next = j1;
        j3.next = j2;
        j4.next = j3;
        j5.next = j4;
        j6.next = j5;
        j7.next = j6;
        j8.next = j7;
        j9.next = j8;
        j10.next = j9;

        //9 + 991 = 1000
        ListNode r1 = addTwoNumbers1(i1, j10);
        ListNode r2 = addTwoNumbers2(i1, j10);
        assert true;
        System.out.println(r1);
    }


    /**
     * test-----------------------------
     */
    @Test public void test1 () {
        int n = (int)Math.pow(10, 5);
        System.out.println(n);
    }

    @Test public void test2 () {
//        Integer.parseInt("9999999999");
//        Integer.parseInt("10000000000");
    }

}
