package com.example.demo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangdongrun
 * @date 2018/12/13 下午4:31
 */
public class Sort {
    public static void main(String[] args) {
        int[] arr1 = new int[]{5, 6, 1, 4, 3, 7, 9, 8, 2};

        // 位运算
        int[] arr = new int[]{5, 5, 6, 7, 6, 7, 1, 2, 12, 2, 1};
        int res = 0;
        for (int i = 0; i < arr.length; ++i) {
            res = res ^ arr[i];
        }
        System.out.println(res);

//        quickS(arr1, 0, arr1.length - 1);

//        int[] temp = new int[arr1.length];
//        guibingS(arr1, 0, arr1.length - 1, temp);

        sortG(arr1);

        for (int i = 0; i < arr1.length; ++i) {
            System.out.println(arr1[i]);
        }
    }

    public static void sortG(int arr[]) {
        int temp[] = new int[arr.length];
        sortGuibing(arr, 0, arr.length - 1, temp);
    }

    public static void sortGuibing(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sortGuibing(arr, left, mid, temp);
        sortGuibing(arr, mid + 1, right, temp);
        mergeArr(arr, left, mid, right, temp);
    }

    public static void mergeArr(int[] arr, int left, int mid, int right, int[] temp) {
        int arr1Left = left;
        int arr2Left = mid + 1;
        int count = 0;
        while (arr1Left <= mid && arr2Left <= right) {
            if (arr[arr1Left] < arr[arr2Left]) {
                temp[count] = arr[arr1Left];
                ++arr1Left;
            } else {
                temp[count] = arr[arr2Left];
                ++arr2Left;
            }
            ++count;
        }
        while (arr1Left <= mid) {
            temp[count] = arr[arr1Left];
            ++arr1Left;
            ++count;
        }
        while (arr2Left <= right) {
            temp[count] = arr[arr2Left];
            ++arr2Left;
            ++count;
        }
        int i = 0;
        while (left <= right){
            arr[left] = temp[i];
            ++left;
            ++i;
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
        while (end != 0) {
            int large = findLarge(A, end);
            flip(A, large);
            flip(A, end - 1);
            res.add(large + 1);
            res.add(end);
            --end;
        }
        return res;
    }

    public static int findLarge(int[] A, int target) {
        for (int i = 0; i < A.length; ++i) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void flip(int[] A, int end) {
        int start = 0;
        while (start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            ++start;
            --end;
        }
    }

    // 归并排序
    private static void guibingS(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        guibingS(arr, left, mid, temp);//左边归并排序，使得左子序列有序
        guibingS(arr, mid + 1, right, temp);//右边归并排序，使得右子序列有序
        merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }


}
