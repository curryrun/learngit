package com.example.demo.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname ForAWS_Tree
 * @Description
 * @Date 2024/1/2 15:22
 * @Creater zhangdongrun_dxm
 **/
public class ForAWS_Tree {

    // https://leetcode.com/problems/binary-tree-paths/
    // 257. 二叉树的所有路径
    // 给定一个二叉树，返回所有从根节点到叶子节点的路径。
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> resList = new ArrayList<>();
        if (null == root) {
            return resList;
        }
        String beginStr = String.valueOf(root.val);
        if (null == root.left && null == root.right) {
            resList.add(beginStr);
            return resList;
        }
        // left
        if (null != root.left) {
            deepBinaryTreePaths(root.left, resList, beginStr);
        }
        // right
        if (null != root.right) {
            deepBinaryTreePaths(root.right, resList, beginStr);
        }
        return resList;
    }

    public void deepBinaryTreePaths(TreeNode root, List<String> resList, String beginStr) {
        if (null == root) {
            return;
        }
        // leaf
        if (null == root.left && null == root.right) {
            resList.add(beginStr + "->" + root.val);
            return;
        }
        // left
        if (null != root.left) {
            deepBinaryTreePaths(root.left, resList, beginStr + "->" + root.val);
        }
        // right
        if (null != root.right) {
            deepBinaryTreePaths(root.right, resList, beginStr + "->" + root.val);
        }
    }

    // 层次遍历
    public List<Integer> bfs(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> resList = new ArrayList<>();
        if (null == root) {
            return resList;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            resList.add(node.val);
            if (null != node.left) {
                queue.offer(node.left);
            }
            if (null != node.right) {
                queue.offer(node.right);
            }
        }
        return resList;
    }

    // 98. Validate Binary Search Tree
    // https://leetcode.com/problems/validate-binary-search-tree/
    // 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return deepIsValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean deepIsValidBST(TreeNode root, Long min, Long max) {
        if (null == root) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return deepIsValidBST(root.left, min, Long.valueOf(root.val)) && deepIsValidBST(root.right, root.val.longValue(), max);
    }

    // 104. Maximum Depth of Binary Tree
    // 104. 二叉树的最大深度
    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public Integer maxDeep = Integer.MIN_VALUE;
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        deepMaxDepth(root, 0);
        return maxDeep;
    }

    public void deepMaxDepth(TreeNode root, int pre) {
        // leaf
        if (null == root.left && null == root.right) {
            if (pre + 1 > maxDeep) {
                maxDeep = pre + 1;
            }
            return;
        }
        if (null != root.left) {
            deepMaxDepth(root.left, pre + 1);
        }
        if (null != root.right) {
            deepMaxDepth(root.right, pre + 1);
        }
    }

    // 110. Balanced Binary Tree
    // https://leetcode.com/problems/balanced-binary-tree/
    // 从下往上 先进到根节点
    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        return getMaxDeep(root) >= 0;
    }

    public Integer getMaxDeep(TreeNode root) {
        // end 左右孩子为空的话 就当做深度0
        if (null == root) {
            return 0;
        }
        int left = getMaxDeep(root.left);
        int right = getMaxDeep(root.right);
        if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
            return -1;
        } else {
            // 最下面这层比较完了 +1 开始往上找
            return Math.max(left, right) + 1;
        }
    }

    // 124. Binary Tree Maximum Path Sum
    // https://leetcode.com/problems/binary-tree-maximum-path-sum/
    public int maxPathSum(TreeNode root) {
        return 0;
    }


}
