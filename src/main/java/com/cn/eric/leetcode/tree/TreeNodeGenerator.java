package com.cn.eric.leetcode.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeNodeGenerator {

    /*
     * @Description
     *      广度优先的Val数组
     * @Param [vals]
     * @return com.cn.eric.leetcode.tree.TreeNode
     *
     *
     **/
    public static TreeNode generate(Integer[] vals) {
        if (vals == null || vals.length == 0) {
            throw new IllegalArgumentException();
        }

        TreeNode root = new TreeNode(vals[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        int index = 1;

        while (stack.size() != 0) {
            int levelSize = stack.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode treeNode = stack.pollFirst();
                if (index >= vals.length) {
                    return root;
                }
                if (vals[index] == null) {
                    treeNode.left = null;
                    index++;
                } else {
                    treeNode.left = new TreeNode(vals[index++]);
                    stack.addLast(treeNode.left);
                }
                if (index >= vals.length) {
                    return root;
                }

                if (vals[index] == null) {
                    treeNode.right = null;
                    index++;
                } else {
                    treeNode.right = new TreeNode(vals[index++]);
                    stack.addLast(treeNode.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] temp = {1,2,3,4,null,null,5,6};
        System.out.println(Arrays.toString(temp));
        TreeNode treeNode = TreeNodeGenerator.generate(temp);
        List<List<Integer>> vals = new BST().bst(treeNode);
        vals.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));

        vals = new DST().dst(treeNode);
        vals.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));

    }
}
