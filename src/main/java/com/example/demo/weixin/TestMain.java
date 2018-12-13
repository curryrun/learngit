package com.example.demo.weixin;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangdongrun
 * @date 2018/11/28 下午5:47
 */
public class TestMain {



    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ReentrantLock lock = new ReentrantLock();

//        classLoader
        System.out.println("======" + new Son().str);
    }
}
