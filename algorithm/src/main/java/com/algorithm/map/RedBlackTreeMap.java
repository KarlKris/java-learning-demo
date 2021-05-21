package com.algorithm.map;

/**
 * @author li-yuanwen
 * 基于红黑二叉树的散列表
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> extends AbstractTreeMap<K, V> {

    /** 跟节点 **/
    private Node<K, V> root;

    @Override
    public void put(K k, V v) {
        if (root == null) {
            root = new RedBlackNode<>(k, v, false);
        }else {

        }
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public V delete(K k) {
        return null;
    }

    @Override
    public boolean containKey(K k) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }




    class RedBlackNode<K extends Comparable<K>, V> implements Node<K, V> {

        /** 键值 **/
        private K key;
        /** Value **/
        private V value;
        /** 左子树 **/
        private Node<K, V> left;
        /** 右子树 **/
        private Node<K, V> right;
        /** 是否是红节点 **/
        private boolean red;

        RedBlackNode(K key, V value, boolean red) {
            this.key = key;
            this.value = value;
            this.red = red;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public Node<K, V> getLeft() {
            return left;
        }

        @Override
        public Node<K, V> getRight() {
            return right;
        }

        @Override
        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        @Override
        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        @Override
        public boolean isRed() {
            return red;
        }
    }

}
