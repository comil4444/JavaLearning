package com.cn.sort;

public class InsertSort implements ISort{
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++)
        {
            //从小到大排序，如果当前元素已经大于前序有序元素最大的，则直接跳过
            if (arr[i] < arr[i - 1])
            {
                int temp = arr[i];
                int j;
                //从后往前比较
                for (j = i - 1; j >= 0 && temp < arr[j]; j--)
                {
                    //挪动元素，为了插入排序腾位置
                    arr[j + 1] = arr[j];
                }
                //腾出的位置直接插入
                arr[j + 1] = temp;
            }
        }
    }
}
