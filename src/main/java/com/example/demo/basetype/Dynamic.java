package com.example.demo.basetype;

/**
 * @Classname Dynamic
 * @Description
 * @Date 2024/1/24 22:06
 * @Creater zhangdongrun_dxm
 *
 * 动规是由前一个状态推导出来的，而贪心是局部直接选最优的
 * 动态规划
 * Dynamic Programming，简称DP
 * 1、确定dp[i]含义
 * 2、推导 递推公式
 * 3、dp 初始化
 * 4、确定dp的顺序
 * 5、举例，把每一步都过一下
 *
 **/
public class Dynamic {

    // 509. Fibonacci Number
    // https://leetcode.com/problems/fibonacci-number/description/
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i<= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 70. Climbing Stairs
    // https://leetcode.com/problems/climbing-stairs/description/
    // dp[i] 第i级台阶 有多少种可能跳上去
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
