package com.cn.eric.leetcode.tree;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Solution {
    public void print() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition singleCondition = lock.newCondition();
        Condition doubleCondition = lock.newCondition();
        AtomicInteger index = new AtomicInteger(1);
        final AtomicBoolean first = new AtomicBoolean(false);

        Thread thread1 = new Thread(() -> {
            while (index.get() <= 100) {
                lock.lock();
                if (!first.get()) {
                    System.out.println(Thread.currentThread().getName() + "---" + index.getAndIncrement());
                    first.set(true);
                    doubleCondition.signal();
                } else {
                    try {
                        singleCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Printer1");
        Thread thread2  = new Thread(() -> {
            while (index.get() <= 100) {
                lock.lock();
                if (first.get()) {
                    System.out.println(Thread.currentThread().getName() + "---" + index.getAndIncrement());
                    first.set(false);
                    singleCondition.signal();
                } else {
                    try {
                        doubleCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Printer2");

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new Solution().print();
    }


}
