package com.example.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
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
            if(useArr[i] == true || ( i - 1 >= 0 && nums[i- 1] == nums[i] && useArr[i - 1] == false)){
                continue;
            }
            tempList.add(nums[i]);
            useArr[i] = true;
            helper3(list, nums, tempList, useArr);
            tempList.remove(tempList.size() - 1);
            useArr[i] = false;
        }
    }

}
