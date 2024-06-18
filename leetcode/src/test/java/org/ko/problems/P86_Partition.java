package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class P86_Partition {

    public ListNode partition(ListNode head, int x) {
        /**
         * 遍历链表调整位置
         */
        ListNode result = new ListNode(0);
        ListNode large = new ListNode(0);
        deep(head, result, large, x);
        ListNode node = result;
        while (null != node.next) {
            node = node.next;
        }
        node.next = large.next;
        return result.next;
    }

    private void deep(ListNode head, ListNode result, ListNode large, int x) {
        if (null == head) return;

        if (x > head.val) {
            result.next = new ListNode(head.val);
            deep(head.next, result.next, large, x);
        } else {
            large.next = new ListNode(head.val);;
            deep(head.next, result, large.next, x);
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    @Test
    public void test1() {
        //1->4->3->2->5->2
        ListNode one = new ListNode(1);
        ListNode two1 = new ListNode(2);
        ListNode two2 = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = four;
        four.next = three;
        three.next = two1;
        two1.next = five;
        five.next = two2;
        ListNode partition = partition(one, 3);
        while (null != partition.next) {
            System.out.println(partition.val);
            partition = partition.next;
        }
    }
}
