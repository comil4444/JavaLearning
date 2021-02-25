package com.cn.sort;

import java.util.Deque;
import java.util.LinkedList;

class Solution {


    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "/";
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        Deque<String> deque = new LinkedList<String>();
        String[] strings = path.split("/");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("")) {
                continue;
            }
            if (strings[i].equals(".")) {
                continue;
            }

            if (strings[i].equals("..")) {
                if (deque.size() == 0) {
                    continue;
                }
                deque.removeLast();
                continue;
            }

            deque.addLast(strings[i]);
        }

        if (deque.size() == 0) {
            return "/";
        }

        StringBuffer stringBuffer = new StringBuffer();
        while (deque.size() != 0) {
            stringBuffer.append("/").append(deque.pollFirst());
        }
        return stringBuffer.toString();
    }
}