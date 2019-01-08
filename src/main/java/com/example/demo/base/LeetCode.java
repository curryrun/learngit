package com.example.demo.base;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangdongrun
 * @date 2019/1/7 下午6:26
 */
public class LeetCode {

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(bigSum("56789", "567"));
    }

    // 寻找最长没有重复字符串的子串长度
    // start end 一点点遍历，记录下来当前最大的
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int start = 0;
        int end = 0;
        int max = 0;
        while (end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                ++end;
            } else {
                int temp = end - start;
                if (temp > max) {
                    max = temp;
                }
                ++start;
                end = start;
                set.clear();
            }
        }
        int temp = end - start;
        if (temp > max) {
            max = temp;
        }
        return max;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    // 两个大数相加 转化为数组内部的相加
    public static String bigSum(String big1, String big2) {
        int maxLength = Math.max(big1.length(), big2.length());
        int[] big1Arr = fanzhuan(big1, maxLength);
        int[] big2Arr = fanzhuan(big2, maxLength);
        int[] sumArr = new int[maxLength + 1];
        for (int i = 0; i < maxLength; ++i) {
            int temp = big1Arr[i] + big2Arr[i];
            int high = 0;
            int low = temp;
            if (temp > 10) {
                low = temp - 10;
                high = temp / 10;
            }
            sumArr[i] = low + sumArr[i];
            sumArr[i + 1] = high + sumArr[i + 1];
        }
        StringBuilder sb = new StringBuilder();
        int length = maxLength + 1;
        if (sumArr[length - 1] == 0) {
            length--;
        }
        for (int i = length - 1; i >= 0; --i) {
            sb.append(sumArr[i]);
        }
        return sb.toString();
    }

    public static int[] fanzhuan(String big, int length) {
        int[] bigArr = new int[length];
        int count = big.length() - 1;
        for (int i = 0; i < length; ++i) {
            if (count < 0) {
                bigArr[i] = 0;
            } else {
                bigArr[i] = big.charAt(count) - '0';
            }
            --count;
        }
        return bigArr;
    }

}
