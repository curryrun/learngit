package com.example.demo.base;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * @author zhangdongrun
 * @date 2018/12/12 下午5:03
 */
public class TreeNode {

    private TreeNode left;

    private TreeNode right;

    private Integer value;

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
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TreeNode(Integer value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }

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
    public static void level(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while(!queue.isEmpty()){
            TreeNode top = queue.poll();
            System.out.println(top.getValue());
            if(null != top.getLeft()){
                queue.add(top.getLeft());
            }
            if(null != top.getRight()){
                queue.add(top.getRight());
            }
        }
    }

}
