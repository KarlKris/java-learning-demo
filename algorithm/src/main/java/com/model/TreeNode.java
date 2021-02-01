package com.model;

import lombok.Data;

/**
 * @Description 二叉树结点
 * @Author li-yuanwen
 * @Date 2021/1/27 20:55
 */
@Data
public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
