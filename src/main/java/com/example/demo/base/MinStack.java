package com.example.demo.base;

import java.util.LinkedList;

/**
 * @author zhangdongrun
 * @date 2019/4/25 上午11:54
 */
// 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数
// 使用一个最小栈来记录最小值的变化过程
public class MinStack {

    private LinkedList<Integer> stack;

    private int min = Integer.MAX_VALUE;

    private LinkedList<Integer> minStack;

    public void push(int node) {
        if (null == stack) {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }
        if (node < min) {
            min = node;
        }
        minStack.push(min);
        stack.push(node);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
        int popMin = minStack.pop();
        min = minStack.peek();
    }

    public int top() {
        if (null == stack) {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }
        return stack.peek();
    }

    public int min() {
        return min;
    }


}
