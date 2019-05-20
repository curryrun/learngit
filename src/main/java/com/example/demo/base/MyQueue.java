package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/5/9 下午1:37
 */
public class MyQueue {

    private int[] arr;

    private int put;

    private int remove;

    public MyQueue(int size) {
        arr = new int[size];
        put = 0;
        remove = 0;
    }

    // 入队
    public void add(int a) {
        if ((put + 1) % arr.length == remove) {
            // error
            System.out.println("入队错误");
            return;
        }
        put++;
        arr[put] = a;
        if (put == arr.length) {
            put = 0;
        }
    }

    // 出队
    public int poll() {
        if (remove % arr.length == put) {
            // error
            System.out.println("出队错误");
            return -1;
        }
        int temp = arr[remove];
        remove++;
        if (remove == arr.length) {
            remove = 0;
        }
        return temp;
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(4);
        myQueue.poll();
        for (int i = 0; i < 4; ++i) {
            myQueue.add(i);
        }
        for(int j = 0; j < 5; ++j){
            myQueue.poll();
        }
    }
}
