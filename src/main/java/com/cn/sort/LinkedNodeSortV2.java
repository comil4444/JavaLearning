package com.cn.sort;

public class LinkedNodeSortV2 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(7, listNode1);
        ListNode listNode3 = new ListNode(3, listNode2);
        ListNode listNode4 = new ListNode(2, listNode3);
        ListNode listNode5 = new ListNode(-1, listNode4);

        ListNode temp = new LinkedNodeSortV2().mergeSort(listNode5);
        while (temp != null) {
            System.out.print(temp.val + "\t");
            temp = temp.next;
        }
    }


//    public static ListNode quickSort(ListNode root) {
//        if (root == null) {
//            return null;
//        }
//        ListNode partition = partition(root);
//
//    }

    public static ListNode mergeSort(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }

        ListNode slow = root;
        ListNode fast = root.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;

        ListNode p1 = mergeSort(mid);
        ListNode p2 = mergeSort(root);
        return merge(p1, p2);
    }

    private static ListNode merge(ListNode p1, ListNode p2) {
        ListNode head = new ListNode(-1, null);
        ListNode temp = head;
        while (p1 != null || p2 != null) {
            if (p1 == null) {
                temp.next = p2;
                break;
            }
            if (p2 == null) {
                temp.next = p1;
                break;
            }

            if (p1.val > p2.val) {
                temp.next = p2;
                p2 = p2.next;
            } else {
                temp.next = p1;
                p1 = p1.next;
            }
            temp = temp.next;
        }
        return head.next;
    }


    private static class ListNode {
        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        ListNode next;
        int val;
    }
}
