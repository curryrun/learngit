package com.example.demo.offer;

import com.example.demo.base.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author zhangdongrun
 * @date 2019/4/8 下午7:14
 */
public class Question1 {

    public static void main(String[] args) {
        int[][] arrEr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}};
        System.out.println(Find(1, arrEr));
    }

    // 第一题 从右上角开始一个个的比较 如果target小 则目标肯定在左边 如果target大，目标肯定在下面
    public static boolean Find(int target, int[][] array) {
        int hang = 0, lie = array[0].length - 1;
        while (hang < array.length && lie >= 0) {
            if (target == array[hang][lie]) {
                return true;
            } else if (target < array[hang][lie]) {
                lie--;
            } else {
                hang++;
            }
        }
        return false;
    }

    // 第一题 把二维数组变成一个一维数组，然后用二分法查找
    public static boolean FindNew(int target, int[][] array) {
        int hangRight = array.length, lieRight = array[0].length, left = 0, right = hangRight * lieRight - 1, mid = (left + right + 1) / 2;
        while (mid >= left && mid <= right) {
            int hang = mid / lieRight;
            int lie = mid % lieRight;
            if (target == array[hang][lie]) {
                return true;
            } else if (target > array[hang][lie]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            mid = (left + right + 1) / 2;
        }
        return false;
    }

    // 第三题 第二题太傻比了不做了
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();
        while (listNode.next != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;
    }

}
