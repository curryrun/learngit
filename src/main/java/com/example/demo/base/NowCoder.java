package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2018/12/23 下午1:41
 */
public class NowCoder {

    public static void main(String[] args) {
        System.out.println(count(4, 2, 2));

        int[] arr1 = new int[]{5, 6, 1, 4, 3, 7, 88, 99, 22, 2};

        // 位运算
        int[] arr = new int[]{5, 5, 6, 7, 6, 7, 1, 2, 12, 2, 1};
//        int res = 0;
//        for (int i = 0; i < arr.length; ++i) {
//            res = res ^ arr[i];
//        }
//        System.out.println(res);

        quickS(arr1, 0, arr1.length - 1);
        for(int i = 0; i< arr1.length; ++i){
            System.out.println(arr1[i]);
        }
    }

    public static int count(int T, int S, int q) {
        int count = 0;
        double myQ = q;
        double v = (myQ - 1) / myQ;
//        double pre = S * v;
        int j = 0;
        for (double i = 0; i <= T; ++i) {
            if ((S + j) * v >= T) {
                break;
            }
            if (i >= (S + j) * v) {
                i = 0;
                ++count;
            }
            ++j;
        }
        return count;
    }

    public static int sort(int[] arr, int left, int right){
        int temp = arr[left];
        while (left < right){
            while (left < right && arr[right] >= temp){
                --right;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp){
                ++left;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return left;
    }

    public static void quickS(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        int mid = sort(arr, left, right);
        quickS(arr, left, mid - 1);
        quickS(arr, mid + 1, right);
    }

}
