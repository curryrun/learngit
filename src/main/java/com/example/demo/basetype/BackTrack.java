package com.example.demo.basetype;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname BackTrack
 * @Description
 * @Date 2024/1/13 15:47
 * @Creater zhangdongrun_dxm
 * 回溯算法
 * https://chienmy.gitbook.io/algorithm-pattern-java/ji-chu-suan-fa/backtrack
 **/
public class BackTrack {

    // 78
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        deepSubsets(nums, 0, result, subList);
        return result;
    }

    public void deepSubsets(int[] nums, int start, List<List<Integer>> result, List<Integer> subList) {
        result.add(new ArrayList<>(subList));
        for (int i = start; i < nums.length; ++i) {
            subList.add(nums[i]);
            deepSubsets(nums, i + 1, result, subList);
            subList.remove(subList.size() - 1);
        }
    }

}
