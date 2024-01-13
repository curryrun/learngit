package com.example.demo.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ForAWS_Window
 * @Description
 * @Date 2024/1/8 23:11
 * @Creater zhangdongrun_dxm
 * https://chienmy.gitbook.io/algorithm-pattern-java/ji-chu-suan-fa/slide_window
 * 滑动窗口
 **/
public class ForAWS_Window {

    // 76. Minimum Window Substring
    // https://leetcode.com/problems/minimum-window-substring/
    public String minWindow(String s, String t) {
        Map<Character, Integer> needMap = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            Character temp = t.charAt(i);
            needMap.put(temp, needMap.getOrDefault(temp, 0) + 1);
        }
        Map<Character, Integer> windowMap = new HashMap<>();
        // 窗口的左右
        int left = 0, right = 0;
        // 现在窗口内分字符串下匹配的数量
        int match = 0;
        // 记录下最小窗口 最终返回使用
        int start = 0, minLen = Integer.MAX_VALUE;
        // 分成两步 第一步右移 即拓展窗口
        while (right < s.length()) {
            Character now = s.charAt(right);
            ++right;
            // target里有这个字符
            if (needMap.containsKey(now)) {
                Integer numInWindow = windowMap.getOrDefault(now, 0);
                windowMap.put(now, numInWindow + 1);
                // 窗口内match的字符数量加1
                if (windowMap.get(now).equals(needMap.get(now))) {
                    match++;
                }
            }
            // 满足条件了 则开始缩小窗口
            while (needMap.size() == match) {
                // 每次check下 获取最小的窗口
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                Character delete = s.charAt(left);
                ++left;
                if (windowMap.containsKey(delete)) {
                    Integer numInWindow = windowMap.get(delete);
                    // target里有个这个字符 才做match扣减
                    if (numInWindow.equals(needMap.get(delete))) {
                        --match;
                    }
                    windowMap.put(delete, numInWindow - 1);
                }
            }
        }
        if (Integer.MAX_VALUE == minLen) {
            return "";
        }
        return s.substring(start, start + minLen);
    }

    public static String minWindowOfMine(String s, String t) {
        Map<Character, Integer> needMap = new HashMap<>();
        for (int i = 0; i < t.length(); ++i) {
            Character temp = t.charAt(i);
            needMap.put(temp, needMap.getOrDefault(temp, 0) + 1);
        }
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0, right = 0;
        int match = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            Character now = s.charAt(right);
            // 放入窗口 窗口右扩过程
            if (needMap.containsKey(now)) {
                // 这里 windowMap没有全部放入滑动窗口内的字符 只放入了需要的
                windowMap.put(now, windowMap.getOrDefault(now, 0) + 1);
                if (needMap.get(now).equals(windowMap.get(now))) {
                    match++;
                }
            }
            ++right;
            // 窗口 左缩过程
            while (needMap.size() == match) {
                // 记录下结果
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                Character delete = s.charAt(left);
                ++left;
                if (windowMap.containsKey(delete)) {
                    // 这里要注意 必须判断下delete这个元素是不是和need里的num一致 一致的时候才删除 否则会多删除掉match数 就不对了
                    Integer deleteNum = windowMap.get(delete);
                    if (needMap.get(delete).equals(deleteNum)) {
                        --match;
                    }
                    windowMap.put(delete, windowMap.get(delete) - 1);
                }
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + minLen);
    }

    // 567. Permutation in String
    // https://leetcode.com/problems/permutation-in-string/description/
    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> needMap = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            Character now = s1.charAt(i);
            needMap.put(now, needMap.getOrDefault(now, 0) + 1);
        }
        int left = 0, right = 0;
        int match = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        while (right < s2.length()) {
            Character now = s2.charAt(right);
            if (needMap.containsKey(now)) {
                windowMap.put(now, windowMap.getOrDefault(now, 0) + 1);
                if (windowMap.get(now).equals(needMap.get(now))) {
                    match++;
                }
            }
            ++right;
            // 判断全部 distinct 的字符出现次数 = match的时候
            while (needMap.size() == match) {
                // right在上面已经加+1了 所以这里不用加1 其实这里的right已经是下一次窗口扩展循环的时候 right的位置
                if (right - left == s1.length()) {
                    return true;
                }
                Character delete = s2.charAt(left);
                if (needMap.containsKey(delete)) {
                    // 扣减之前就要要重新比对下 是不是match
                    if (windowMap.get(delete).equals(needMap.get(delete))) {
                        match--;
                    }
                    windowMap.put(delete, windowMap.get(delete) - 1);
                }
                ++left;
            }
        }
        return false;
    }

    // 438. Find All Anagrams in a String
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resList = new ArrayList<>();
        Map<Character, Integer> needMap = new HashMap<>();
        for (int i = 0; i< p.length(); ++i) {
            Character now = p.charAt(i);
            needMap.put(now, needMap.getOrDefault(now, 0) + 1);
        }
        int left = 0, right = 0;
        int match = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        while (right < s.length()) {
            Character now = s.charAt(right);
            if (needMap.containsKey(now)) {
                windowMap.put(now, windowMap.getOrDefault(now, 0) + 1);
                if (windowMap.get(now).equals(needMap.get(now))) {
                    ++match;
                }
            }
            ++right;
            while (match == needMap.size() && left < s.length()) {
                if (right - left == p.length()) {
                    resList.add(left);
                }
                Character delete = p.charAt(left);
                if (windowMap.containsKey(delete)) {
                    if (windowMap.get(delete).equals(needMap.get(delete))) {
                        --match;
                    }
                    windowMap.put(delete, windowMap.get(delete) - 1);
                }
                ++left;
            }
        }
        return resList;
    }

    // 3. Longest Substring Without Repeating Characters
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        while (right < s.length()) {
            Character now = s.charAt(right);
            windowMap.put(now, windowMap.getOrDefault(now, 0) + 1);
            // 当前唯一
            if (1 == windowMap.get(now)) {
                if (right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                }
            }
            // 当前不唯一 开始收缩
            else {
                // 当前重复的字符肯定是 now
                while (windowMap.get(now) > 1) {
                    Character delete = s.charAt(left);
                    windowMap.put(delete, windowMap.get(delete) - 1);
                    ++left;
                }
            }
            ++right;
        }
        return maxLen;
    }

    public static void main(String[] args) {
//        System.out.println(minWindowOfMine("ADOBECODEBANC", "ABC"));
        System.out.println(checkInclusion("abcdxabcde", "abcdeabcdx"));
    }
















}
