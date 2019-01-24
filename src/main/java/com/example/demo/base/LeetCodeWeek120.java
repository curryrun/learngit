package com.example.demo.base;

import java.util.Arrays;

/**
 * @author zhangdongrun
 * @date 2019/1/20 上午10:25
 */
public class LeetCodeWeek120 {

    public static void main(String[] args) {
        int[] arr = new int[]{0,1,1,0,1,0,1,1,0,0};
//        System.out.println(maxTurbulenceSize(arr));

        int[][] arr1 = new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        uniquePathsIII(arr1);
    }

    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        for(int i= 0; i< A.length; ++i){
            res[i] = A[i] * A[i];
        }
        Arrays.sort(res);
        return res;
    }

    public static int maxTurbulenceSize(int[] A) {
        int max = 0;
        int count = 0;
        for(int i = 0; i< A.length; ++i){
            boolean preIsBig = false;
            count = 0;
            for(int j = i + 1; j< A.length; ++j){
                // start
                if(j == i + 1){
                    if(A[i] > A[j]){
                        preIsBig = true;
                    }else if(A[i] < A[j]){
                        preIsBig = false;
                    }else {
                        break;
                    }
                    count++;
                }
                else {
                    // 前一个是大于号
                    if(preIsBig){
                        if(A[j - 1] < A[j]){
                            count++;
                            preIsBig = false;
                        }else {
                            break;
                        }
                    }
                    // 前一个是小于号
                    else {
                        if(A[j - 1] > A[j]){
                            count++;
                            preIsBig = true;
                        }else {
                            break;
                        }
                    }
                }
            }
            max = Math.max(max, count+ 1);
        }
        return max;
    }

    public int distributeCoins(TreeNode root) {
        return 0;
    }

    public static int count = 0;

    public static int zeroCount = 0;

    public static int uniquePathsIII(int[][] grid) {
        int nowHeng =0, nowZong = 0;
        for(int i = 0; i< grid.length; ++i){
            for(int j = 0; j< grid[i].length; ++j){
                if(grid[i][j] == 0){
                    zeroCount++;
                }
                if(grid[i][j] == 1){
                    nowHeng = i;
                    nowZong = j;
                }
            }
        }
        find(grid, nowHeng, nowZong, nowHeng, nowZong, zeroCount);
        return count;
    }

    public static void find(int[][] grid, int nowHeng, int nowZong, int preHeng, int preZong, int zeroNum){
        if(nowHeng < 0 || nowHeng >= grid.length){
            return;
        }
        if(nowZong < 0 || nowZong >= grid[nowHeng].length){
            return;
        }
        if(grid[nowHeng][nowZong] == -1){
            return;
        }
        if(grid[nowHeng][nowZong] == 2 && zeroCount == zeroNum){
            count++;
            return;
        }
        zeroCount++;
        if(preHeng != nowHeng - 1){
            find(grid, nowHeng - 1, nowZong, nowHeng, nowZong, zeroNum);
        }
        if(preHeng != nowHeng + 1) {
            find(grid, nowHeng + 1, nowZong, nowHeng, nowZong, zeroNum);
        }
        if(preZong != nowZong - 1){
            find(grid, nowHeng, nowZong - 1, nowHeng, nowZong, zeroNum);
        }
        if(preZong != nowZong + 1) {
            find(grid, nowHeng, nowZong + 1, nowHeng, nowZong, zeroNum);
        }
    }

}
