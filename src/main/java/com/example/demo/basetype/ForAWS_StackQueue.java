package com.example.demo.basetype;

import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Classname ForAWS_StackQueue
 * @Description
 * @Date 2023/12/26 14:29
 * @Creater zhangdongrun_dxm
 * https://chienmy.gitbook.io/algorithm-pattern-java/shu-ju-jie-gou/stack_queue
 **/
public class ForAWS_StackQueue {

    // 150. Evaluate Reverse Polish Notation 逆波兰表达式求值
    // https://leetcode.com/problems/evaluate-reverse-polish-notation/
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i< tokens.length; ++i) {
            String nowStr = tokens[i];
            if ("+".equals(nowStr)) {
                int res = stack.pop() + stack.pop();
                stack.push(res);
            } else if ("-".equals(nowStr)) {
                int after = stack.pop();
                int pre = stack.pop();
                stack.push(pre - after);
            } else if ("*".equals(nowStr)) {
                int res = stack.pop() * stack.pop();
                stack.push(res);
            } else if ("/".equals(nowStr)) {
                int after = stack.pop();
                int pre = stack.pop();
                stack.push(pre / after);
            } else {
                stack.push(Integer.valueOf(nowStr));
            }
        }
        return stack.pop();
    }

    // 20. Valid Parentheses
    // https://leetcode.com/problems/valid-parentheses/
    public static boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i< s.length(); ++i) {
            Character nowStr = s.charAt(i);
            if ('(' == nowStr || '[' == nowStr || '{' == nowStr) {
                stack.push(nowStr);
            } else {
                Character peek = stack.peek();
                if (null == peek) {
                    return false;
                }
                if (('(' == peek && ')' == nowStr)
                        || ('[' == peek && ']' == nowStr)
                        || ('{' == peek && '}' == nowStr)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    // 84. Largest Rectangle in Histogram
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    // 这道题相当有意思
    // 为了找当前位置左右比他小的截止，然后高度乘上左右限界，就是最大值
    public static int largestRectangleArea(int[] heights) {
        int max = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= heights.length; ++i) {
            int now = 0;
            // 要再多跑一次，用0把栈内全部数据出栈
            if (i != heights.length) {
                now = heights[i];
            }
            // 开始出栈流程
            while (!stack.isEmpty() && heights[stack.peek()] > now) {
                int peekI = stack.pop();
                int peekValue = heights[peekI];
                // 默认宽度就是pop时的i 栈为空的时候 自己就是最小的
                int width = i;
                if (!stack.isEmpty()) {
                    int newPeek = stack.peek();
                    width = i - newPeek - 1;
                }
                int res = width * peekValue;
                max = Math.max(max, res);
            }
            stack.push(i);
        }
        return max;
    }

    // 42. Trapping Rain Water
    // https://leetcode.com/problems/trapping-rain-water/
    // Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    // Output: 6
    public static int trap(int[] height) {
        int nowI = 0, sum = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        while (nowI < height.length) {
            int nowValue = height[nowI];
            while (!stack.isEmpty() && nowValue > height[stack.peek()]) {
                int low = stack.pop();
                int lowHeight = height[low];
                // 没有左边的墙了 直接结束
                if (stack.isEmpty()) {
                    break;
                }
                int newPeek = stack.peek();
                // 左侧墙壁高度
                int leftHeight = height[newPeek];
                // 根据左右墙壁的最小值确认能装多少
                int minHeight = Math.min(nowValue, leftHeight);
                // 宽度
                int width = nowI - newPeek - 1;
                int itemSum = width * (minHeight - lowHeight);
                sum = sum + itemSum;
            }
            stack.push(nowI);
            ++nowI;
        }
        return sum;
    }

    // 402 Remove K Digits
    // https://leetcode.com/problems/remove-k-digits/
    // 遍历 如果左边比当前的大 就抛掉
    public static String removeKdigits(String num, int k) {
        if (null == num || "".equals(num)) {
            return num;
        }
        int popCount = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(Integer.valueOf(String.valueOf(num.charAt(0))));
        for (int i = 1; i< num.length(); ++i) {
            int now = Integer.valueOf(String.valueOf(num.charAt(i)));
            if (popCount >= k) {
                stack.push(now);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > now) {
                stack.pop();
                popCount++;
                if (popCount >= k) {
                    break;
                }
            }
            stack.push(now);
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        res = res.substring(0, num.length() - k);
        res = res.replaceAll("^0*", "");
        if ("".equals(res)) {
            return "0";
        }
        return res;
    }

    public static void main(String[] args) {
//        isValid("()");
//        largestRectangleArea(new int[] {2,1,5,6,2,3});
//        System.out.println(trap(new int[] {4,2,0,3,2,5}));
        System.out.println(removeKdigits("1234567890", 9));
    }

}
