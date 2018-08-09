package com.example.demo.util;

import java.lang.instrument.Instrumentation;

/**
 * @author zhangdongrun
 * @date 2018/8/9 下午12:11
 */
public class ObjectSize {
    private static volatile Instrumentation instru;

    public static void premain(String args, Instrumentation inst) {
        instru = inst;
    }

    public static Long getSizeOf(Object object) {
        if (instru == null) {
            throw new IllegalStateException("Instrumentation is null");
        }
        return instru.getObjectSize(object);
    }

    public static void main(String[] args) {
        String a = "aaa";
        System.out.println(ObjectSize.getSizeOf(a));
    }
}
