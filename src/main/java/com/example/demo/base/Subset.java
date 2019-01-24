package com.example.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangdongrun
 * @date 2019/1/22 下午2:11
 */
public class Subset {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
//        System.out.println(subsets(arr1));
//        System.out.println(getAll());
//        System.out.println(permute(arr1));
//        System.out.println(pailie(arr1));
        System.out.println(combinationSum(arr1, 3));
    }

    // 输出一个数组的所有子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 4个数选3个数 保证有序 输出所有的可能
    public static List<List<Integer>> getAll() {
        List<List<Integer>> res = new ArrayList<>();
        find(res, new ArrayList<>(), 1, 4, 3);
        System.out.println(res.size());
        return res;
    }

    public static void find(List<List<Integer>> res, List<Integer> temp, int start, int size, int targetNum) {
        if (temp.size() == targetNum) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // start后面的数全放进去也不能满足条件
        if ((size - start + 1) + temp.size() < targetNum) {
            return;
        }
        for (int i = start; i <= size; i++) {
            temp.add(i);
            find(res, temp, i + 1, size, targetNum);
            temp.remove(temp.size() - 1);
        }
    }

    // 排列的时候 循环每次从0开始 要保证位数
    public static List<List<Integer>> pailie(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        pailieDigui(res, new ArrayList<>(), arr);
        return res;
    }

    public static void pailieDigui(List<List<Integer>> res, List<Integer> tempList, int[] arr) {
        if (tempList.size() == arr.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < arr.length; ++i) {
            if (tempList.contains(arr[i])) {
                continue;
            }
            tempList.add(arr[i]);
            pailieDigui(res, tempList, arr);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 组合
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // 组合
    public static List<List<Integer>> zuhe(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        zuheDigui(res, new ArrayList<>(), arr);
        return res;
    }

    public static void zuheDigui(List<List<Integer>> res, List<Integer> tempList, int[] arr) {
        if (tempList.size() == arr.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < arr.length; ++i) {
            if (tempList.contains(arr[i])) {
                continue;
            }
            tempList.add(arr[i]);
            zuheDigui(res, tempList, arr);
            tempList.remove(tempList.size() - 1);
        }
    }

}
