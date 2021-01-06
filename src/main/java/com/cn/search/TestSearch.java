package com.cn.search;

import com.cn.sort.NumberGenerator;

import java.util.Arrays;

public class TestSearch {

    public static void main(String[] args) {
        int[] num = NumberGenerator.generateRandomNumbers(50, 100);
//        new QuickSortV2().sort(num);
        System.out.println(Arrays.toString(num));
        System.out.println(new BinarySearch().binarySearch(num, 10));
        System.out.println(new BinarySearch().binarySearch(num, 11));
        System.out.println(new BinarySearch().binarySearch(num, 12));
        System.out.println(new BinarySearch().binarySearch(num, 13));
    }
}
