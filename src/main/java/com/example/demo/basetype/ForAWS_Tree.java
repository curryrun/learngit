package com.example.demo.basetype;

import com.example.demo.base.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname ForAWS_Tree
 * @Description
 * @Date 2024/1/2 15:22
 * @Creater zhangdongrun_dxm
 **/
public class ForAWS_Tree {

    // 144. Binary Tree Preorder Traversal
    // https://leetcode.com/problems/binary-tree-preorder-traversal/
    // 前序遍历 使用stack 先放右孩子 再放左孩子
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (null == root) {
            return resList;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            resList.add(temp.val);
            if (null != temp.right) {
                stack.push(temp.right);
            }
            if (null != temp.left) {
                stack.push(temp.left);
            }
        }
        return resList;
    }

    // 94. Binary Tree Inorder Traversal
    // https://leetcode.com/problems/binary-tree-inorder-traversal/
    // 中序遍历
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (null == root) {
            return resList;
        }
        TreeNode temp = root;
        while (null != temp || !stack.isEmpty()) {
            // 为了一直找到左边的底 每棵子树都这样做
            while (null != temp) {
                stack.push(temp);
                temp = temp.left;
            }
            // left root right
            TreeNode popNode = stack.pop();
            resList.add(popNode.val);
            // switch 把right也加入左孩子找到底的遍历
            temp = popNode.right;
        }
        return resList;
    }

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
    public static Integer maxPathSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        deepMaxPathSum(root);
        return maxPathSum;
    }

    // 获取子树（左/右）最大值
    // 在这个递归的过程中 可以获得 左子树+右子树+root的 最大值
    public static int deepMaxPathSum(TreeNode root) {
        if (null == root) {
            return 0;
        }
        // 如果是负数 则可以不用带上这颗子树 也就是最大值 0
        int leftMax = Math.max(deepMaxPathSum(root.left), 0);
        int rightMax = Math.max(deepMaxPathSum(root.right), 0);
        int newPathValue = leftMax + root.val + rightMax;
        maxPathSum = Math.max(maxPathSum, newPathValue);
        return Math.max(leftMax + root.val, rightMax + root.val);
    }

    // 235. Lowest Common Ancestor of a Binary Search Tree
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return root;
        }
        if (p == root || q == root) {
            return root;
        }
        // find p or q 是否在左 or 右子树上
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // left 为空则一定都在right上
        if (null == left) {
            return right;
        } else if (null == right) {
            return left;
        }
        // 否则分别在在左右两边 直接返回根即可
        else {
            return root;
        }
    }

    // 102. Binary Tree Level Order Traversal
    // 层序遍历
    // https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (null == root) {
            return resList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 使用size 做一下每一层的数量记录
            int size = queue.size();
            List<Integer> itemList = new ArrayList<>();
            for (int i = 0; i< size; ++i) {
                TreeNode temp = queue.poll();
                itemList.add(temp.val);
                if (null != temp.left) {
                    queue.offer(temp.left);
                }
                if (null != temp.right) {
                    queue.offer(temp.right);
                }
            }
            resList.add(itemList);
        }
        return resList;
    }

    // 107. Binary Tree Level Order Traversal II
    // https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    // 自底向上遍历
    // Z字型遍历 可以继续用这个办法 隔一层做一个reverse
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> resList = new ArrayList<>();
        if (null == root) {
            return resList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> itemList = new ArrayList<>();
            for (int i = 0; i< size; ++i) {
                TreeNode temp = queue.poll();
                itemList.add(temp.val);
                if (null != temp.left) {
                    queue.offer(temp.left);
                }
                if (null != temp.right) {
                    queue.offer(temp.right);
                }
            }
            resList.add(itemList);
        }
        Collections.reverse(resList);
        return resList;
    }

    // 701. Insert into a Binary Search Tree
    // https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (null == root) {
            return new TreeNode(val);
        }
        // 挂在左孩子上
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        // 挂在右孩子上
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(5);
        TreeNode t5 = new TreeNode(3);
        root.setLeft(t1);
        root.setRight(t2);
        t1.setLeft(t3);
        t2.setLeft(t5);
//        inorderTraversal(root);
//        System.out.println(maxPathSum(new TreeNode(0)));
        lowestCommonAncestor(root, t3, t5);

    }


}
