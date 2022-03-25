package com.cn.sort;

public class ShellSort implements ISort{
    @Override
    public void sort(int[] nums) {
        
        if (nums != null && nums.length != 0 ) {
            int increment = nums.length;
            do {
                increment = increment/3 + 1;

                for (int i = 0; i < increment; i++) {
                    for (int j = i + increment; j < nums.length; j+=increment) {
                        int temp = nums[j];
                        int k = j - increment;
                        for (; k >= 0 && nums[k] > temp; k-=increment) {
                            nums[k + increment] = nums[k];
                        }
                        nums[k+increment] = temp;
                    }
                }
            } while (increment > 1);
        }
    }
}
