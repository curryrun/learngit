package com.example.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangdongrun
 * @date 2019/5/3 下午1:38
 */
public class 排列组合 {

    public static void main(String[] args) {
//        System.out.println(subsets(new int[]{1, 2, 3}));
//        System.out.println(pailie(new int[]{1, 2, 3}));
        System.out.println(pailie2(new int[]{1, 1, 2}));
    }

    // 子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        helper(list, nums, new ArrayList<>(), 0);
        return list;
    }

    public static void helper(List<List<Integer>> list, int[] nums, List<Integer> tempList, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; ++i) {
            tempList.add(nums[i]);
            helper(list, nums, tempList, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 排列
    public static List<List<Integer>> pailie(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        helper2(list, nums, new ArrayList<>());
        return list;
    }

    public static void helper2(List<List<Integer>> list, int[] nums, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        }
        for (int i = 0; i < nums.length; ++i) {
            // 有了这个条件限制了递归，所以这个办法的上面就不用return
            if (!tempList.contains(nums[i])) {
                tempList.add(nums[i]);
                helper2(list, nums, tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // 排列 有重复的情况
    public static List<List<Integer>> pailie2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        // 记录某个位置上的数有没有用过 不能把已经加过的数字在下一轮循环中使用
        boolean[] useArr = new boolean[nums.length];
        helper3(list, nums, new ArrayList<>(), useArr);
        return list;
    }

    // boolean[] useArr是记录当前tempList是否被正在使用，如果在后面的数看到前面的数是false了其实也是被用了
    public static void helper3(List<List<Integer>> list, int[] nums, List<Integer> tempList, boolean[] useArr) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            // 加完结果集之后就直接退出
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (useArr[i] == true || (i - 1 >= 0 && nums[i - 1] == nums[i] && useArr[i - 1] == false)) {
                continue;
            }
            tempList.add(nums[i]);
            useArr[i] = true;
            helper3(list, nums, tempList, useArr);
            tempList.remove(tempList.size() - 1);
            useArr[i] = false;
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        boolean[] usedArr = new boolean[candidates.length];
        helpCombinationSum(candidates, target, res, tempList, 0);
        return res;
    }

    public void helpCombinationSum(int[] candidates, int remain, List<List<Integer>> res, List<Integer> tempList, int start) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; ++i) {
            tempList.add(candidates[i]);
            helpCombinationSum(candidates, remain - candidates[i], res, tempList, i);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 和上一题类似 但是有重复
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        Arrays.sort(candidates);
        boolean[] usedArr = new boolean[candidates.length];
        helpCombinationSum2(candidates, target, res, tempList, 0, usedArr);
        return res;
    }

    public void helpCombinationSum2(int[] candidates, int remain, List<List<Integer>> res, List<Integer> tempList, int start, boolean[] usedArr) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; ++i) {
            if (i >= 1 && usedArr[i - 1] == false && candidates[i - 1] == candidates[i]) {
                continue;
            }
            tempList.add(candidates[i]);
            usedArr[i] = true;
            helpCombinationSum2(candidates, remain - candidates[i], res, tempList, i + 1, usedArr);
            usedArr[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

    // 回文串
    public List<List<String>> partition(String s) {
        List<List<String>> res =new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        helpPartition(res, tempList, s, 0);
        return res;
    }

    public void helpPartition(List<List<String>> res, List<String> tempList, String s, int start){
        if(start == s.length()){
            res.add(new ArrayList<>(tempList));
        }else {
            for(int i = start; i< s.length(); ++i){
                if(isHui(start, i, s)){
                    tempList.add(s.substring(start, i + 1));
                    helpPartition(res, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    public boolean isHui(int left, int right, String s){
        while (left< right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }

}
