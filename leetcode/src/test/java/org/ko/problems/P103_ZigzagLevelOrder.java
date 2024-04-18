package org.ko.problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class P103_ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (null == root) return new ArrayList<>();

        //先从左往右遍历 bfs
        //再从右往左遍历
        //记录递归层数

        //构建返回结果
        List<List<Integer>> res = new ArrayList<>();

        //初始化队列
        Queue<TreeNode> queue = new ArrayDeque<>();

        //添加初始化元素
        queue.add(root);

        /*
         * 1
         * 2 3
         * 4 5 6 7
         */
        //构建左右迭代打印参数
        boolean treeOnLeft = true;

        //如果集合为空
        while (!queue.isEmpty()) {

            //数组
            List<Integer> integers = new ArrayList<>();

            //当前这一层的大小
            int count = queue.size();

            //循环这一层的全部数据
            for (int i = 0; i < count; i++) {
                TreeNode poll = queue.poll();
                if (treeOnLeft) {
                    integers.add(poll.val);
                } else {
                    integers.add(0, poll.val);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            treeOnLeft = !treeOnLeft;
            res.add(integers);
        }
        return res;
    }


    @Test
    public void test1() {
        TreeNode head = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        head.right=three;
        head.left=second;
        second.right=five;
        second.left=four;
        three.right=seven;
        three.left=six;
        List<List<Integer>> lists = zigzagLevelOrder(head);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }



}
