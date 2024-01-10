package com.example.demo.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ForAWS_Window
 * @Description
 * @Date 2024/1/8 23:11
 * @Creater zhangdongrun_dxm
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

    // 校验是否包含
    public static boolean isHaveAll(Map<Character, Integer> needMap, Map<Character, Integer> windowMap) {
        for (Map.Entry<Character, Integer> item : needMap.entrySet()) {
            Character k = item.getKey();
            Integer v = item.getValue();
            Integer windowValue = windowMap.getOrDefault(k, 0);
            if (!v.equals(windowValue)) {
                return false;
            }
        }
        return true;
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

    public static void main(String[] args) {
        System.out.println(minWindowOfMine("ADOBECODEBANC", "ABC"));
    }
















}
