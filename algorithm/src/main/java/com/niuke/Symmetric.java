package com.niuke;

import com.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 给定一棵二叉树，判断琪是否是自身的镜像（即：是否对称）
 * 例如：下面这棵二叉树是对称的
 * 1
 * /  \
 * 2    2
 * / \    / \
 * 3 4  4  3
 * 下面这棵二叉树不对称。
 * 1
 * / \
 * 2   2
 * \    \
 * 3    3
 * @Author li-yuanwen
 * @Date 2021/2/2 21:02
 */
public class Symmetric {

    /**
     * @param root TreeNode类
     * @return bool布尔型
     */
    public boolean isSymmetric(TreeNode root) {
        // write code here
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root.getLeft());
        queue.offer(root.getRight());
        return doIsSymmetric(queue);
    }

    /**
     * 子树为null也加入队列，保证位置堆成
     **/
    private boolean doIsSymmetric(Queue<TreeNode> queue) {
        int size = queue.size();
        if (size == 0) {
            return true;
        }

        if (size % 2 != 0) {
            return false;
        }

        Integer[] vs = new Integer[size];
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node != null) {
                vs[i] = node.getVal();
                queue.offer(node.getLeft());
                queue.offer(node.getRight());
            }

        }

        int half = size / 2;
        for (int j = 0; j < half; j++) {
            Integer left = vs[j];
            Integer right = vs[size - j - 1];
            boolean leftNull = left == null;
            boolean rightNull = right == null;
            // 对称节点均为null
            if (leftNull && rightNull) {
                continue;
            }
            // 对称节点均不为null
            if (leftNull == rightNull) {
                if (!left.equals(right)) {
                    return false;
                }
                continue;
            }
            return false;
        }
        return doIsSymmetric(queue);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1, new TreeNode(2), new TreeNode(2));
        Symmetric symmetric = new Symmetric();
        System.out.println(symmetric.isSymmetric(node));
    }

}
