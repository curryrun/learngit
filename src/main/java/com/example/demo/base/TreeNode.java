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
        level(root);
        findRoot(root, 10);

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
}