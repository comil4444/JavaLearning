package com.cn.eric.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BST {
    public List<List<Integer>> bst(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (stack.size() != 0) {
            int levelSize = stack.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode treeNode = stack.pollFirst();
                if (treeNode != null) {
                    stack.addLast(treeNode.left);
                    stack.addLast(treeNode.right);
                    level.add(treeNode.val);
                } else {
                    level.add(null);
                }
            }
            result.add(level);
        }
        return result;
    }
}
