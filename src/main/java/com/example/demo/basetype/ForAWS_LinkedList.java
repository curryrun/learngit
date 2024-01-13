package com.example.demo.basetype;

import com.example.demo.base.ListNode;

/**
 * @Classname ForAWS
 * @Description
 * @Date 2023/12/21 16:20
 * @Creater zhangdongrun_dxm
 * https://github.com/chienmy/algorithm-pattern-java/blob/master/data_structure/linked_list.md
 * linked list
 **/
public class ForAWS_LinkedList {

    // 删除排序的链表中重复的元素 重复元素只保留一个
    // https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode now = head;
        while (null != now) {
            // 这次直接删完
            while (null != now.next && now.val == now.next.val) {
                now.next = now.next.next;
            }
            now = now.next;
        }
        return head;
    }

    // 删除排序的链表中重复的元素 重复元素不保留
    // https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode now = head;
        Integer lastDeletedVal = null;
        while (null != now) {
            // same as last delete
            if (null != lastDeletedVal && lastDeletedVal == now.val) {
                pre.next = now.next;
                now = now.next;
                continue;
            }
            // new loop
            if (null != now.next && now.val == now.next.val) {
                lastDeletedVal = now.val;
                pre.next = now.next;
                now = now.next;
                continue;
            }
            pre = now;
            now = now.next;
        }
        return newHead.next;
    }

    // 反转链表
    // https://leetcode.cn/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode now = head;
        while (null != now) {
            ListNode temp = now.next;
            now.next = pre;
            pre = now;
            now = temp;
        }
        return pre;
    }

    // 92. 反转链表 II
    // https://leetcode.cn/problems/reverse-linked-list-ii/description/
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left >= right) {
            return head;
        }
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        newHead.next = head;
        ListNode now = newHead;
        ListNode pre = null;
        ListNode leftPre = null;
        ListNode leftNode = null;
        ListNode rightNext = null;
        for (int i = 0; null != now && i <= right; ++i) {
            if (i == left - 1) {
                leftPre = now;
                leftNode = now.next;
            }
            if (i == right) {
                rightNext = now.next;
            }
            // start reverse from left to right
            if (i >= left && i <= right) {
                ListNode temp = now.next;
                now.next = pre;
                pre = now;
                now = temp;
            } else {
                now = now.next;
            }
        }
        if (null != leftPre) {
            leftPre.next = pre;
        }
        if (null != leftNode) {
            leftNode.next = rightNext;
        }
        return newHead.next;
    }

    // 876. Middle of the Linked List
    // https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (null != quick && null != quick.next) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    // 143. Reorder List
    // https://leetcode.com/problems/reorder-list/
    // L0 → L1 → … → Ln - 1 → Ln
    //Reorder the list to be on the following form:
    //L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    // 思路 先找链表中点 反转链表后半部分 然后做两个链表合并
    public static void reorderList(ListNode head) {
        // find mid
        ListNode slow = head;
        ListNode quick = head;
        while (null != quick && null != quick.next) {
            slow = slow.next;
            quick = quick.next.next;
        }
        // reverse from mid next
        ListNode pre = null;
        ListNode now = slow.next;
        while (null != now) {
            ListNode temp = now.next;
            now.next = pre;
            pre = now;
            now = temp;
        }
        // split the two linked list
        slow.next = null;
        ListNode p1 = head, p2 = pre;
        while (null != p1 && null != p2) {
            ListNode temp = p1.next;
            p1.next = p2;
            ListNode temp2 = p2.next;
            if (null != temp) {
                p2.next = temp;
            }
            p1 = temp;
            p2 = temp2;
        }
    }

    // 234. Palindrome Linked List 是不是回文 2112 369963, 121也是回文
    // https://leetcode.com/problems/palindrome-linked-list/
    // 思路 先找链表中点 反转链表后半部分 然后做两个链表对比
    // 注意这个：
    // fast如果初始化为head.Next则中点在slow.Next
    // fast初始化为head,则中点在slow
    public static boolean isPalindrome(ListNode head) {
        // find mid
        ListNode slow = head;
        // fast如果初始化为head.Next则中点在slow.Next
        // fast初始化为head,则中点在slow
        ListNode quick = head.next;
        while (null != quick && null != quick.next) {
            slow = slow.next;
            quick = quick.next.next;
        }
        // reverse from mid next
        ListNode pre = null;
        ListNode now = slow.next;
        while (null != now) {
            ListNode temp = now.next;
            now.next = pre;
            pre = now;
            now = temp;
        }
        // split the two linked list
        slow.next = null;
        ListNode p1 = head, p2 = pre;
        while (null != p1 && null != p2) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }


    public static void main(String[] args) {
        ListNode node_1a = new ListNode(1);
        ListNode node_1b = new ListNode(2);
        ListNode node_1c = new ListNode(3);
        ListNode node_2a = new ListNode(4);
//        ListNode node_3a = new ListNode(5);
        node_1a.next=node_1b;
        node_1b.next = node_1c;
        node_1c.next= node_2a;
//        node_2a.next = node_3a;
//        deleteDuplicates(node_1a);

        ListNode node_3 = new ListNode(3);
        ListNode node_5 = new ListNode(5);
        node_3.next = node_5;
//        reverseBetween(node_3, 1, 2);
//        reorderList(node_1a);
        isPalindrome(node_1a);
    }

}
