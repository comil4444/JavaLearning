package com.cn.sort;

public class LinkedNodeSort {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(7, listNode1);
        ListNode listNode3 = new ListNode(3, listNode2);
        ListNode listNode4 = new ListNode(2, listNode3);
        ListNode listNode5 = new ListNode(10, listNode4);

        ListNode temp = mergeSort(listNode5);
        while (temp != null) {
            System.out.print(temp.val + "\t");
            temp = temp.next;
        }

    }

    //归并排序
    public static ListNode mergeSort(ListNode head){
        if(head == null || head.next == null)  //空链表或者只有单个结点
            return head;
        ListNode slow = head, fast = head.next;

        while(fast != null && fast.next != null){  //使用快慢指针寻找中间 结点
            slow = slow.next;

            fast = fast.next;
            if(fast.next != null)
                fast = fast.next;
        }

        ListNode ptr1 = slow.next;
        slow.next = null;

        ListNode tmp1 = mergeSort(head);
        ListNode tmp2 = mergeSort(ptr1);
        return merge(tmp1, tmp2);
    }


    public static ListNode merge(ListNode start1,  ListNode start2){
        ListNode header = new ListNode(-1);
        ListNode pre = header;

        ListNode ptr1 = start1, ptr2 = start2;
        while(ptr1 != null && ptr2 != null){
            if(ptr1.val <= ptr2.val){
                pre.next = ptr1;
                pre = ptr1;
                ptr1 = ptr1.next;
            }else{
                pre.next = ptr2;
                pre = ptr2;
                ptr2 = ptr2.next;
            }
        }
        while(ptr1 != null){
            pre.next = ptr1;
            pre = ptr1;
            ptr1 = ptr1.next;
        }

        while(ptr2 != null){
            pre.next = ptr2;
            pre = ptr2;
            ptr2 = ptr2.next;
        }


        return header.next;

    }



    //快速排序
    public static void quickSort(ListNode begin, ListNode end){
        if(begin == null || begin == end)
            return;

        ListNode index = paration(begin, end);
        quickSort(begin, index);
        quickSort(index.next, end);
    }

    /**
     * 划分函数，以头结点值为基准元素进行划分
     * @param begin
     * @param end
     * @return
     */
    public static ListNode paration(ListNode begin, ListNode end){
        if(begin == null || begin == end)
            return begin;

        int val = begin.val;  //基准元素
        ListNode index = begin, cur = begin.next;

        while(cur != end){
            if(cur.val < val){  //交换
                index = index.next;
                int tmp = cur.val;
                cur.val = index.val;
                index.val = tmp;
            }
            cur = cur.next;
        }


        begin.val = index.val;
        index.val = val;

        return index;
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
