package com.niuke;

import com.model.ListNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 闭环检测
 * @Author li-yuanwen
 * @Date 2021/2/2 19:59
 */
public class HasCycle {

    //快慢指针能相遇说明有环！
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /** 对于一个给定的链表，返回环的入口节点，如果没有环，返回null **/
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 相遇点
        ListNode meet = null;

        // 检查相遇点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                meet =  slow;
                break;
            }
        }

        if (meet == null) {
            return null;
        }

        // 一个从相遇点出发，一个从起点触发，相遇点一定是环起点
        fast = head;
        while (fast != meet) {
            meet = meet.next;
            fast = fast.next;
        }


        return meet;
    }

    public static void main(String[] args) {
        HasCycle cycle = new HasCycle();
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println(cycle.detectCycle(n1));

    }
}
