package com.niuke;

import com.model.ListNode;

import java.util.List;

/**
 * @Description 合并k个已排序的链表并将其作为一个已排序的链表返回。分析并描述其复杂度。
 * @Author li-yuanwen
 * @Date 2021/2/25 19:37
 */
public class MergeKLists {

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }

        int size = lists.size();
        if (size == 1) {
            return lists.get(0);
        }

        ListNode node = lists.get(0);
        for (int i = 1; i < size; i++) {
            node = doMergeTwoList(node, lists.get(i));
        }

        return node;

    }

    private ListNode doMergeTwoList(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        ListNode node = new ListNode(0);
        ListNode temp = node;
        while (left != null && right != null) {
            int v1 = left.val;
            int v2 = right.val;

            if (v1 < v2) {
                temp.next = left;
                temp = temp.next;
                left = left.next;
            }else {
                temp.next = right;
                temp = temp.next;
                right = right.next;
            }
        }

        if (left != null) {
            temp.next = left;
        }

        if (right != null) {
            temp.next = right;
        }

        return node.next;
    }

}
