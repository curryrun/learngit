package com.example.demo.basetype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void printArr(int[][] arr) {
        // 打印dp数组
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

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

    // 背包问题
    // dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
    // https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html#%E6%80%9D%E8%B7%AF
    // 存在两种可能 1放进去 2 不放进去
    // 不放进去就是,dp[i - 1][j], 前一个的值 放进去 就得找到刚刚好够放的那个 再加上这个放进去的价值
    // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
    // 二维数组写法
    private int bagP1(int[] weight, int[] value, int bagSize) {
        int[][] dp = new int[weight.length][bagSize + 1];
        // init
        // 尝试把第一个物品放进背包里
        for (int j = 1; j < dp[0].length; ++j) {
            if (j >= weight[0]) {
                dp[0][j] = value[0];
            }
        }
        // 后遍历背包容量
        for (int j = 1; j < dp[0].length; ++j) {
            // 先遍历物品
            for (int i = 1; i < dp.length; ++i) {
                // 当前背包容量小 那就是前一个i的数值
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        // 打印dp数组
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
        return dp[weight.length - 1][bagSize];
    }

    // 还是背包问题 这次要用一维数组解决 上面的可以复制下来 这种情况可以简化 这一维数组相当于一直滚动 只需要记录这一维就够用了
    // 0	15	15	15	15
    // 0	15	15	20	35
    // 0	15	15	20	35
    // 第二层遍历的时候 可以在第一层上接着改 所以公式变成:
    // dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]); =>
    // dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
    // 相当于把i这一层维度去掉了
    // 一维数组要从后往前遍历 从前往后的话会把上一轮的数组覆盖掉
    private int bagP2(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < weight.length; ++i) {
            for (int j = bagSize; j >= weight[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagSize];
    }

    // 416. Partition Equal Subset Sum
    // https://leetcode.com/problems/partition-equal-subset-sum/description/
    // 加和/2 = 背包大小
    // 二维数组背包问题： dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少。
    // dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
    // 这里大小和价值完全相等
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum = sum + nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int bagSize = sum / 2;
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i< nums.length; ++i) {
            for (int j = bagSize; j >= nums[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        if (dp[bagSize] == bagSize) {
            return true;
        }
        return false;
    }

    // 1049. Last Stone Weight II
    // https://leetcode.com/problems/last-stone-weight-ii/description/
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i< stones.length; ++i) {
            sum = sum + stones[i];
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; ++i) {
            for (int j = target; j >= stones[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[target] - dp[target];
    }

    // 用二维数组写
    public int lastStoneWeightIIV2(int[] stones) {
        int sum = 0;
        for (int i = 0; i< stones.length; ++i) {
            sum = sum + stones[i];
        }
        int target = sum / 2;
        int[][] dp = new int[stones.length][target + 1];
        // init
        // 尝试把第一个物品放进背包里
        for (int j = 1; j < dp[0].length; ++j) {
            if (j >= stones[0]) {
                dp[0][j] = stones[0];
            }
        }
        for (int i = 1; i< stones.length; ++i) {
            for (int j = 1; j <= target; ++j) {
                if (j < stones[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                }
            }
        }
        return sum - dp[stones.length - 1][target] - dp[stones.length - 1][target];
    }

    // 494. Target Sum
    // https://leetcode.com/problems/target-sum/
    // 二维数组
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i< nums.length; ++i) {
            sum = sum + nums[i];
        }
        if (sum < Math.abs(target)) {
            return 0;
        }
        if ((sum + target) % 2 != 0) {
            return 0;
        }
        int left = (sum + target) / 2;
        int[][] dp = new int[nums.length][left + 1];
        // init
        if (nums[0] <= left) {
            dp[0][nums[0]] = 1;
        }

        int numZeros = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                numZeros++;
            }
            dp[i][0] = (int) Math.pow(2, numZeros);
        }

        for (int i = 1; i < nums.length; ++i) {
            for (int j = 1; j <= left; ++j) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                }
            }
        }
//        printArr(dp);
        return dp[nums.length - 1][left];
    }

    // 一维数组
    public int findTargetSumWaysV2(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i< nums.length; ++i) {
            sum = sum + nums[i];
        }
        if (sum < Math.abs(target)) {
            return 0;
        }
        if ((sum + target) % 2 != 0) {
            return 0;
        }
        int left = (sum + target) / 2;
        int[] dp = new int[left + 1];
        // 初始化
        // 相当于从num的 0, 容量为0的背包 有多少种方法
        // 如果数组[0] ，target = 0，那么 bagSize = (target + sum) / 2 = 0。 dp[0]也应该是1， 也就是说给数组里的元素 0 前面无论放加法还是减法，都是 1 种方法。
        dp[0] = 1;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = left; j >= nums[i]; --j) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[left];
    }

    // 先用回溯做一下
    public int findTargetSumWaysTrack(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i< nums.length; ++i) {
            sum = sum + nums[i];
        }
        if (sum < target) {
            return 0;
        }
        if ((sum + target) % 2 != 0) {
            return 0;
        }
        Arrays.sort(nums);
        int left = (sum + target) / 2;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> itemList = new ArrayList<>();
        findTargetSumWaysDeep(nums, left, 0, 0, res, itemList);
        return res.size();
    }

    public void findTargetSumWaysDeep(int[] nums, int left, int startIndex, int sum, List<List<Integer>> res, List<Integer> itemList) {
        if (sum > left) {
            return;
        }
        if (sum == left) {
            res.add(new ArrayList<>(itemList));
        }
        for (int i = startIndex; i < nums.length; ++i) {
            int tempSum = sum + nums[i];
            if (tempSum > left) {
                break;
            }
            itemList.add(nums[i]);
            findTargetSumWaysDeep(nums, left, i + 1, tempSum, res, itemList);
            itemList.remove(itemList.size() - 1);
        }
    }

    // 474. Ones and Zeroes
    // https://leetcode.com/problems/ones-and-zeroes/description/
    // dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String item: strs) {
            int number0 = 0, number1 = 0;
            for (int i = 0; i < item.length(); ++i) {
                if ('0' == item.charAt(i)) {
                    ++number0;
                } else if ('1' == item.charAt(i)) {
                    ++number1;
                }
            }
            // 0-1背包问题 遍历背包需要从后往前遍历
            for (int i = m; i >= number0; --i) {
                for (int j = n; j >= number1; --j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - number0][j- number1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    // 完全背包问题 和01背包不同 01背包的一个物品只能装一次 完全背包是物品可以装多次


    public static void main(String[] args) {
        Dynamic dynamic = new Dynamic();
//        System.out.println(dynamic.integerBreakV2(8));
//        System.out.println(dynamic.numTrees(3));
//        System.out.println(dynamic.bagP1(new int[]{1,3,4}, new int[]{15,20,30}, 4));
//        System.out.println(dynamic.bagP2(new int[]{1,3,4}, new int[]{15,20,30}, 4));
        System.out.println(dynamic.findTargetSumWaysV2(new int[]{1,1,1,1,1}, 3));
    }

}
