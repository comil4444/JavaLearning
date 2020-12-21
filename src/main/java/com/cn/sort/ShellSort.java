package com.cn.sort;

public class ShellSort implements ISort{
    @Override
    public void sort(int[] arr) {
        if (arr != null && arr.length != 0 ) {
            int increment = arr.length;
            do {
                increment = increment/3 + 1;

                for (int i = 0; i < increment; i++) {
                    for (int j = i + increment; j < arr.length; j+=increment) {
                        int temp = arr[j];
                        int k = j - increment;
                        for (; k >= 0 && arr[k] > temp; k-=increment) {
                            arr[k + increment] = arr[k];
                        }
                        arr[k+increment] = temp;
                    }
                }
            } while (increment > 1);
        }
    }
}
