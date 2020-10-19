package com.cn.sort;

import java.util.Arrays;
import java.util.Random;

public class NumberGenerator {
    public static int[] generateRandomNumbers(int count, int maxNumber) {
        Random random = new Random(System.currentTimeMillis());
        int[] result = new int[count];

        for (int i = 0; i < count; i++) {
            result[i] = random.nextInt(maxNumber);
        }

        return result;
    }

    public static int[] generateNearlySortedNumbers(int count, int swapTimes) {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = i;
        }

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < swapTimes; i++) {
            int x = random.nextInt(count);
            int y = random.nextInt(count);

            result[x] = result[x] + result[y];
            result[y] = result[x] - result[y];
            result[x] = result[x] - result[y];
        }

        return result;
    }
}
