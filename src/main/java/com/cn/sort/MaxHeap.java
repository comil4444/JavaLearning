package com.cn.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 最小堆
 * 节点的value大于等于子节点
 * 完全二叉树
 **/
public class MaxHeap {
    public static void main(String[] args) {
//        new MaxHeap(new int[]{11,8,1,3,5,7,4,0,9});
//        new MinHeap(new int[]{11,8,1,3,5,7,4,0,9});

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            map.put(i, i);
        }
    }


    private int[] data;

    public MaxHeap(int[] data) {
        this.data = data;
        buildTree();
        System.out.println(Arrays.toString(this.data));
    }

    private void buildTree() {
        for (int i = data.length / 2; i >= 0; i--) {
            treeFy(i);
        }
    }

    public int getRoot() {
        return data[0];
    }

    public void setRoot(int val) {
        data[0] = val;
        treeFy(0);
    }

    private void treeFy(int current) {
        int left = current << 1 + 1 > data.length - 1 ? current : current << 1 + 1;
        int right = (current + 1) << 1 > data.length - 1 ? current : (current + 1) << 1;

        int maxIndex = current;
        if (data[maxIndex] < data[left]) {
            maxIndex = left;
        }

        if (data[maxIndex] < data[right]) {
            maxIndex = right;
        }

        if (maxIndex == current) {
            return;
        }

        swap(maxIndex, current);
        treeFy(maxIndex);
    }

    private void swap(int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}
