package com.cn.eric.leetcode;

public class IntegerToRoman {
    private static int[] nums = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    private static String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    public static void main(String[] args) {
        System.out.println(new IntegerToRoman().intToRoman(3));
        ;
    }

    public String intToRoman(int num) {
        StringBuffer stringBuffer = new StringBuffer();
        int index = 0;
        while (num != 0 && index < symbols.length) {
            int digit = num / nums[index];
            num = num % nums[index];
            for (int i = 0; i < digit; i++) {
                stringBuffer.append(symbols[index]);
            }
            index++;
        }
        return stringBuffer.toString();
    }
}
