package com.cn.eric.leetcode;

import java.util.Arrays;

public class DynamicPlanning {


    public int cutSteelBottomUp(int[] values, int len) {
        if (values == null || values.length == 0) {
            return 0;
        }

        if(len == 0) {
            return 0;
        }

        int[] result = new int[values.length + 1];
        Arrays.fill(result, -1);

        return cutSteelOptimizeInternal(values, len, result);
    }



    public int cutSteelOptimize(int[] values, int len) {
        if (values == null || values.length == 0) {
            return 0;
        }

        if(len == 0) {
            return 0;
        }

        int[] result = new int[values.length + 1];
        Arrays.fill(result, -1);

        return cutSteelOptimizeInternal(values, len, result);
    }

    private int cutSteelOptimizeInternal(int[] values, int len, int[] result) {
        if (len == 0) {
            return 0;
        }

        if (result[len] != -1) {
            return result[len];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= len ; i++) {
            max = Math.max(max, cutSteelOptimizeInternal(values, len - i, result) + values[i - 1]);
        }
        result[len] = max;
        return max;
    }

    public int cutSteel(int[] values, int len) {
        if (values == null || values.length == 0) {
            return 0;
        }

        if(len == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            max = Math.max(max, values[i - 1] + cutSteel(values, len - i));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new DynamicPlanning().cutSteelOptimize(new int[]{1,5,8,9,10,17,17,20,24,30}, 4));
    }
}
