package com.cn.eric.leetcode;

import java.util.TreeMap;

class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }
        int sum = 0;
        for (int i = 0 ; i < arr.length ; i++) {
            sum += arr[i];
        }

        if (sum % 3 != 0) {
            return false;
        }

        int part = sum/3;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        int count = 0;
        for (int i = 0; i < arr.length - 2; i ++) {
            if (map.get(i) == null || map.get(i) <= 0) {
                continue;
            }
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (map.get(j) == null || map.get(j) <= 0) {
                    continue;
                }
                int left = part - arr[i] - arr[j];
                if (map.get(left) != null && map.get(left) > 0) {
                    count++;
                    map.put(left, map.get(left) - 1);
                    map.put(left, map.get(i) - 1);
                    map.put(left, map.get(j) - 1);
                }
                if (count == 2) {
                    return true;
                }
            }
        }

        return false;
    }
}