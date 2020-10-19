package com.cn.sort;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        int[] numbers = NumberGenerator.generateRandomNumbers(50, 100);
        System.out.println(Arrays.toString(numbers));
        new QuickSortV2().sort(numbers);
        System.out.println(Arrays.toString(numbers));

        numbers = NumberGenerator.generateRandomNumbers(50, 100);
        System.out.println(Arrays.toString(numbers));
        new BubbleSort().sort(numbers);
        System.out.println(Arrays.toString(numbers));

        numbers = NumberGenerator.generateRandomNumbers(50, 100);
        System.out.println(Arrays.toString(numbers));
        new QuickSortV5().sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
