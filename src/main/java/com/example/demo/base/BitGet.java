package com.example.demo.base;

import java.util.LinkedList;

/**
 * @author zhangdongrun
 * @date 2019/4/22 下午12:07
 */
public class BitGet {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-7));
        NumberOf1(-7);
        get(-7);
        get(7);
    }

    // 输出一个整数的二进制 1的个数 负数使用补码 补码 = 反码 + 1
    public static void NumberOf1(int n) {
        boolean isF = false;
        if (n < 0) {
            isF = true;
            // 负数的绝对值的 原码 - 1 再取反 就是负数的补码
            n = -n - 1;
        }
        LinkedList<Integer> list = new LinkedList<>();
        while (n > 0) {
            list.add(n % 2);
            n = n / 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; --i) {
            if (isF) {
                if (i > list.size() - 1) {
                    sb.append(1);
                } else {
                    // 1->0 0->1 原码取反
                    sb.append(list.get(i) == 1 ? 0 : 1);
                }
            } else {
                if (i > list.size() - 1) {
                    sb.append(0);
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        System.out.println(sb.toString());
    }

    // 优雅的使用位运算
    public static void get(int n) {
        StringBuilder sb = new StringBuilder();
        while (0 != n) {
            int res = (1 & n);
            sb.append(res);
            n = n >>> 1;
        }
        System.out.println(sb.reverse().toString());
    }

}
