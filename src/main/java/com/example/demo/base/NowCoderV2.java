package com.example.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @Classname NowCoderV2
 * @Description
 * @Date 2023/9/3 10:22
 * @Creater zhangdongrun_dxm
 **/
public class NowCoderV2 {

    // 剑指offer第一题
    // 链表反向顺序输出，注意push、pop
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (null == listNode) {
            return res;
        }
        LinkedList<Integer> tempStack = new LinkedList<Integer>();
        while (listNode != null) {
            tempStack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!tempStack.isEmpty()) {
            res.add(tempStack.pop());
        }
        return res;
    }

    // 剑指offer第二题
    // 给定二叉树前序、中序遍历，给出二叉树重构，注意使用：Arrays.copyOfRange
    public TreeNode reConstructBinaryTree(int[] preOrder, int[] vinOrder) {
        // write code here
        return getNode(preOrder, vinOrder);
    }

    public TreeNode getNode(int[] preOrder, int[] vinOrder) {
        if (preOrder.length == 0 || vinOrder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[0]);
        int pos = getPos(preOrder[0], vinOrder);
        if (pos < 0) {
            return null;
        }
        root.left = getNode(Arrays.copyOfRange(preOrder, 1, pos + 1),
                Arrays.copyOfRange(vinOrder, 0, pos));
        root.right = getNode(Arrays.copyOfRange(preOrder, pos + 1, preOrder.length),
                Arrays.copyOfRange(vinOrder, pos + 1, vinOrder.length));
        return root;

    }

    public int getPos(int key, int[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // JZ3 数组中重复的数字
    public int duplicate(int[] numbers) {
        // write code here
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; ++i) {
            int now = numbers[i];
            if (tempMap.get(now) != null) {
                return now;
            }
            tempMap.put(numbers[i], i);
        }
        return -1;
    }

    // 更好的写法 数组内数据重新排序,注意前提条件 ：在一个长度为n的数组里的所有数字都在0到n-1的范围内。
    // 数组中某些数字是重复的，但不知道有几个数字是重复的。
    // 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组[2,3,1,0,2,5,3]，那么对应的输出是2或者3。存在不合法的输入的话输出-1
    public int duplicate2(int[] numbers) {
        // write code here
        int i = 0;
        while (i < numbers.length) {
            int now = numbers[i];
            if (i == now) {
                ++i;
                continue;
            } else {
                if (now == numbers[now]) {
                    return now;
                } else {
                    numbers[i] = numbers[now];
                    numbers[now] = now;
                }
            }
        }
        return -1;
    }

    // 确认某个方向 走是小，另一个方向走是大，这样即可根据大小确认前进方向
    public boolean Find(int target, int[][] array) {
        // write code here
        if (array.length == 0 || (array.length == 1 && array[0].length == 0)) {
            return false;
        }
        int i = 0, j = array[0].length - 1;
        while (i < array.length && j > -1) {
            int point = array[i][j];
            if (point == target) {
                return true;
            }
            if (point > target) {
                --j;
            } else {
                ++i;
            }
        }
        return false;
    }

    // JZ8 二叉树的下一个结点 先找根，然后中序遍历 得到list，再遍历list获取下一个
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (null == pNode) {
            return null;
        }
        TreeLinkNode root = pNode;
        while (null != root.next) {
            root = root.next;
        }
        ArrayList<TreeLinkNode> midList = new ArrayList<>();
        zhongxu(root, midList);
        TreeLinkNode res = null;
        for (int i = 0; i < midList.size() - 1; ++i) {
            if (pNode.equals(midList.get(i))) {
                res = midList.get(i + 1);
                break;
            }
        }
        return res;
    }

    private void zhongxu(TreeLinkNode pNode, ArrayList<TreeLinkNode> midList) {
        if (null != pNode.left) {
            zhongxu(pNode.left, midList);
        }
        if (null != pNode) {
            midList.add(pNode);
        }
        if (null != pNode.right) {
            zhongxu(pNode.right, midList);
        }
    }

    // JZ9 用两个栈实现队列
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.add(node);
    }

    // 注意这个地方 stack2里 必须为空的时候，才能重新添加，否则会之前要先出队的，出不来了，顺序有问题
    public int pop() {
        if (stack2.size() == 0) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    // JZ10 斐波那契数列,现在要求输入一个正整数 n ，请你输出斐波那契数列的第 n 项。
    // 最优的是这样，pre, mid, sum; 这样一直动态加
    public int Fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        // write code here
        int pre = 1, mid = 1, sum = 0;
        for (int i = 3; i <= n; ++i) {
            sum = mid + pre;
            pre = mid;
            mid = sum;
        }
        return sum;
    }

    // 空间复杂度高 f(5) = f(4) + f(3), f(4) = f(3) + f(2); 注意这个地方f(3)会重复计算
    private int fibDigui(int count) {
        if (count <= 2) {
            return 1;
        }
        return fibDigui(count - 1) + fibDigui(count - 2);
    }

    // JZ11 旋转数组的最小数字 一直做二分即可 二分拆分的两个数组，比较最后一个元素，谁小，证明最小数字在哪个数字里
    // step 1：双指针指向旋转后数组的首尾，作为区间端点。
    // step 2：若是区间中点值大于区间右界值，则最小的数字一定在中点右边。
    // step 3：若是区间中点值等于区间右界值，则是不容易分辨最小数字在哪半个区间，比如[1,1,1,0,1]，应该逐个缩减右界。
    // step 4：若是区间中点值小于区间右界值，则最小的数字一定在中点左边。
    // step 5：通过调整区间最后即可锁定最小值所在。
    public int minNumberInRotateArray(int[] nums) {
        // write code here
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            }
            // nums[mid] == nums[right]
            else {
                right--;
            }
        }
        return nums[left];
    }

    // JZ12 矩阵中的路径
    public static boolean hasPath(char[][] matrix, String word) {
        // write code here
        char first = word.charAt(0);
        int hangLenth = matrix.length;
        int lieLenth = matrix[0].length;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                // find begin
                if (matrix[i][j] == first) {
                    int posI = i;
                    int posJ = j;
                    int[][] alreadyArr = new int[hangLenth][lieLenth];
                    for (int k = 0; k < alreadyArr.length; ++k) {
                        for (int m = 0; m < alreadyArr[0].length; ++m) {
                            alreadyArr[k][m] = -1;
                        }
                    }
                    if (hasPathDigui(0, word, alreadyArr, posI, posJ, matrix)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasPathDigui(int wordPos, String word, int[][] alreadyArr, int posI, int posJ, char[][] matrix) {
        int hangLenth = matrix.length;
        int lieLenth = matrix[0].length;
        char nextWord = word.charAt(wordPos);
        // 这里要先判断这个点位有没有用过, 即alreadyArr[posI][posJ] == 1
        if (posI >= hangLenth || posI < 0 || posJ >= lieLenth || posJ < 0 ||
                alreadyArr[posI][posJ] == 1 || matrix[posI][posJ] != nextWord) {
            return false;
        }
        if (wordPos == word.length() - 1) {
            return true;
        }
        // 记录一下已经用过了
        alreadyArr[posI][posJ] = 1;
        // 每次都尝试走4个方向 在上面已经判断过这个点位有没有走过
        boolean res = hasPathDigui(wordPos + 1, word, alreadyArr, posI + 1, posJ, matrix)
                || hasPathDigui(wordPos + 1, word, alreadyArr, posI - 1, posJ, matrix)
                || hasPathDigui(wordPos + 1, word, alreadyArr, posI, posJ + 1, matrix)
                || hasPathDigui(wordPos + 1, word, alreadyArr, posI, posJ - 1, matrix);
        // 递归结束需要归位
        alreadyArr[posI][posJ] = -1;
        return res;
    }

    // JZ13 机器人的运动范围
    public int movingCount(int threshold, int rows, int cols) {
        int[][] alreadyArr = new int[rows][cols];
        for (int k = 0; k < alreadyArr.length; ++k) {
            for (int m = 0; m < alreadyArr[0].length; ++m) {
                alreadyArr[k][m] = -1;
            }
        }
        tryMove(alreadyArr, 0, 0, threshold, rows, cols);
        int count = 0;
        for (int k = 0; k < alreadyArr.length; ++k) {
            for (int m = 0; m < alreadyArr[0].length; ++m) {
                if (alreadyArr[k][m] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // 注意这里，不需要往回走，因为只需要记录点位有没有访问，这个递归里的其他人会接着走，所以你自己不需要往回走
    public void tryMove(int[][] alreadyArr, int i, int j, int threshold, int rows,
                        int cols) {
        if (i < 0 || j < 0 || i >= rows || j >= cols || alreadyArr[i][j] == 1) {
            return;
        }
        if (movingCountAdd(i) + movingCountAdd(j) > threshold) {
            return;
        }
        alreadyArr[i][j] = 1;
        // 尝试往右走 如果不通则 return 回来 换个方向走
        tryMove(alreadyArr, i + 1, j, threshold, rows, cols);
        tryMove(alreadyArr, i - 1, j, threshold, rows, cols);
        tryMove(alreadyArr, i, j + 1, threshold, rows, cols);
        tryMove(alreadyArr, i, j - 1, threshold, rows, cols);
    }

    public static int movingCountAdd(int i) {
        int sum = 0;
        while (i >= 10) {
            sum = i % 10 + sum;
            i = i / 10;
        }
        sum = sum + i;
        return sum;
    }

    // JZ14 剪绳子
    // 一旦分出一段长度为1的小段，只会减少总长度，还不能增加乘积，因此长度为2的绳子不分比分开的乘积大，
    // 长度为3的绳子不分比分开的乘积大，长度为4的绳子分成2*2比较大。前面的我们都可以通过这样递推得到，后面的呢？
    // 后面的可以接着递推, 也就是说后面的可以通过前面的最大值推导出来,相当于穷举了
    // 之前的乘积最大值，互相乘一下，那就是新的最大值
    public int cutRope(int n) {
        // write code here
        if (n <= 3) {
            return n - 1;
        }
        // calArr[i]表示长度为i的绳子可以被剪出来的最大乘积
        int[] calArr = new int[n + 1];
        calArr[0] = 0;
        calArr[1] = 1;
        calArr[2] = 2;
        calArr[3] = 3;
        calArr[4] = 4;
        for (int i = 5; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                calArr[i] = Math.max(calArr[i], calArr[i - j] * calArr[j]);
            }
        }
        return calArr[n];
    }

    // JZ15 二进制中1的个数
    // 输入一个整数 n ，输出该数32位二进制表示中1的个数。其中负数用补码表示。
    public int NumberOf1(int n) {
        // write code here
        int count = 0;
        while (0 != n) {
            int res = (1 & n);
            if (1 == res) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    // JZ16 数值的整数次方
    // 负数次幂 = base 分之一 乘N次
    public double Power(double base, int exponent) {
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        double res = 1;
        for (int i = 1; i <= exponent; ++i) {
            res = res * base;
        }
        return res;
    }

    // JZ17 打印从1到最大的n位数
    public int[] printNumbers(int n) {
        // write code here
        String max = "";
        for (int i = 0; i < n; ++i) {
            max = max + "9";
        }
        int maxInt = Integer.valueOf(max);
        int[] resArr = new int[maxInt];
        for (int i = 1; i <= maxInt; ++i) {
            resArr[i - 1] = i;
        }
        return resArr;
    }

    // JZ18 删除链表的节点
    // 可以加入一个头结点,为了避免删除的是头结点
    public ListNode deleteNode(ListNode head, int val) {
        ListNode now = head;
        ListNode pre = null;
        while (now != null) {
            if (now.val == val) {
                if (null == pre) {
                    ListNode temp = now.next;
                    now.next = null;
                    return temp;
                } else {
                    pre.next = now.next;
                    now.next = null;
                    break;
                }
            }
            pre = now;
            now = now.next;
        }
        return head;
    }

    // 可以加入一个头结点,为了避免删除的是头结点
    public ListNode deleteNodeV2(ListNode head, int val) {
        ListNode myFirst = new ListNode(-1);
        myFirst.next = head;
        ListNode now = head;
        ListNode pre = myFirst;
        while (now != null) {
            if (now.val == val) {
                pre.next = now.next;
                now.next = null;
                break;
            }
            pre = now;
            now = now.next;
        }
        return myFirst.next;
    }

    // JZ19 正则表达式匹配
    public boolean match (String str, String pattern) {
        // write code here
        return false;
    }



    public static void main(String[] args) {
        char[][] arr = new char[][]{{'A', 'B', 'C', 'E', 'H', 'J', 'I', 'G'}
                , {'S', 'F', 'C', 'S', 'L', 'O', 'P', 'Q'}
                , {'A', 'D', 'E', 'E', 'M', 'N', 'O', 'E'}
                , {'A', 'D', 'I', 'D', 'E', 'J', 'F', 'M'}
                , {'V', 'C', 'E', 'I', 'F', 'G', 'G', 'S'}};
        String s = "SGGFIECVAASABCEHJIGQEM";
//        System.out.println(hasPath(arr, s));
        System.out.println(movingCountAdd(122));
        int[] calArr = new int[5];
        System.out.println(calArr[4]);
    }


}
