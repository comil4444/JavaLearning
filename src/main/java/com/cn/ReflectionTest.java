package com.cn;

import java.lang.reflect.Method;

public class ReflectionTest {
    private static int count = 0;
    public static void foo() {
        new Exception("test#" + (count++)).printStackTrace();
    }

    public static void main(String[] args) throws Exception{
        Class<?> aClass = Class.forName("com.cn.ReflectionTest");
        Method foo = aClass.getMethod("foo");
        for (int i = 0; i < 20; i++) {
            foo.invoke(null);
        }
    }
}
