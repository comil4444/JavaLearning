package com.cn.eric.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class DST {
    public List<List<Integer>> dst(TreeNode treeNode) {
        List<List<Integer>> result = new ArrayList<>();
        if (treeNode == null) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        list.add(treeNode.val);
        dst(treeNode.left, list, result);
        dst(treeNode.right, list, result);
        return result;
    }

    private void dst(TreeNode root, List<Integer> list, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            List<Integer> subList = new ArrayList<>(list);
            subList.add(root.val);
            result.add(subList);
            return;
        }
        List<Integer> subList = new ArrayList<>(list);
        subList.add(root.val);
        dst(root.left, subList, result);
        dst(root.right, subList, result);
    }
}
