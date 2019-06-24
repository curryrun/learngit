package com.example.demo.base;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author zhangdongrun
 * @date 2019/1/7 下午6:26
 */
public class LeetCode {

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("au"));
//        System.out.println(bigSum("56789", "567"));
        int[] arr1 = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//        System.out.println(maxAreaOn(arr1));

        int points[][] = new int[][]{{1, 3}, {-2, 2}};
//        kClosest(points, 1);
        int[] arr2 = new int[]{2, 1, 2};
//        System.out.println(largestPerimeter(arr2));
        int[] arr3 = new int[]{4, 5, 0, -2, -3, 1};
//        subarraysDivByK(arr3, 5);
//        subarraysDivByKMy(arr3, 5);
        int[] arr4 = new int[]{10, 13, 12, 14, 15};
//        oddEvenJumps(arr4);
        generateTrees(3);
//        System.out.println(quickFindKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
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

    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                int minHeight = Math.min(height[i], height[j]);
                int area = minHeight * (j - i);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    // 优化之后的方法
    public static int maxAreaOn(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int temp = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(temp, max);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static int[][] kClosest(int[][] points, int K) {
        Map<Integer, int[][]> treeMap = new TreeMap<>();
        for (int i = 0; i < points.length; ++i) {
            int temp = sqrt(points[i][0], points[i][1]);
            int[][] tempArr = new int[][]{points[i]};
            treeMap.put(temp, tempArr);
        }
        int[][] res = new int[K][2];
        int i = 0;
        for (Map.Entry<Integer, int[][]> entry : treeMap.entrySet()) {
            if (i >= K) {
                break;
            }
            res[i][0] = entry.getValue()[0][0];
            res[i][1] = entry.getValue()[0][1];
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            i++;
        }
        return res;
    }

    public static int sqrt(int x, int y) {
        return x * x + y * y;
    }

    public static int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int max = 0;
        for (int x = A.length - 1; x >= 0; --x) {
            for (int y = x - 1; y >= 0; --y) {
                for (int z = y - 1; z >= 0; --z) {
                    int temp = A[x] + A[y] + A[z];
                    if (hasArea(A[x], A[y], A[z])) {
                        max = Math.max(max, temp);
                        return max;
                    }
                }
            }
        }
        return max;
    }

    public static boolean hasArea(int x, int y, int z) {
        if (x + y <= z || x + z <= y || y + z <= x) {
            return false;
        }
        return true;
    }

    public static int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);
        int prefix = 0, res = 0;
        for (int a : A) {
            prefix = (prefix + a % K + K) % K;
            int temp = count.getOrDefault(prefix, 0);
            res = res + temp;
            count.put(prefix, temp + 1);
        }
        return res;
    }

    // 超时了```
    public static int subarraysDivByKMy(int[] A, int K) {
        int count = 0;
        for (int i = 0; i < A.length; ++i) {
            int sum = A[i];
            if (sum % K == 0) {
                count++;
            }
            for (int j = i + 1; j < A.length; ++j) {
                sum = sum + A[j];
                if (sum % K == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int oddEvenJumps(int[] A) {
        int count = 0;
        // i是起始位
        for (int i = 0; i < A.length; ++i) {
            int jumpCount = 1;
            int now = i;
            while (now < A.length) {
                if (now == A.length - 1) {
                    count++;
                    break;
                }
                // 奇数跳
                if (jumpCount % 2 != 0) {
                    int temp = findJiPositionJump(A, now);
                    if (temp < 0) {
                        break;
                    }
                    now = temp;
                }
                // 偶数跳
                else {
                    int temp = findOuPositionJump(A, now);
                    if (temp < 0) {
                        break;
                    }
                    now = temp;
                }
                jumpCount++;
            }
        }
        return count;
    }

    public static int findJiPositionJump(int[] arr, int now) {
        int pre = 900000;
        int pos = -1;
        for (int i = now + 1; i < arr.length; ++i) {
            if (arr[now] <= arr[i] && arr[i] < pre) {
                pos = i;
                pre = arr[i];
            }
        }
        return pos;
    }

    public static int findOuPositionJump(int[] arr, int now) {
        int pre = -1;
        int pos = -1;
        for (int i = now + 1; i < arr.length; ++i) {
            if (arr[now] >= arr[i] && arr[i] > pre) {
                pos = i;
                pre = arr[i];
            }
        }
        return pos;
    }

    // 看不明白啊````
    public static LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        List<TreeNode> res = generate_trees(1, n);
        return res;
    }

    public static void main1(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int[] aiList = new int[n];
        for (int i = 0; i < n; i++) {
            aiList[i] = cin.nextInt();
        }
        int prof = 0;//利润
        int count = 0;//交易的次数
        int minPrice = 0;//
        for (int i = 0; i < n; i++) {
            // 找到第一个买入的低价
            while (i < n - 1 && aiList[i + 1] <= aiList[i])
                i += 1;
            minPrice = aiList[i];

            //从最低价的后一个元素起，开始寻找第一个卖出的高价
            i += 1;
            while (i < n - 1 && aiList[i + 1] >= aiList[i])
                i += 1;
            /*
             * 情况1：但最低价为最后一个元素n-1，if语句不执行
			 * 情况2：当最低价为倒数第二个的元素时,那么最高价必定为最后一个.
			 * */
            if (i < n) {
                // 找到的高价与找到的低价做差即可
                prof += aiList[i] - minPrice;
                // 交易的次数为偶数，买和卖，土豪不会将神秘石留在手上的
                count += 2;
            }
        }
        System.out.println(prof + " " + count);
        cin.close();
    }

    // Best Time to Buy and Sell Stock
    // 给定一个价格序列prices，其中prices[i]代表第i天商品的价格，商家需要在某一天买入，然后在之后的某一天出售，计算可以获得的最大利润
    public int maxProfit(int[] prices) {
        if (0 == prices.length) {
            return 0;
        }
        int min = prices[0];
        int maxPrice = 0;
        for (int i = 1; i < prices.length; ++i) {
            maxPrice = Math.max(maxPrice, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return maxPrice;
    }

    // 这回变成可以买卖多次 计算可以获得的最大利润
    public int maxProfitV2(int[] prices) {
        if (0 == prices.length) {
            return 0;
        }
        int maxPrice = 0;
        for (int i = 1; i < prices.length; ++i) {
            // 计算每次的前后差值 如果大于0 则累加
            maxPrice = maxPrice + Math.max(prices[i] - prices[i - 1], 0);
        }
        return maxPrice;
    }

    // dp[i][j] = Math.max (dp[i][j - 1], dp[i-1][m] + (price[j] - price[m]) ） 其中 m = 0 , 1, 2, ``` ,j -1
    // 前一天已经做了i次交易， 前一次交易的最大值 + （今天股票价格 - m天买入的价格）
    // 这回变成最多可以完成K次交易 计算可以获得的最大利润
    public int maxProfitV3(int[] prices, int k) {
        if (null == prices || 0 == prices.length) {
            return 0;
        }
        if (k > prices.length / 2) return maxProfitV2(prices);
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i < k + 1; ++i) {
            for (int j = 1; j < prices.length; ++j) {
                int tempMax = 0;
                for (int m = 0; m < j; ++m) {
                    tempMax = Math.max(tempMax, dp[i - 1][m] + prices[j] - prices[m]);
                }
                dp[i][j] = Math.max(dp[i][j - 1], tempMax);
            }
        }
        return dp[k][prices.length - 1];
    }

    // 优化
    public int maxProfitV3_youhua(int[] prices, int k) {
        if (null == prices || 0 == prices.length) {
            return 0;
        }
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i < k + 1; ++i) {
            int max = dp[i][0] - prices[i];
            for (int j = 1; j < prices.length; ++j) {
                dp[i][j] = Math.max(dp[i][j - 1], max + prices[j]);
                max = Math.max(dp[i][j] - prices[j], max);
            }
        }
        return dp[k][prices.length - 1];
    }

}
