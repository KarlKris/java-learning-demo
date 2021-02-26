package com.niuke;

import com.model.ListNode;

/**
 * @Description 合并有序列表
 * @Author li-yuanwen
 * @Date 2021/2/22 19:34
 */
public class MergeTwoLists {

    /**
     * 将两个有序的链表合并为一个新链表，要求新的链表是通过拼接两个链表的节点来生成的，且合并后新链表依然有序。
     **/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write code here
        return doMergeTwoListsByRecursion(l1, l2);
    }

    /** 递归实现 **/
    private ListNode doMergeTwoListsByRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode node = null;

        int v1 = l1.val;
        int v2 = l2.val;

        if (v1 < v2) {
            node = l1;
            node.next = mergeTwoLists(l1.next, l2);
        }else {
            node = l2;
            node.next = mergeTwoLists(l1, l2.next);
        }

        return node;
    }

    /** 循环实现 **/
    private ListNode doMergeTwoListsByLoop(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode node = new ListNode(0);
        ListNode temp = node;
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;

            if (v1 < v2) {
                temp.next = l1;
                temp  = temp.next;
                l1 = l1.next;
            }else {
                temp.next = l2;
                temp  = temp.next;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            temp.next = l1;
        }

        if (l2 != null) {
            temp.next = l2;
        }

        return node.next;
    }


}
