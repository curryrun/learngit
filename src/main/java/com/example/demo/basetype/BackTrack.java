package com.example.demo.basetype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    // 90. Subsets II
    // https://leetcode.com/problems/subsets-ii/description/
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        Arrays.sort(nums);
        deepSubsetsWithDup(nums, 0, result, subList);
        return result;
    }

    public static void deepSubsetsWithDup(int[] nums, int start, List<List<Integer>> result, List<Integer> subList) {
        result.add(new ArrayList<>(subList));
        for (int i = start; i < nums.length; ++i) {
            // 跳过
            if (i != start && nums[i - 1] == nums[i]) {
                continue;
            }
            subList.add(nums[i]);
            deepSubsetsWithDup(nums, i + 1, result, subList);
            subList.remove(subList.size() - 1);
        }
    }

    // 491. Non-decreasing Subsequences
    // https://leetcode.com/problems/non-decreasing-subsequences/description/
    // 非递减子序列 原有元素的顺序不能发生变化
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> subList = new LinkedList<>();
        deepFindSubsequences(nums, 0, result, subList);
        return result;
    }

    public void deepFindSubsequences(int[] nums, int start, List<List<Integer>> result, LinkedList<Integer> subList) {
        if (subList.size() >= 2) {
            result.add(new ArrayList<>(subList));
        }
        Set<Integer> usedSet = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (usedSet.contains(nums[i])
                    || (!subList.isEmpty() && nums[i] < subList.getLast())) {
                continue;
            }
            subList.add(nums[i]);
            usedSet.add(nums[i]);
            deepFindSubsequences(nums, i + 1, result, subList);
            subList.remove(subList.size() - 1);
        }
    }

    // 46. Permutations 全排列
    // https://leetcode.com/problems/permutations/description/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        Set<Integer> usedSet = new HashSet<>();
        deepPermute(nums, result, subList, usedSet);
        return result;
    }

    public void deepPermute(int[] nums, List<List<Integer>> result, List<Integer> subList, Set<Integer> usedSet) {
        if (subList.size() == nums.length) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (usedSet.contains(nums[i])) {
                continue;
            }
            subList.add(nums[i]);
            usedSet.add(nums[i]);
            deepPermute(nums, result, subList, usedSet);
            subList.remove(subList.size() - 1);
            usedSet.remove(nums[i]);
        }
    }

    // 47. Permutations II 全排列 存在重复的元素
    // https://leetcode.com/problems/permutations-ii/description/
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        boolean[] usedArr = new boolean[nums.length];
        Arrays.sort(nums);
        deepPermuteUnique(nums, result, subList, usedArr);
        return result;
    }

    // 如果要对树层中前一位去重，就用used[i - 1] == false，如果要对树枝前一位去重用used[i - 1] == true。
    // 这一段有点猪脑过载了 需要后面反过来重新做一下
    // 只对树层前一位做去重 再之前的就不用管了
    public void deepPermuteUnique(int[] nums, List<List<Integer>> result, List<Integer> subList, boolean[] usedArr) {
        if (subList.size() == nums.length) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (usedArr[i]) {
                continue;
            }
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && !usedArr[i - 1]) {
                continue;
            }

            subList.add(nums[i]);
            usedArr[i] = true;
            deepPermuteUnique(nums, result, subList, usedArr);
            subList.remove(subList.size() - 1);
            usedArr[i] = false;
        }
    }

    // 77. Combinations
    // https://leetcode.com/problems/combinations/
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        deepCombine(n, k, 1, result, subList);
        return result;
    }

    public void deepCombine(int n, int k, int start, List<List<Integer>> result, List<Integer> subList) {
        if (subList.size() == k) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = start; i <= n; ++i) {
            subList.add(i);
            deepCombine(n, k, i + 1, result, subList);
            subList.remove(subList.size() - 1);
        }
    }

    // 216. Combination Sum III
    // https://leetcode.com/problems/combination-sum-iii/description/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
