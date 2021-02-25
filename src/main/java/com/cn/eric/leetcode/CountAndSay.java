package com.cn.eric.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(new CountAndSay().countAndSay(2));
        System.out.println(new CountAndSay().countAndSay(3));
        System.out.println(new CountAndSay().countAndSay(4));
    }

    public String countAndSay(int n) {
        String result = "1";
        for (int i = 1 ; i < n ; i ++) {
            char[] temp = result.toCharArray();
            int num = temp[0];
            int count = 0;
            StringBuffer stringBuffer = new StringBuffer();
            for (int  j = 0;  j < temp.length;  j++) {
                if (temp[j] == num) {
                    count++;
                } else {
                    stringBuffer.append(count).append(num - '0');
                    count = 1;
                    num = temp[j];
                }
                if (j == temp.length -1) {
                    stringBuffer.append(count).append(num - '0');
                }
            }
            result = stringBuffer.toString();
        }
        return result;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        if (strs != null && strs.length != 0 ) {
            for (int i = 0; i < strs.length; i++) {
                String raw = strs[i];
                char[] chars = raw.toCharArray();
                Arrays.sort(chars);
                String sortString = new String(chars);
                List<String> temp = map.getOrDefault(sortString, new ArrayList<>());
                temp.add(raw);
                map.put(sortString, temp);
            }
        }
        return map.values().stream().collect(Collectors.toList());
    }


}
