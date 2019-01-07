package com.example.demo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangdongrun
 * @date 2018/12/13 下午4:31
 */
public class Sort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{5, 6, 1, 4, 3, 7, 88, 99, 22, 2};

        // 位运算
        int[] arr = new int[]{5, 5, 6, 7, 6, 7, 1, 2, 12, 2, 1};
        int res = 0;
        for (int i = 0; i < arr.length; ++i) {
            res = res ^ arr[i];
        }
        System.out.println(res);

        quickS(arr1, 0, arr1.length - 1);
        for (int i = 0; i < arr1.length; ++i) {
            System.out.println(arr1[i]);
        }
    }

    // 核心思想在于交换 把小的换到左边 大的换到右边
    public static int sort(int arr[], int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                --right;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                ++left;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return left;
    }

    public static void quickS(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = sort(arr, left, right);
        quickS(arr, left, mid - 1);
        quickS(arr, mid + 1, right);
    }


    // 煎饼排序
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        int end = A.length;
        while (end != 0){
            int large = findLarge(A, end);
            flip(A, large);
            flip(A, end - 1);
            res.add(large + 1);
            res.add(end);
            --end;
        }
        return res;
    }

    public static int findLarge(int[] A, int target){
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void flip(int[] A, int end){
        int start = 0;
        while (start < end){
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            ++start;
            --end;
        }
    }


}
