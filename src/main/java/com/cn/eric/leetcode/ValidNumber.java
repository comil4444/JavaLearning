package com.cn.eric.leetcode;


import com.cn.eric.leetcode.tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidNumber {

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return null;
        }

        Arrays.sort(nums);

        Set<List<Integer>> result = new HashSet<>(subsetsInternal(nums));
        result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return new ArrayList<>(result);
    }

    private List<List<Integer>> subsetsInternal (int[] nums) {
        if (nums == null) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        if (nums.length == 1) {
            result.add(new ArrayList());
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            result.add(list);
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int[] nextLevel = new int[nums.length - 1];
            System.arraycopy(nums,0,nextLevel,0,i ==0 ? 0:i-1);
            System.arraycopy(nums,i == 0 ? 0 : i + 1,nextLevel,i ==0 ? 0:i-1,nums.length -1 - i);
            result.add(Arrays.stream(nextLevel).boxed().collect(Collectors.toList()));

            List<List<Integer>> nextLevelResult = subsetsInternal(nextLevel);
            result.addAll(nextLevelResult);
        }
        return result;
    }

    public static void main(String[] args) {
//        new ValidNumber().merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6},3);
//        ListNode l1 = new ListNode(3);
//        ListNode l2 = new ListNode(1, l1);
//        ListNode l3 = new ListNode(2, l2);
//        ListNode l4 = new ListNode(4, l3);
//        new ValidNumber().insertionSortList(l4);
        new ValidNumber().subsets(new int[]{1,2,3});
    }


     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         public ListNode(int val) { this.val = val; }
         public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

}
