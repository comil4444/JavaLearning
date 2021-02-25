package com.cn.eric.leetcode;

public class ReverseLinkedNode {

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tempHead = null, current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = tempHead;
            tempHead = current;
            current = next;
        }

        return tempHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, null);
        ListNode l2 = new ListNode(2, l1);
        ListNode l3 = new ListNode(3, l2);
        ListNode l4 = new ListNode(4, l3);

        ListNode temp = l4;
        while (temp != null) {
            System.out.print(temp.val);
            temp = temp.next;
        }
        System.out.println();

        temp = reverse(l4);
        while (temp != null) {
            System.out.print(temp.val);
            temp = temp.next;
        }
    }
}
