package com.example.demo.basetype;

import java.util.LinkedList;
import java.util.List;

/**
 * @Classname SingleStack
 * @Description
 * @Date 2024/3/10 16:52
 * @Creater zhangdongrun_dxm
 * Monotonic 单调的
 * 单调栈
 **/
public class MonotonicStack {

    public void printArr(int[] arr) {
        // 打印dp数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println("\n");
    }


    // 739. Daily Temperatures
    // https://leetcode.com/problems/daily-temperatures/description/
    public int[] dailyTemperatures(int[] temperatures) {
        // 一个单调递减的栈
        // 这里需要一个递减的栈 也就是从栈底开始从下往上都是小的
        // 这样在pop的时候可以做操作
        // 栈里要加入一个元素i的时候，才知道栈顶元素在数组中右面第一个比栈顶元素大的元素是i。
        LinkedList<Integer> stack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; ++i) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                int peek = stack.peek();
                int peekValue = temperatures[peek];
                // 一直出栈 直到满足条件
                while (!stack.isEmpty() && temperatures[i] > peekValue) {
                    int pop = stack.pop();
                    res[pop] = i - pop;
                    if (!stack.isEmpty()) {
                        peek = stack.peek();
                        peekValue = temperatures[peek];
                    }
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            res[pop] = 0;
        }
//        printArr(res);
        return res;
    }

    public static void main(String[] args) {
        MonotonicStack monotonicStack = new MonotonicStack();
        System.out.println(monotonicStack.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}));
    }

}
