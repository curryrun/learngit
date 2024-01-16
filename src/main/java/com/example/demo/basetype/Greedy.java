package com.example.demo.basetype;

import java.util.Arrays;
import java.util.HashMap;
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

    public static void main(String[] args) {
        Greedy greedy = new Greedy();
        System.out.println(greedy.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
    }

}
