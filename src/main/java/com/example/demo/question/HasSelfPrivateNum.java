package com.example.demo.question;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangdongrun
 * @date 2018/9/12 上午11:59
 */
public class HasSelfPrivateNum {

    private int num = 0;
    public void addI(String userName){
        if(userName.equals("a")){
            num = 100;
            System.out.println("a set over");
        }else {
            num = 200;
            System.out.println("b set over");
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("aaaa");
        System.out.println(threadLocal.get());
    }

}
