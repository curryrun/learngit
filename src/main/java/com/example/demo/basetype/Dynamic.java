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

    // 746  Min Cost Climbing Stairs
    // https://leetcode.com/problems/min-cost-climbing-stairs/description/
    // 含义为调到第i层 的最小cost
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 1) {
            return cost[0];
        }
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i< dp.length; ++i) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[dp.length - 1];
    }

    // 62. Unique Paths
    // https://leetcode.com/problems/unique-paths/description/
    // dp[i][j] 代表到达i j 位置的路径数量
    // 由于只能向下或者向右 初始化路径的都是 1
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i< m; ++i) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp [i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // 63. Unique Paths II
    // https://leetcode.com/problems/unique-paths-ii/description
    // dp[i][j] 代表到达i j 位置的路径数量
    // 初始化的时候要注意 第一行和第一列里 如果前一个有障碍，那么也到不了现在这个地方，所以障碍物后面的都要初始化为0
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < dp.length; ++i) {
            if (obstacleGrid[i][0] == 1 || (i > 0 && dp[i-1][0] == 0)) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < dp[0].length; ++j) {
            if (obstacleGrid[0][j] == 1 || (j > 0 && dp[0][j - 1] == 0)) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[i].length; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    // 343. Integer Break
    // https://leetcode.com/problems/integer-break/description/
    // 如果不用动态递归 凭感觉 将int拆分成多个 直到长度是n/2  达到拆分数量和拆分数值大小的平衡
    public int integerBreak(int n) {
        int half = n / 2;
        if (half <= n) {
            half = n;
        }
        int max = 0;
        for (int i = 2; i <= half; ++i) {
            int item = n / i, yushu = 0;
            if (n % i > 0) {
                yushu = n % i;
            }
            int[] splitArr = new int[i];
            int tempRes = 1;
            for (int j = 0; j < splitArr.length; ++j) {
                if (yushu > 0) {
                    splitArr[j] = item + 1;
                    --yushu;
                } else {
                    splitArr[j] = item;
                }
                tempRes = tempRes * splitArr[j];
            }
            if (tempRes > max) {
                max = tempRes;
            }
        }
        return max;
    }

    // 343. Integer Break
    // https://leetcode.com/problems/integer-break/description/
    // 用动归来写
    // TODO 后面再看看
    public int integerBreakV2(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i< dp.length; ++i) {
            for (int j = 1; j <= i - j; ++j) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }

    // 96. Unique Binary Search Trees
    // https://leetcode.com/problems/unique-binary-search-trees/description/
    // dp[i] 含义是i个的数量
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // i是现在的节点数量
        for (int i = 2; i <= n; ++i) {
            // j是数量为j的左子树数量(j把头结点算进来了 所以事实上是j - 1是左节点数量 i - j 是右节点数量)
            // 注意 这里要遍历到等于i 也就是右子树完全为0的时候
            for (int j = 1; j <= i; ++j) {
                dp[i] = dp[i] + dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Dynamic dynamic = new Dynamic();
//        System.out.println(dynamic.integerBreakV2(8));
        System.out.println(dynamic.numTrees(3));
    }

}
