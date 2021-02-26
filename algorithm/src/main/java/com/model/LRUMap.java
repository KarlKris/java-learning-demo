package com.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 *
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * [要求]
 *   set和get方法的时间复杂度为O(1)
 *   某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 *   当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 *
 * @Author li-yuanwen
 * @Date 2021/2/21 20:29
 */
public class LRUMap<K, V> {

    private int size;
    private Map<K, LinkNode<K, V>> map;
    private LinkNode<K, V> head;
    private LinkNode<K, V> tail;

    public LRUMap(int size) {
        this.size = size;
        this.map = new HashMap<>(size);
    }

    public void set(K k, V v) {
        updateNode(k, v);

        if (map.size() > size) {
            K oldKey = head.k;
            LinkNode<K, V> next = head.next;

            map.remove(oldKey);
            head = next;
            head.prev = null;
        }

    }


    private void updateNode(K k, V v) {
        LinkNode<K, V> node = new LinkNode<>(k, v);
        if (head == null) {
            head = node;
        }

        if (tail == null) {
            tail = node;
        }else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        // 移除
        LinkNode<K, V> oldNode = map.put(k, node);
        if (oldNode != null) {
            LinkNode<K, V> prev = oldNode.prev;
            LinkNode<K, V> next = oldNode.next;

            if (prev == null) {
                head = next;
            }else {
                prev.next = next;
                next.prev = prev;
            }
        }
    }

    public V get(K k) {
        LinkNode<K, V> node = map.get(k);
        if (node == null) {
            return null;
        }

        updateNode(node.k, node.v);
        return node.v;
    }

    class LinkNode<K, V> {

        private LinkNode<K, V> prev;
        private LinkNode<K, V> next;
        private K k;
        private V v;

        LinkNode(K k, V v) {
            this.k = k;
            this.v = v;
        }

    }

}
