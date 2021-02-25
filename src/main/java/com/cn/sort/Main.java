package com.cn.sort;

public class Main {

    public static void main(String[] args) {
        System.out.println(new Main().findSingle(new int[]{4,4,3,3,6,6,7}));
    }

    private int findSingle(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;

        while (left < right) {
            mid = (left + right)/2;
            if (mid % 2 == 0) {
                if (arr[mid] == arr[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (arr[mid] == arr[mid - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return left;
    }
}
