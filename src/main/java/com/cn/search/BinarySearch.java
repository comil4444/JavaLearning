package com.cn.search;

public class BinarySearch {

    public boolean binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0 ) {
            return false;
        }

        int l = 0;
        int r = arr.length -1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int low = 0;
        int high = matrix.length - 1;
        int rowIndex = -1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (matrix[mid][0] < target) {
                low = mid + 1;
            } else if (matrix[mid][0] > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }

        if (low == 0) {
            return false;
        }

        if (matrix[low - 1][matrix[low - 1].length - 1] >= target && matrix[low - 1][0] < target) {
            rowIndex = low - 1;
        } else {
            return false;
        }

        int left = 0;
        int right = matrix[rowIndex].length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (matrix[rowIndex][mid] < target) {
                left = mid + 1;
            } else if (matrix[rowIndex][mid] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new BinarySearch().searchMatrix(new int[][]{{1}}, 2));
    }
}
