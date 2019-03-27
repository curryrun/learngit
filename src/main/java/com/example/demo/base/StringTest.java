package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/2/23 下午10:24
 */
public class StringTest {
    public static void main(String[] args) {
        String str1  = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
