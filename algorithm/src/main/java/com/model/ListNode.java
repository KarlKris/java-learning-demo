package com.model;

import lombok.Data;

/**
 * @Description 单链表元素
 * @Author li-yuanwen
 * @Date 2021/2/1 19:40
 */
@Data
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode() {

    }

    public void add(int v) {
        ListNode next = this.next;
        while (next != null && next.next != null){
            next = next.next;
        }

        next = new ListNode(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListNode)) return false;

        ListNode listNode = (ListNode) o;

        return val == listNode.val;
    }

    @Override
    public int hashCode() {
        return val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
