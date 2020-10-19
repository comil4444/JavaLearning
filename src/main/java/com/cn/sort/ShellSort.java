package com.cn.sort;

public class ShellSort implements ISort{
    @Override
    public void sort(int[] arr) {
        int length = arr.length;
        int h = 1;
        while(h < length/3){
            //h的取值遵循等差数列：1,4,13,40,121,364...
            //并且h最终定能取1
            h = 3*h + 1;
        }
        while(h >= 1){
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h; j -= h) {//h有序子序列
                    if (arr[j] < arr[j-h]) {
                        swap(arr, j, j-h);
                    }else{
                        break;
                    }
                }
            }
            h = h/3;
        }
    }
}
