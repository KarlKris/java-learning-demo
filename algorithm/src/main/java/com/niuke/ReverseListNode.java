package com.niuke;

import com.model.ListNode;

/**
 * @Description 反转单链表
 * @Author li-yuanwen
 * @Date 2021/2/1 19:40
 */
public class ReverseListNode {

    /**
     * @param head ListNode类
     * @param k    int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here
        if(head == null || head.next == null || k < 2) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head, temp;
        int len = 0;
        while (head != null) {
            len ++ ;
            head = head.next;
        }
        for (int i = 0; i < len / k; i ++ ) {
            for (int j = 1; j < k; j ++ ) {
                temp = cur.next;
                cur.next = temp.next;
                temp.next = pre.next;
                pre.next = temp;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;

    }

    public static void main(String[] args) {
        ReverseListNode reverseListNode = new ReverseListNode();
        int[] vs = {1,2,3,4,5};

        ListNode head = null;
        ListNode next = null;
        for (int i = 0; i < vs.length; i++) {
            int v = vs[i];
            ListNode node = new ListNode(v);
            if (i == 0) {
                head = node;
            }

            if (i == 1) {
                next = node;
                head.next = next;
            }

            if (i > 1) {
                next.next = node;
                next = node;
            }

        }
        System.out.println("------------------------------------");
        System.out.println(reverseListNode.reverseKGroup(head, 4));
    }


}
