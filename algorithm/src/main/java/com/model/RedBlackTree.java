package com.model;

import java.util.Collection;

/**
 * @author li-yuanwen
 * @title: RedBlackTree
 * @projectName Algorithm-Exercise
 * @description: TODO
 * @date 2019-04-2217:51
 */
public class RedBlackTree<T> {

    /**
     *  颜色
     */
    private Color color;

    /**
     * @param <T>
     * 红黑树结点
     */
    private RedBlackTree<T> node;

    public RedBlackTree(RedBlackTree node) {
        this.color = Color.RED;
        this.node = node;
    }

    /**
     * 构建红黑树
     */
    public void build(Collection<T> collection){
        collection.stream().forEach(i->{
            TreeNode node = new TreeNode(i);

        });
    }

    /**
     * 变色
     */
    public void changeColor(){
        if (color == Color.RED){
            color = Color.BLACK;
        }
        color = Color.RED;
    }



    public  class TreeNode<T>{

        private T value;

        private TreeNode<T>  left;

        private TreeNode<T> right;

        public TreeNode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }
    }

    public enum Color{

        RED("RED"),BLACK("BLACK");

        private final String color;

         Color(String color){
            this.color = color;
        }
    }


}
