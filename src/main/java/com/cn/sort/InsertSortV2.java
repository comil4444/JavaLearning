package com.cn.sort;

public class InsertSortV2 implements ISort{
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }
}