//        boolean[] used = new boolean[10];
        deepCombinationSum3(k, n, 0, 1, result, subList);
        return result;
    }

    public void deepCombinationSum3(int k, int n, int sum, int start, List<List<Integer>> result, List<Integer> subList) {
        if (subList.size() > k) {
            return;
        }
        if (sum == n && subList.size() == k) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = start; i < 10; ++i) {
            int tempSum = sum + i;
            if (tempSum > n) {
                return;
            }
            subList.add(i);
            deepCombinationSum3(k, n, tempSum, i + 1, result, subList);
            subList.remove(subList.size() - 1);
        }
    }

    // 17 Letter Combinations of a Phone Number
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if ("".equals(digits)) {
            return result;
        }
        Map<Character, List<String>> initMap = new HashMap<>();
        initMap.put('0', new ArrayList<>());
        initMap.put('1', new ArrayList<>());
        initMap.put('2', Arrays.asList("a", "b", "c"));
        initMap.put('3', Arrays.asList("d", "e", "f"));
        initMap.put('4', Arrays.asList("g", "h", "i"));
        initMap.put('5', Arrays.asList("j", "k", "l"));
        initMap.put('6', Arrays.asList("m", "n", "o"));
        initMap.put('7', Arrays.asList("p", "q", "r", "s"));
        initMap.put('8', Arrays.asList("t", "u", "v"));
        initMap.put('9', Arrays.asList("w", "x", "y", "z"));
        List<List<String>> inputList = new ArrayList<>();
        for (int i = 0; i < digits.length(); ++i) {
            Character now = digits.charAt(i);
            inputList.add(initMap.get(now));
        }
        deepLetterCombinations(0, inputList, result, "");
        return result;
    }

    public void deepLetterCombinations(int level, List<List<String>> inputList, List<String> result, String tempStr) {
        if (tempStr.length() == inputList.size()) {
            result.add(new String(tempStr));
            return;
        }
        // 本层可以遍历 但是优先走深度 走递归的下一层
        for (int i = 0; i < inputList.get(level).size(); ++i) {
            tempStr = tempStr + inputList.get(level).get(i);
            deepLetterCombinations(level + 1, inputList, result, tempStr);
            tempStr = tempStr.substring(0, tempStr.length() - 1);
        }
    }

    // 39. Combination Sum
    // https://leetcode.com/problems/combination-sum/description/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        Arrays.sort(candidates);
        deepCombinationSum(candidates, target, 0, 0, result, subList);
        return result;
    }

    public void deepCombinationSum(int[] candidates, int target, int sum, int start, List<List<Integer>> result, List<Integer> subList) {
        if (sum == target) {
            result.add(new ArrayList<>(subList));
            return;
        }
        // 组合就要每次从start开始
        for (int i = start; i < candidates.length; ++i) {
            int tempSum = sum + candidates[i];
            if (tempSum > target) {
                return;
            }
            subList.add(candidates[i]);
            // 注意这个地方没有start + 1, 因为可以重复使用自己
            deepCombinationSum(candidates, target, tempSum, start, result, subList);
            subList.remove(subList.size() - 1);
        }
    }
    // 40. Combination Sum II
    // 数组中有重复元素 就需要用boolean[] used 来算去重
    // 组合就从start开始 排列就从0开始
    // https://leetcode.com/problems/combination-sum-ii/description/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        deepCombinationSum2(candidates, target, 0, 0, result, subList, used);
        return result;
    }

    public void deepCombinationSum2(int[] candidates, int target, int sum, int start, List<List<Integer>> result, List<Integer> subList, boolean[] used) {
        if (sum == target) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = start; i< candidates.length; ++i) {
            if (used[i]) {
                continue;
            }
            // 这个地方 是判断前面一个树枝（也就是同层的） 有没有用过 走过的话就是used == false 因为是递归的 前面用过就会置成false
            // 这里有个前提条件 当前这个数和前一个数相同
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            int tempSum = sum + candidates[i];
            if (tempSum > target) {
                return;
            }
            subList.add(candidates[i]);
            used[i] = true;
            deepCombinationSum2(candidates, target, tempSum, i + 1, result, subList, used);
            subList.remove(subList.size() - 1);
            used[i] = false;
        }
    }

    // 131. Palindrome Partitioning
    // https://leetcode.com/problems/palindrome-partitioning/description/
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> subList = new ArrayList<>();
        deepPartition(0, s, result, subList);
        return result;
    }

    public void deepPartition(int start, String s, List<List<String>> result, List<String> subList) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = start; i < s.length(); ++i) {
            String splitStr = s.substring(start, i + 1);
            if (isPalindrome(splitStr)) {
                subList.add(splitStr);
            } else {
                continue;
            }
            deepPartition(i + 1, s, result, subList);
            subList.remove(subList.size() - 1);
        }
    }

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    // 93. Restore IP Addresses
    // https://leetcode.com/problems/restore-ip-addresses/description/
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        List<String> subList = new ArrayList<>();
        deepRestoreIpAddresses(0, 0, s, result, subList);
        return result;
    }

    public void deepRestoreIpAddresses(int start, int count, String s, List<String> result, List<String> subList) {
        if (count == 3) {
            String splitStr = s.substring(start);
            if (isValidIpItem(splitStr)) {
                subList.add(splitStr);
                result.add(subList.stream().map(String::valueOf).collect(Collectors.joining(".")));
                subList.remove(subList.size() - 1);
            }
            return;
        }
        for (int i = start; i < 4; ++i) {
            String splitStr = s.substring(start, i + 1);
            if (isValidIpItem(splitStr)) {
                subList.add(splitStr);
            } else {
                continue;
            }
            deepRestoreIpAddresses(start + 1, count + 1, s, result, subList);
            subList.remove(subList.size() - 1);
        }
    }

    public boolean isValidIpItem(String s) {
        Long item = Long.valueOf(s);
        if (item >= 0 && item <= 255) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2};
//        System.out.println(subsetsWithDup(nums));
        BackTrack track = new BackTrack();
//        System.out.println(track.partition("aab"));
        System.out.println(track.restoreIpAddresses("0000"));
    }


}
