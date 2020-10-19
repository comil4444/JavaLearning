package com.cn.sort;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        int[] numbers = NumberGenerator.generateRandomNumbers(50, 100);
        System.out.println(Arrays.toString(numbers));

        new InsertSort().sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
