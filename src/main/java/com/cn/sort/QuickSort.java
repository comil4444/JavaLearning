package com.cn.sort;

public class QuickSort implements ISort{

    public void sort(int[] arr) {
        if (arr != null && arr.length !=0) {
            internalSort(arr, 0, arr.length -1);
        }
    }

    private void internalSort(int[] arr, int left, int right) {
        if (left < right) {
            int partition = partition(arr, left, right);
            internalSort(arr, left, partition - 1);
            internalSort(arr, partition, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int flag = arr[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (arr[l] <= flag && l < r) l++;
            while (arr[r] >= flag && l < r) r--;
            if (l < r) {
                swap(arr, l , r);
                l++;
                r--;
            }
        }
        if (arr[l] < flag) {
            swap(arr, left, l);
        }

        return l;
    }
}
