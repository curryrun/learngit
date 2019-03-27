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
        int[] arr4 = new int[]{10,13,12,14,15};
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
        for(int i = 0; i< A.length; ++i){
            int jumpCount = 1;
            int now = i;
            while (now < A.length){
                if(now == A.length - 1){
                    count++;
                    break;
                }
                // 奇数跳
                if(jumpCount % 2 != 0){
                    int temp = findJiPositionJump(A, now);
                    if(temp < 0){
                        break;
                    }
                    now = temp;
                }
                // 偶数跳
                else {
                    int temp = findOuPositionJump(A, now);
                    if(temp < 0){
                        break;
                    }
                    now = temp;
                }
                jumpCount++;
            }
        }
        return count;
    }

    public static int findJiPositionJump(int[] arr, int now){
        int pre = 900000;
        int pos = -1;
        for(int i = now + 1; i< arr.length; ++i){
            if(arr[now] <= arr[i] && arr[i] < pre){
                pos = i;
                pre = arr[i];
            }
        }
        return pos;
    }

    public static int findOuPositionJump(int[] arr, int now){
        int pre = -1;
        int pos = -1;
        for(int i = now + 1; i< arr.length; ++i){
            if(arr[now] >= arr[i] && arr[i] > pre){
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


}
