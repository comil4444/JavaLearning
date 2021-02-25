package com.cn.eric.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagrams {

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

    public static void main(String[] args) {
        String str = "//.////.///".replaceAll("/+","/");
        System.out.println(str.substring(0, str.lastIndexOf("/")));
    }
}
