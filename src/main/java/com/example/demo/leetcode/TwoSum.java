package com.example.demo.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

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
        ListNode start = new ListNode((int)test);
        ListNode temp = start;
        sum = sum / 10;
        while (sum > 0) {
            long test2 = sum % 10;
            ListNode node = new ListNode((int)test2);
            sum = sum / 10;
            temp.next = node;
            temp = node;
        }
        return start;
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
        l5.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l6;
        l6.next=l7;
        l7.next=l8;
        l8.next=l9;
        l9.next=l10;
        l10.next=l11;
        addTwoNumbers(l1, l5);
    }
}
