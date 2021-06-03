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
        root = doPut(root, k, v);
        root.changeColor(false);
    }

    private Node<K, V> doPut(Node<K,V> node, K key, V value) {
        if (node == null) {
            return new RedBlackNode<>(key, value, true);
        }

        int compare = compare(node.getKey(), key);
        if (compare < 0) {
            node.setRight(doPut(node.getRight(), key, value));
        }else if (compare > 0) {
            node.setLeft(doPut(node.getLeft(), key, value));
        }else {
            node.setValue(value);
        }

        // 右节点是红节点，左节点是黑节点,则左旋
        if (isRed(node.getRight()) && isRed(node.getLeft())) {
            node = rotateLeft(node);
        }

        // 左节点和左左节点均是红色,右旋
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }

        // 左右节点均为红色,则父子节点兑换颜色
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            flipColor(node);
        }

        return node;
    }



    /** 父子节点兑换颜色 **/
    private void flipColor(Node<K, V> node) {
        node.changeColor(true);
        node.getLeft().changeColor(false);
        node.getRight().changeColor(false);
    }


    /** 左旋 **/
    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);

        // 兑换颜色
        exchangeColor(temp, node);

        return temp;
    }

    /** 右旋 **/
    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> temp = node.getLeft();

        temp.setRight(node);
        node.setLeft(temp.getRight());

        // 兑换颜色
        exchangeColor(node, temp);

        return temp;
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

    /**
     * 比较键值大小
     * @param k 键值a
     * @param o 键值b
     * @return 比较大小
     */
    private int compare(K k, K o) {
        return k.compareTo(o);
    }

    /** 节点颜色对换 **/
    private void exchangeColor(Node<K, V> n1, Node<K, V> n2) {
        boolean red = n1.isRed();
        n1.changeColor(n2.isRed());
        n2.changeColor(red);
    }

    /** 判断节点是否是红色 **/
    private boolean isRed(Node<K, V> node) {
        if (node == null) {
            return false;
        }
        return node.isRed();
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
        public void setValue(V v) {
            this.value = v;
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

        @Override
        public void changeColor(boolean red) {
            this.red = red;
        }
    }

}
