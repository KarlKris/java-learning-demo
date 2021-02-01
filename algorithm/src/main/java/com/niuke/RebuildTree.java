package com.niuke;

import com.model.TreeNode;

/**
 * @Description 牛客网习题 重建二叉树
 * @Author li-yuanwen
 * @Date 2021/1/27 20:55
 */
public class RebuildTree {

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树
     * 。假设输入的前序遍历和中序遍历的结果中都不含重复的数字
     * 。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     **/
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        return recursion(pre, 0, pre.length - 1, in, 0, in.length - 1);
//        return doReConstructBinaryTree(pre, in);

    }

    /**
     *  运行时间：148ms 占用内存：23464KB
     **/
    private TreeNode doReConstructBinaryTree(int[] pre, int[] in) {
        int preLength = pre.length;
        int inLength = in.length;

        if (preLength == 0 || inLength == 0) {
            return null;
        }

        int root = pre[0];
        TreeNode rootNode = new TreeNode(root);

        int i = 0;
        for (; i < inLength; i++) {
            int v = in[i];
            if (v == root) {
                break;
            }
        }

        int[] preLeftCp = new int[i];
        System.arraycopy(pre, 1, preLeftCp, 0, i);
        int[] inLeftCp = new int[i];
        System.arraycopy(in, 0, inLeftCp, 0, i);

        rootNode.setLeft(reConstructBinaryTree(preLeftCp, inLeftCp));

        int[] preRightCp = new int[preLength - i - 1];
        System.arraycopy(pre, i + 1, preRightCp, 0, preLength - i - 1);
        int[] inRightCp = new int[preLength - i - 1];
        System.arraycopy(in, i + 1, inRightCp, 0, preLength - i - 1);

        rootNode.setRight(reConstructBinaryTree(preRightCp, inRightCp));
        return rootNode;
    }

    /**
     *  运行时间：134ms 占用内存：23672KB
     **/
    private TreeNode recursion(int[] pre, int startPreIndex, int endPreIndex, int[] in, int startInIndex, int endInIndex) {
        int inLength = in.length;

        int preValidLength = endPreIndex - startPreIndex;
        if (preValidLength < 0) {
            return null;
        }

        int root = pre[startPreIndex];
        TreeNode rootNode = new TreeNode(root);

        int i = 0;
        for (; i < inLength; i++) {
            int v = in[i];
            if (v == root) {
                break;
            }
        }

        int sub = Math.abs(i - startInIndex);

        rootNode.setLeft(recursion(pre, startPreIndex + 1, Math.min(startPreIndex + sub, endPreIndex), in, startInIndex, i - 1));
        rootNode.setRight(recursion(pre, startPreIndex + sub + 1, endPreIndex, in, i + 1, endInIndex));

        return rootNode;
    }


    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        RebuildTree tree = new RebuildTree();
        TreeNode node = tree.reConstructBinaryTree(pre, in);
        System.out.println(node);

    }

}
