package com.algorithm.map;

/**
 * @author li-yuanwen
 * 基于树的抽象map
 */
public abstract class AbstractTreeMap<K, V> implements IndexMap<K, V> {

    /** 节点接口 **/
    interface Node<K, V> {

        /**
         * 获取键值
         * @return 键值
         */
        K getKey();

        /**
         * 获取值
         * @return 值
         */
        V getValue();

        /**
         * 设置新值
         * @param v 新值
         */
        void setValue(V v);

        /**
         * 获取左子树
         * @return 左子树
         */
        Node<K, V> getLeft();

        /**
         *  获取右子树
         * @return 右子树
         */
        Node<K, V> getRight();

        /**
         * 添加左子树
         * @param left 左子树
         */
        void setLeft(Node<K, V> left);

        /**
         * 添加右子树
         * @param right 右子树
         */
        void setRight(Node<K, V> right);
        /**
         * 是否是红节点
         * @return 红节点 true
         */
        boolean isRed();

        /**
         * 更改节点颜色
         */
        void changeColor(boolean red);


    }
}
