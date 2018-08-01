package com.example.demo.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangdongrun
 * @date 2018/7/30 上午10:58
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            hashMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement) && hashMap.get(complement) != i) {
                return new int[]{i, hashMap.get(complement)};
            }
        }
        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long count = 1;
        long l1Val = 0;
        while (null != l1) {
            l1Val += l1.val * count;
            count = count * 10;
            l1 = l1.next;
        }

        count = 1;
        long l2Val = 0;
        while (null != l2) {
            l2Val += l2.val * count;
            count = count * 10;
            l2 = l2.next;
        }

        long sum = l1Val + l2Val;
        long test = sum % 10;
        ListNode start = new ListNode((int) test);
        ListNode temp = start;
        sum = sum / 10;
        while (sum > 0) {
            long test2 = sum % 10;
            ListNode node = new ListNode((int) test2);
            sum = sum / 10;
            temp.next = node;
            temp = node;
        }
        return start;
    }

    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int i = 0, j = 0, res = 0;
        HashSet<Character> hashSet = new HashSet<>();
        while (i < length && j < length) {
            if (!hashSet.contains(s.charAt(j))) {
                hashSet.add(s.charAt(j));
                res = Math.max(res, j - i + 1);
                j++;
            } else {
                hashSet.remove(s.charAt(i));
                i++;
            }
        }
        return res;
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int count = 1;
        int i = 0, j = 0;
        int left = 0, right = 0;
        if (m != 0 && n != 0) {
            left = nums1[0] > nums2[0] ? nums2[0] : nums1[0];
            right = nums1[0] < nums2[0] ? nums2[0] : nums1[0];
            if (nums1[m - 1] < nums2[0]) {
                left = nums1[m - 1];
                right = nums2[0];
            } else if (nums2[n - 1] < nums1[0]) {
                left = nums2[n - 1];
                right = nums1[0];
            }
        } else if (m == 0 && n > 1) {
            left = nums2[0];
            right = nums2[1];
            j = 1;
        } else if (n == 0 && m > 1) {
            left = nums1[0];
            right = nums1[1];
            i = 1;
        } else if (n == 0 && m == 1) {
            return nums1[0];
        } else {
            return nums2[0];
        }

        while (count < (m + n + 1) / 2) {
            left = right;
            count++;
            if (i + 1 >= m) {
                j++;
                right = nums2[j];
            } else if (j + 1 >= n) {
                i++;
                right = nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
                right = nums1[i];
            } else {
                j++;
                right = nums2[j];
            }
        }
        if ((m + n) % 2 == 0) {
            return (left + right) / 2.0;
        } else {
            return (double) left;
        }
    }

    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = iMin + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = iMax - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static double findMedianSortedArrays2(int[] num1, int[] num2) {
        int m = num1.length;
        int n = num2.length;
        int halfLength = (m + n) / 2;
        int i = 0, j = 0, count = 0;
        int[] temp = new int[halfLength + 1];
        if (m == 0) {
            temp = num2;
        } else if (n == 0) {
            temp = num1;
        } else {
            while (count <= halfLength) {
                if (i < m && j < n) {
                    if (num1[i] < num2[j]) {
                        temp[count] = num1[i];
                        i++;
                    } else {
                        temp[count] = num2[j];
                        j++;
                    }
                } else if (i < m && j == n) {
                    temp[count] = num1[i];
                    i++;
                } else if (j < n && i == m) {
                    temp[count] = num2[j];
                    j++;
                } else {
                    break;
                }
                count++;
            }
        }
        if ((m + n) % 2 == 0) {
            return (temp[halfLength] + temp[halfLength - 1]) / 2.0;
        } else {
            return temp[halfLength];
        }
    }

    public static void main(String[] args) {
        int nums[] = new int[]{2, 7, 11, 15};
        System.out.println(JSON.toJSONString(twoSum(nums, 9)));
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(9);
        ListNode l9 = new ListNode(9);
        ListNode l10 = new ListNode(9);
        ListNode l11 = new ListNode(9);
        ListNode l5 = new ListNode(1);
        l5.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;
        l10.next = l11;
        addTwoNumbers(l1, l5);

        lengthOfLongestSubstring("pwwkew");
        System.out.println(findMedianSortedArrays2(new int[]{1}, new int[]{1}));
    }
}
