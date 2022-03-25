package com.cn.daily.exercise;

public class TestVolitale {
    private volatile String a;
    public void test() {
        a = "b";
        System.out.println(a);
    }
}
