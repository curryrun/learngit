package com.example.demo.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangdongrun
 * @date 2018/12/12 下午5:03
 */
public class TreeNode {

    private ConcurrentHashMap map;

    private HashMap hashMap;

    public TreeNode left;

    public TreeNode right;

    public Integer val;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Integer getValue() {
        return val;
    }

    public void setValue(Integer value) {
        this.val = value;
    }

    public TreeNode(Integer value) {
        this.left = null;
        this.right = null;
        this.val = value;
    }

    // 树的结构 https://blog.csdn.net/My_Jobs/article/details/43451187
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r1 = new TreeNode(2);
        TreeNode r2 = new TreeNode(3);
        TreeNode r3 = new TreeNode(4);
        TreeNode r4 = new TreeNode(5);
        TreeNode r5 = new TreeNode(6);
        TreeNode r6 = new TreeNode(7);
        TreeNode r7 = new TreeNode(8);
        root.setLeft(r1);
        root.setRight(r2);
        r2.setRight(r5);
        r1.setLeft(r3);
        r1.setRight(r4);
        r4.setLeft(r6);
        r4.setRight(r7);

//        cengci(root);
//        cengciComm(root);
//        zhongxu(root);
//        houxu(root);
//        level(root);
//        findRoot(root, 10);

//        System.out.println(numTrees(3));
//        generateTrees(3);
//        zhongxu(root);
        System.out.println();
//        zhongxu2(root);
//        System.out.println(getMax(root));
        System.out.println(SerializeDigui(root));

    }

    public static void cengci(TreeNode node) {
        if (null == node) {
            return;
        }
        cengci(node.getLeft());
        cengci(node.getRight());
        System.out.println(node.getValue());
    }

    // 前序遍历 先访问这个节点 然后跳到他的left 如果left已经是Null了 则从栈里取出它的right
    public static void qianxu(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode node = root;
        while (null != node || !linkedList.isEmpty()) {
            if (null != node) {
                System.out.println(node.getValue());
                linkedList.push(node);
                node = node.getLeft();
            } else {
                TreeNode treeNode = linkedList.pop();
                node = treeNode.getRight();
            }
        }
    }

    public static void qianxu2(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.push(root);
        while (!linkedList.isEmpty()) {
            TreeNode node = linkedList.pop();
            System.out.println(node.val);
            if (null != node.right) {
                linkedList.push(node.right);
            }
            if (null != node.left) {
                linkedList.push(node.left);
            }
        }
    }

    public static void zhongxu(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode node = root;
        while (null != node || !linkedList.isEmpty()) {
            if (null != node) {
                linkedList.push(node);
                node = node.getLeft();
            } else {
                TreeNode treeNode = linkedList.pop();
                System.out.println(treeNode.getValue());
                node = treeNode.getRight();
            }
        }
    }

    public static void zhongxu2(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode node = root;
        while (null != node || !linkedList.isEmpty()) {
            while (null != node) {
                linkedList.push(node);
                node = node.getLeft();
            }
            TreeNode treeNode = linkedList.pop();
            System.out.println(treeNode.getValue());
            node = treeNode.getRight();
        }
    }

    public static void houxu(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.push(root);
        // 上一个被输出的节点
        TreeNode pre = null;
        while (!linkedList.isEmpty()) {
            TreeNode cur = linkedList.peek();
            // 出栈的时机
            // 1 左右孩子都是Null 即当前自己就是叶子节点
            // 2 右孩子是null 自己的左孩子已经被输出 其实也就是上一个被输出的节点是自己的左孩子
            // 3 自己的右孩子已经被输出 也就是说上一个被输出的节点是自己的右孩子
            if ((null == cur.getLeft() && null == cur.getRight()) || (null != pre && ((null == pre.getRight() && cur == pre.getLeft()) || pre == cur.getRight()))) {
                System.out.println(cur.getValue());
                TreeNode treeNode = linkedList.pop();
                pre = treeNode;
            } else {
                // 先入right 因为栈是先入后出 保证先遍历left
                if (null != cur.getRight()) {
                    linkedList.push(cur.getRight());
                }
                if (null != cur.getLeft()) {
                    linkedList.push(cur.getLeft());
                }
            }
        }
    }

    // 层次遍历 poll offer 当队列为空时候，使用add方法会报错，而offer方法会返回false
    // add当属于List
    // offer当属于queue
    public static void level(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            System.out.println(top.getValue());
            if (null != top.getLeft()) {
                queue.add(top.getLeft());
            }
            if (null != top.getRight()) {
                queue.add(top.getRight());
            }
        }
    }

    public static LinkedList<TreeNode> findRoot(TreeNode root, int target) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = root.getValue();
        TreeNode prePop = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            // 出栈时机
            // 1、如果上一个被pop的是当前栈顶节点的左孩子，并且右孩子为null
            // 2、如果上一个被pop的是当前栈顶节点的右孩子
            // 3、如果已经到了叶子节点加和还不满足条件的时候
            if (prePop != null && ((prePop == temp.getLeft() && temp.getRight() == null) || prePop == temp.getRight())) {
                prePop = stack.pop();
                sum = sum - prePop.getValue();
            } else if (null != temp.getLeft() && prePop != temp.getLeft()) {
                sum += temp.getLeft().getValue();
                // 判断加和，模拟出栈操作
                if (sum > target) {
                    prePop = temp.getLeft();
                    sum -= temp.getLeft().getValue();
                } else {
                    stack.push(temp.getLeft());
                }
            } else if (null != temp.getRight() && prePop != temp.getRight()) {
                sum += temp.getRight().getValue();
                if (sum > target) {
                    prePop = temp.getRight();
                    sum -= temp.getRight().getValue();
                } else {
                    stack.push(temp.getRight());
                }
            }

            // 叶子节点
            else {
                if (sum == target) {
                    break;
                } else {
                    prePop = stack.pop();
                    sum = sum - prePop.getValue();
                }
            }
        }
        return stack;
    }

    // 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
    public static int numTrees(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        int count = 0;
        // 选出根节点
        for (int node = 1; node <= n; ++node) {
            // 左子树节点个数 为根的值 - 1
            for (int left = node - 1; left < node; ++left) {
                // 右子树节点个数 节点总个数 - 左子树个数 - 1(根节点)
                int right = n - left - 1;
                count = count + numTrees(left) * numTrees(right);
            }
        }
        return count;
    }

    // 给定一个整数 n，输出以 1 ... n 为节点组成的二叉搜索树有
    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return null;
        }
        return treeNodes(1, n);
    }

    public static List<TreeNode> treeNodes(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        // start == end 也不要set null
        if (start > end) {
            res.add(null);
            return res;
        }
        // 选出根节点
        for (int i = start; i <= end; ++i) {
            List<TreeNode> leftSub = treeNodes(start, i - 1);
            List<TreeNode> rightSub = treeNodes(i + 1, end);

            for (TreeNode l : leftSub) {
                for (TreeNode r : rightSub) {
                    TreeNode t = new TreeNode(i);
                    t.left = l;
                    t.right = r;
                    res.add(t);
                }
            }

        }
        return res;
    }

    // 求树的最大宽度
    public static int getMax(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int max = 0, nowCount = 0, limit = 1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (null != temp.left) {
                queue.add(temp.left);
            }
            if (null != temp.right) {
                queue.add(temp.right);
            }
            nowCount++;
            if (limit == nowCount) {
                max = Math.max(max, nowCount);
                nowCount = 0;
                limit = queue.size();
            }
        }
        return max;
    }

    // leetcode 79 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
    // 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    public boolean isFind = false;

    public boolean exist(char[][] board, String word) {
        if (null == word || 0 == word.length() || null == board || board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                boolean[][] isUsed = new boolean[board.length][board[0].length];
                if (board[i][j] == word.charAt(0)) {
                    isUsed[i][j] = true;
                    dfs(i, j, 1, word, board, isUsed);
                    isUsed[i][j] = false;
                }
            }
        }
        return isFind;
    }

    public void dfs(int i, int j, int index, String word, char[][] board, boolean[][] isUsed) {
        if (index == word.length()) {
            isFind = true;
            return;
        }
        if (i - 1 >= 0 && !isFind && board[i - 1][j] == word.charAt(index) && !isUsed[i - 1][j]) {
            isUsed[i - 1][j] = true;
            dfs(i - 1, j, index + 1, word, board, isUsed);
            isUsed[i - 1][j] = false;
        }
        if (i + 1 < board.length && !isFind && board[i + 1][j] == word.charAt(index) && !isUsed[i + 1][j]) {
            isUsed[i + 1][j] = true;
            dfs(i + 1, j, index + 1, word, board, isUsed);
            isUsed[i + 1][j] = false;
        }
        if (j - 1 >= 0 && !isFind && board[i][j - 1] == word.charAt(index) && !isUsed[i][j - 1]) {
            isUsed[i][j - 1] = true;
            dfs(i, j - 1, index + 1, word, board, isUsed);
            isUsed[i][j - 1] = false;
        }
        if (j + 1 < board[0].length && board[i][j + 1] == word.charAt(index) && !isUsed[i][j + 1]) {
            isUsed[i][j + 1] = true;
            dfs(i, j + 1, index + 1, word, board, isUsed);
            isUsed[i][j + 1] = false;
        }
    }

    public static String Serialize(TreeNode root) {
        if(null == root){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(null == temp){
                sb.append("#,");
                continue;
            }
            sb.append(temp.val).append(",");
            if(null == temp.left && null == temp.right){
                continue;
            }
            stack.push(temp.right);
            stack.push(temp.left);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String SerializeDigui(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(null == root){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val).append(",");
        sb.append(SerializeDigui(root.left));
        sb.append(SerializeDigui(root.right));
        return sb.toString();
    }

}
