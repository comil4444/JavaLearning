package com.cn.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MergeSort implements ISort{
    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }


    }

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i + "");
        }
        System.out.println("finish!");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println("sub\t" + iterator.next());

                iterator.remove();
            }
        });

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("main\t" + iterator.next());
            iterator.remove();
        }
    }
}
