package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/2/3 上午10:22
 */
public class LeetXCodeWeek122 {
    public static void main(String[] args) {
        int[][] queries = new int[][]{{1, 0}, {-3, 1}, {-4, 0}, {2, 3}};
        int[] A = new int[]{1, 2, 3, 4};
//        System.out.println(sumEvenAfterQueries(A, queries));
        System.out.println((char) ('a' + 1));
        TreeNode t1 = new TreeNode(0);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(3);
        TreeNode t7 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        smallestFromLeaf(t1);
    }

    public static int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] res = new int[A.length];
        int sum = 0;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] % 2 == 0) {
                sum = sum + A[i];
            }
        }
        for (int i = 0; i < queries.length; ++i) {
            int index = queries[i][1];
            int temp = A[index] + queries[i][0];
            if (A[index] % 2 == 0) {
                sum = sum - A[index];
            }
            if (temp % 2 == 0) {
                sum = sum + temp;
            }
            A[index] = temp;
            res[i] = sum;
        }
        return res;
    }

    public static String res = "";

    public static TreeNode target = new TreeNode(Integer.MAX_VALUE);

    public static int smallLevel = Integer.MAX_VALUE;

    public static String smallestFromLeaf(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        int level = Integer.MAX_VALUE;
        findNode(sb, root, 0);
        StringBuilder sb2 = new StringBuilder(res);
        return sb2.reverse().toString();
    }

    public static void findNode(StringBuilder sb, TreeNode node, int level) {
        if (null == node) {
            return;
        }
        ++level;
        sb.append((char) ('a' + node.val));
        findNode(sb, node.left, level);
        findNode(sb, node.right, level);
        if (null == node.left && null == node.right) {
            if (node.val < target.val) {
                smallLevel = level;
                target = node;
                res = sb.toString();
            } else if (node.val == target.val && level < smallLevel) {
                smallLevel = level;
                target = node;
                res = sb.toString();
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        --level;
    }

}
