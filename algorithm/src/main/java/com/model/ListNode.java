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
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
