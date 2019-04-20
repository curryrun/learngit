package com.example.demo.leetcode;

import com.example.demo.base.ListNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangdongrun
 * @date 2019/4/13 下午4:31
 */
public class Gaoshi {

    public static void main(String[] args) {

    }

    // leetcode 第二题
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int pre = 0;
        ListNode header = new ListNode(0);
        ListNode preNode = header;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + pre;
            pre = sum / 10;
            ListNode now = new ListNode(sum % 10);
            preNode.next = now;
            preNode = now;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + pre;
            pre = sum / 10;
            ListNode now = new ListNode(sum % 10);
            preNode.next = now;
            preNode = now;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + pre;
            pre = sum / 10;
            ListNode now = new ListNode(sum % 10);
            preNode.next = now;
            preNode = now;
            l2 = l2.next;
        }
        if (pre > 0) {
            ListNode now = new ListNode(pre % 10);
            preNode.next = now;
        }
        return header.next;
    }

    // leetcode 445 是第二题的加强版 反向链表
    // 想法就是先遍历入栈 要不就反转链表
    public ListNode addTwoNumbers_jiaqiangban(ListNode l1, ListNode l2) {
        ListNode ll1, ll2, pre = l1, now = l1.next;
        while (now != null){
            ListNode next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        ll1 = pre;
        l1.next = null;
        pre = l2;
        now = l2.next;
        while (now != null){
            ListNode next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        ll2 = pre;
        l2.next = null;
        return addTwoNumbers(ll1, ll2);
    }

    // leetcode 第三题
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, res = 0;
        Set<Character> set = new HashSet<Character>();
        while (left < s.length() && right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                res = Math.max(right - left, res);
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return res;
    }

    // leetcode 19
    // 太秀了8，搞两个指针，距离为n，这样前面的指针到头了，就找到了倒数第n个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 创建一个自己的header，为了防止要删除头结点这种情况
        ListNode myHeader = new ListNode(0);
        myHeader.next = head;
        ListNode pre = myHeader, after = myHeader;
        // 让pre多走一步
        for (int i = 0; i < n + 1; ++i) {
            pre = pre.next;
        }
        while (pre != null) {
            pre = pre.next;
            after = after.next;
        }
        after.next = after.next.next;
        return myHeader.next;
    }

}
