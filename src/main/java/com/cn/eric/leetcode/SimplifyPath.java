package com.cn.eric.leetcode;

import java.util.LinkedList;

public class SimplifyPath {

    public String simplifyPath(String path) {
        LinkedList linkedList = new LinkedList();
        if (path == null || path.length() == 0 ) {
            return null;
        }

        String trimPath = path.trim().replaceAll("/+","/");
        trimPath = trimPath.endsWith("/") ? trimPath.substring(0, trimPath.lastIndexOf("/")) : trimPath;
        String[] splitPath = trimPath.split("/");
        for (int i = 1; i < splitPath.length; i++) {
            if (splitPath[i].equals(".")) {
                continue;
            } else if (splitPath[i].equals("..")) {
                if (linkedList.size() > 0) {
                    linkedList.removeLast();
                }
            } else {
                linkedList.addLast(splitPath[i]);
            }
        }

        if (linkedList.size() ==0) {
            return "/";
        }
        StringBuffer stringBuffer = new StringBuffer();
        linkedList.forEach(str -> stringBuffer.append("/").append(str));
        return stringBuffer.toString();
    }

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0 ) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = chars.length - 1; i >=0 ; i--) {
            if (chars[i] != ' ') {
                count++;
            } else {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new SimplifyPath().lengthOfLastWord("a"));
    }
}
