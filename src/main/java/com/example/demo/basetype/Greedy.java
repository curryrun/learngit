package com.example.demo.basetype;

import java.util.Arrays;

/**
 * @Classname Greedy
 * @Description
 * @Date 2024/1/16 19:55
 * @Creater zhangdongrun_dxm
 * 贪心算法
 * https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
 * <p>
 * 贪心的本质是选择每一阶段的局部最优，从而达到全局最优。
 * 那么如何能看出局部最优是否能推出整体最优呢？有没有什么固定策略或者套路呢？
 * 不好意思，也没有！ 靠自己手动模拟，如果模拟可行，就可以试一试贪心策略，如果不可行，可能需要动态规划。
 * 有同学问了如何验证可不可以用贪心算法呢？
 * 最好用的策略就是举反例，如果想不到反例，那么就试一试贪心吧。
 **/
public class Greedy {

    // 455. Assign Cookies
    // https://leetcode.com/problems/assign-cookies/description/
    // g child s cookie
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int cookiePos = 0, childPos = 0;
        while (cookiePos < s.length && childPos < g.length) {
            int nowChild = g[childPos];
            int nowCookie = s[cookiePos];
            if (nowCookie >= nowChild) {
                count++;
                childPos++;
                cookiePos++;
            } else {
                cookiePos++;
            }
        }
        return count;
    }

    // 376. Wiggle Subsequence
    // https://leetcode.com/problems/wiggle-subsequence/description/
    public int wiggleMaxLength(int[] nums) {
        int count = 1;
        if (nums.length < 2) {
            return count;
        }
        int nowTop = nums[0];
        Boolean isIncr = null;
        for (int i = 1; i < nums.length; ++i) {
            int now = nums[i];
            // 确定趋势
            if (null == isIncr) {
                // 相同就接着往后走
                if (now == nowTop) {
                    continue;
                }
                if (now > nowTop) {
                    isIncr = true;
                    ++count;
                } else {
                    isIncr = false;
                    ++count;
                }
                nowTop = now;
                continue;
            }
            // 相同就接着往后走
            if (now == nowTop) {
                continue;
            }
            // 之前是递增趋势
            if (isIncr) {
                // 现在还是递增
                if (now > nowTop) {
                    nowTop = now;
                    continue;
                }
                // 现在是递减
                else {
                    nowTop = now;
                    ++count;
                    isIncr = false;
                }
            }
            // 之前是递减趋势
            else {
                // 现在还是递减
                if (now < nowTop) {
                    nowTop = now;
                    continue;
                }
                // 现在是递增
                else {
                    nowTop = now;
                    ++count;
                    isIncr = true;
                }
            }
        }
        return count;
    }

    // 53. Maximum Subarray
    // https://leetcode.com/problems/maximum-subarray/description/
    // 贪心贪的是哪里呢？
    // 如果 -2 1 在一起，计算起点的时候，一定是从 1 开始计算，因为负数只会拉低总和，这就是贪心贪的地方！
    // 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
    // 全局最优：选取最大“连续和”
    public int maxSubArray(int[] nums) {
        int nowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            nowSum = nowSum + nums[i];
            maxSum = Math.max(maxSum, nowSum);
            // 负数了 则从0计数
            if (nowSum < 0) {
                nowSum = 0;
            }
        }
        return maxSum;
    }

    // 122. Best Time to Buy and Sell Stock II
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    public int maxProfit(int[] prices) {
        Boolean isIncr = null;
        if (prices.length < 2) {
            return 0;
        }
        int sum = 0, pre = prices[0], low = 0;
        for (int i = 1; i < prices.length; ++i) {
            int now = prices[i];
            // find first incr
            if (null == isIncr) {
                if (now > pre) {
                    low = pre;
                    isIncr = true;
                    // 最后一天还在涨 那就直接当天卖掉
                    if (i == prices.length - 1) {
                        sum = sum + now - low;
                    }
                }
                pre = now;
            }
            else {
                // 如果是递增趋势
                if (isIncr) {
                    // 当前还在涨
                    if (now > pre) {
                        pre = now;
                        // 最后一天还在涨 那就直接当天卖掉
                        if (i == prices.length - 1) {
                            sum = sum + now - low;
                        }
                    }
                    // 当前下跌了 之前就该卖出
                    else {
                        sum = sum + pre - low;
                        low = now;
                        isIncr = false;
                        pre = now;
                    }
                }
                // 进入下跌趋势 那就要找到最低点
                else {
                    // 当前在涨 前一个就是最低点
                    if (now > pre) {
                        low = pre;
                        isIncr = true;
                        pre = now;
                        // 最后一天还在涨 那就直接当天卖掉
                        if (i == prices.length - 1) {
                            sum = sum + now - low;
                        }
                    }
                    // 当前下跌了 继续往后走
                    else {
                        pre = now;
                    }
                }
            }
        }
        return sum;
    }

    // 55. Jump Game
    // https://leetcode.com/problems/jump-game/description/
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int maxPos = 0;
        for (int i = 0; i< nums.length; ++i) {
            if (i > maxPos) {
                return false;
            }
            int possibleMaxPos = nums[i] + i;
            maxPos = Math.max(maxPos, possibleMaxPos);
            if (maxPos >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    // 45. Jump Game II
    // https://leetcode.com/problems/jump-game-ii/description/
    // 每一跳 都取可见范围内最大的
    public static int jump(int[] nums) {
        int count = 0;
        int beginPos = 0;
        while (beginPos < nums.length - 1) {
            // 进入跳转 加一
            ++count;
            // 当前位置可跳转的最大值 = 当前位置 + 当前位置对应的数组中的值
            int maxPos = beginPos + nums[beginPos], nextJump = 0;
            // 已经超了的话 就不用选下一跳转的点了
            if (maxPos >= nums.length - 1) {
                break;
            }
            // 选下一个要跳转的点 当然是要在当前可见范围内 也就是 [beginPos + 1, beginPos + nums[beginPos]]
            for (int j = beginPos + 1; j <= beginPos + nums[beginPos]; ++j) {
                if (j + nums[j] > maxPos) {
                    nextJump = j;
                    maxPos = j + nums[j];
                }
            }
            // 跳到下一个点位上
            beginPos = nextJump;
        }
        return count;
    }

    // 1005. Maximize Sum Of Array After K Negations
    // https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/description/
    public int largestSumAfterKNegations(int[] nums, int k) {
        int remainK = k;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (remainK <= 0) {
                break;
            }
            if (nums[i] < 0) {
                nums[i] = -nums[i];
                --remainK;
            }
        }
        if (remainK > 0) {
            // 剩余奇数
            if (remainK % 2 == 1) {
                int minPos = 0, min = Integer.MAX_VALUE;
                for (int i = 0; i < nums.length; ++i) {
                    if (nums[i] < min) {
                        min = nums[i];
                        minPos = i;
                    }
                }
                nums[minPos] = -nums[minPos];
            }
        }
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            result = result + nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Greedy greedy = new Greedy();
//        System.out.println(greedy.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(greedy.largestSumAfterKNegations(new int[]{3,-1,0,2}, 3));
    }

}
