package com.example.demo.base;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

//        sortG(arr1);

//        insertSort(arr1);

//        insertSortMy(arr1);

        for (int i = 0; i < arr1.length; ++i) {
            System.out.println(arr1[i]);
        }

        findKthLargest(arr1, 3);
    }

    // 插入排序 是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
    public static void insertSortMy(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            // 有序队列的末尾
            int preIndex = i - 1;
            // 先把当前位置的值记录一下
            int now = arr[i];
            // 如果当前值小于有序队列末尾值，则有序队列整个向后移动
            while (preIndex >= 0 && now < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                --preIndex;
            }
            // 把临时存储的当前位置的值放在被移动出来的位置上
            arr[preIndex + 1] = now;
        }
    }


    // 归并排序思想 拆分数组成小块 然后将两个数组进行合并
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
        while (left <= right) {
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

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k + 1);
        int i = 0;
        for(; i< k; ++i){
            queue.add(nums[i]);
        }
        for(; i< nums.length; ++i){
//            if(!queue.contains(nums[i])){
            queue.add(nums[i]);
//            }
            if(queue.size() > k || nums[i] > queue.peek()){
                queue.poll();
            }
        }
        return queue.peek();
    }

    public static int quickFindKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (true){
            int mid = sorts(nums, left, right);
            if(mid == nums.length - k){
                return nums[mid];
            }else if(mid < nums.length - k){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
    }

    // 核心思想在于交换 把小的换到左边 大的换到右边
    public static int sorts(int arr[], int left, int right) {
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


}
