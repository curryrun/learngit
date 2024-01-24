package com.example.demo.basetype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    // 134. Gas Station
    // https://leetcode.com/problems/gas-station/description/
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] temp = new int[gas.length];
        int sum = 0;
        for (int i = 0; i< gas.length; ++i) {
            temp[i] = gas[i] - cost[i];
            sum = sum + temp[i];
        }
        if (sum < 0) {
            return -1;
        }
        int nowGas = 0, begin = 0;
        for (int i = 0; i < temp.length; ++i) {
            if (0 == nowGas) {
                begin = i;
            }
            nowGas = nowGas + temp[i];
            if (nowGas < 0) {
                nowGas = 0;
            }
        }
        return begin;
    }

    // 135. Candy
    // https://leetcode.com/problems/candy/description/
    // 单独看一个孩子 整体流程拆分成和左侧孩子比，和右侧孩子比 默认值是1
    // 这道题有点意思 可以回过头来再看一遍
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        candy[0] = 1;
        // from left to right
        // 校验的是 左侧是不是比当前大 看的是左孩子
        for (int i = 1; i < candy.length; ++i) {
            // 右侧大 则我应该是左侧的+1
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            } else {
                candy[i] = 1;
            }
        }
        // 从右向左
        // 看的是右侧 当前比右侧大 则是右侧的+1
        for (int i = candy.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }
        int sum = 0;
        for (int i = 0; i< candy.length; ++i) {
            sum = sum + candy[i];
        }
        return sum;
    }

    // 860. Lemonade Change
    // https://leetcode.com/problems/lemonade-change/description/
    public boolean lemonadeChange(int[] bills) {
        int count_5 = 0, count_10 = 0;
        for (int i = 0; i< bills.length; ++i) {
            if (bills[i] == 5) {
                ++count_5;
            }
            if (bills[i] == 10) {
                if (count_5 <= 0) {
                    return false;
                }
                --count_5;
                ++count_10;
            }
            if (bills[i] == 20) {
                if (count_5 <= 0) {
                    return false;
                }
                if (count_10 > 0) {
                    --count_5;
                    --count_10;
                } else {
                    if (count_5 < 3) {
                        return false;
                    }
                    count_5 = count_5 - 3;
                }

            }
        }
        return true;
    }

    // 406. Queue Reconstruction by Height
    // https://leetcode.com/problems/queue-reconstruction-by-height/description/
    // 按身高从大到小排序 相同的则按第二个维度排序
    // 使用链表 方便插入
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i< people.length; ++i) {
            list.add(people[i][1], people[i]);
        }
        return list.toArray(new int[people.length][]);
    }

    // 452. Minimum Number of Arrows to Burst Balloons
    // https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
    // 先按开头排序 重新更新范围
    public int findMinArrowShots(int[][] points) {
        // 注意这里要用 Integer.compare(a[0], b[0])
        // 直接减的话会溢出 真的牛皮
        Arrays.sort(points, (a, b) -> {return Integer.compare(a[0], b[0]);});
        int count = 1;
        for (int i = 1; i< points.length; ++i) {
            if (points[i][0] <= points[i - 1][1]) {
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
            } else {
                count++;
            }
        }
        return count;
    }

    // 435. Non-overlapping Intervals
    // https://leetcode.com/problems/non-overlapping-intervals/description/
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int count = 0;
        for (int i = 1; i< intervals.length; ++i) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                ++count;
                // 这个地方可能end 前面的更大 最新的区间应该是取这两个的最小值
                // 贪心体现在这 如果已经重叠了 那就优先删除最大的范围
                // [1, 4]
                // [2, 3]
                // [3, 4]
                // [1, *, *, *, 4]
                // [*, *,[2, 3] *]
                // [*, *, *, [3 4]
                // 正常情况只要删除 1，4 即可了 所以每次判断的是end的最小值
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
            }
        }
        return count;
    }

    // 763. Partition Labels
    // https://leetcode.com/problems/partition-labels/description/
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> maxMap = new HashMap<>();
        for (int i = 0; i< s.length(); ++i) {
            Character now = s.charAt(i);
            maxMap.put(now, i);
        }
        List<Integer> res = new ArrayList<>();
        int count = 0, max = -1;
        for (int i = 0; i< s.length(); ++i) {
            Character now = s.charAt(i);
            int nowMax = maxMap.get(now);
            if (nowMax > max) {
                max = nowMax;
            }
            ++count;
            // 分隔点
            if (i == max) {
                res.add(count);
                count = 0;
                max = -1;
            }
        }
        return res;
    }

    // 56. Merge Intervals
    // https://leetcode.com/problems/merge-intervals/description/
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int[] pre = result.get(result.size() - 1);
            if (pre[1] >= intervals[i][0]) {
                pre[1] = Math.max(pre[1], intervals[i][1]);
            } else {
                result.add(intervals[i]);
            }
        }
        int[][] resArr = new int[result.size()][2];
        for (int i = 0; i< result.size(); ++i) {
            resArr[i] = result.get(i);
        }
        return resArr;
    }

    // 738. Monotone Increasing Digits
    // https://leetcode.com/problems/monotone-increasing-digits/description/
    public int monotoneIncreasingDigits(int n) {
        if (n == 0) {
            return 0;
        }
        List<Integer> numbers = new ArrayList<>();
        while (n > 0) {
            numbers.add(n % 10);
            n = n / 10;
        }
        for (int i = 1; i< numbers.size(); ++i) {
            if (numbers.get(i - 1) < numbers.get(i)) {
                int temp = i - 1;
                while (temp >= 0) {
                    numbers.set(temp, 9);
                    temp = temp - 1;
                }
                numbers.set(i, numbers.get(i) - 1);
            }
        }
        int res = 0, count = 1;
        for (int i = 0; i< numbers.size(); ++i) {
            res = res + numbers.get(i) * count;
            count = count * 10;
        }
        return res;
    }

    public static void main(String[] args) {
        Greedy greedy = new Greedy();
//        System.out.println(greedy.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
//        System.out.println(greedy.largestSumAfterKNegations(new int[]{3,-1,0,2}, 3));
//        System.out.println(greedy.candy(new int[]{1,0,2}));
//        System.out.println(greedy.candy(new int[]{1,3,4,5,2}));
//        System.out.println(greedy.findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
//        System.out.println(greedy.eraseOverlapIntervals(new int[][]{{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}}));
//        System.out.println(greedy.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(greedy.monotoneIncreasingDigits(100));
    }

}
