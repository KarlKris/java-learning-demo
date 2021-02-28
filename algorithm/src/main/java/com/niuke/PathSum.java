package com.niuke;

import com.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: li-yuanwen
 * @Date: 2021/2/27 19:15
 * @Description: 给定一个二叉树和一个值 sum，请找出所有的根节点到叶子节点的节点值之和等于 sum 的路径
 */
public class PathSum {

    /**
     * @return
     * @Author li-yuanwen
     * @Description
     * @Date 2021/2/27  19:34
     * @Param
     **/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // write code here
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> results = new ArrayList<>();
        doPathSum(results, new ArrayList<>(), root, sum);
        return results;
    }

    /**
     * @return
     * @Author li-yuanwen
     * @Description 递归实现
     * @Date 2021/2/27  23:38
     * @Param
     **/
    private void doPathSum(List<List<Integer>> results, List<Integer> list, TreeNode root, int target) {
        if (root == null) {
            return;
        }

        int val = root.getVal();
        list.add(val);

        // 满足目标
        if (root.getLeft() == null && root.getRight() == null && target == val) {
            results.add(new ArrayList<>(list));
            // 移除，为了保障遍历下一个
            list.remove(list.size() - 1);
            return;
        }
        doPathSum(results, list, root.getLeft(), target - val);
        doPathSum(results, list, root.getRight(), target - val);
        // 由上向下，需移除最底，为了保障遍历下一个
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(8);
        root.setLeft(n1);
        root.setRight(n2);

        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(11);
        TreeNode n5 = new TreeNode(9);
        n1.setLeft(n3);
        n1.setRight(n4);
        n2.setRight(n5);

        TreeNode n6 = new TreeNode(2);
        TreeNode n7 = new TreeNode(7);
        n4.setLeft(n6);
        n4.setRight(n7);

        PathSum pathSum = new PathSum();
        List<List<Integer>> lists = pathSum.pathSum(root, 22);

        StringBuilder sb = new StringBuilder("[");
        int size = lists.size();
        for (int i = 0; i < size; i++) {
            sb.append("[");
            List<Integer> list = lists.get(i);
            int s = list.size();
            for (int j = 0; j < s; j++) {
                sb.append(list.get(j));
                if (j != s - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.println(sb.toString());

    }


}
