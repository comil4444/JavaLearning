package com.cn.sort;

import java.util.Arrays;

public class SortExercise {

    public static void main(String[] args) {
        int[] arr = NumberGenerator.generateRandomNumbers(30, 100);
        System.out.println(Arrays.toString(arr));
        bubbleSort(Arrays.copyOf(arr, arr.length));
        selectionSort(Arrays.copyOf(arr, arr.length));
        insertSort(Arrays.copyOf(arr, arr.length));
        quickSort(Arrays.copyOf(arr, arr.length));
    }

    public static void quickSort(int[] arr) {
        doQuickSort(arr, 0, arr.length - 1);
    }

    private static void doQuickSort(int[] arr, int left, int right) {
        if (left < right) {
            int index = findIndex(arr, left, right);
            doQuickSort(arr, left, index - 1);
            doQuickSort(arr, index, right);
        }
    }

    private static int findIndex(int[] arr, int left, int right) {
        int flag = arr[left];
        int l = left, r = right;
        while (l < r) {
            while (arr[l] <= flag && l < r) l++;
            while (arr[r] >= flag && l < r) r--;
            if (l < r) {
                swap(arr, l, r);
                l++;
                r--;
            }
        }

        if (arr[l] < flag) {
            swap(arr, l, left);
        }
        return l;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "\t" + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            if (current < arr[j]) {
                for (; j >= 0 && arr[j] > current ; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = current;
            }
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "\t" + Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = minIndex + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "\t" + Arrays.toString(arr));
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }







}
