package com.example.demo.base;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    public void pushByDoubleQueue(int node) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.add(node);
    }

    // 注意这个地方 stack2里 必须为空的时候，才能重新添加，否则会之前要先出队的，出不来了，顺序有问题
    public int popByDoubleQueue() {
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

    // TODO JZ19 正则表达式匹配
    public boolean match(String str, String pattern) {
        // write code here
        int n = str.length();
        int m = pattern.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 空正
                if (j == 0) {
                    if (i == 0) {
                        f[i][j] = true;
                    }
                }
                // 非空正则
                else {
                    // 分为两种情况 * 和 非*
                    if (pattern.charAt(j - 1) != '*') {
                        if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        //看
                        if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }

    // JZ21 调整数组顺序使奇数位于偶数前面(一) 空间复杂度o(n)
    public int[] reOrderArray(int[] array) {
        // write code here
        int jiCount = 0, ouCount = 0;
        for (int i = 0; i < array.length; ++i) {
            if (isJi(array[i])) {
                jiCount++;
            } else {
                ouCount++;
            }
        }
        int[] res = new int[array.length];
        int jiBegin = 0, ouBegin = jiCount;
        for (int i = 0; i < array.length; ++i) {
            if (isJi(array[i])) {
                res[jiBegin] = array[i];
                jiBegin++;
            } else {
                res[ouBegin] = array[i];
                ouBegin++;
            }
        }
        return res;
    }

    private static boolean isJi(int n) {
        if (n % 2 == 1) {
            return true;
        }
        return false;
    }

    // 还有一个空间复杂度o(1)的 也就是每次看到偶数 这个时间复杂度o(n3)
    public int[] reOrderArrayV2(int[] array) {
        // write code here
        for (int i = 0; i < array.length; ++i) {
            // 前面是奇数 跳过即可 不需要做调整
            if (isJi(array[i])) {
                continue;
            }
            // i就是找到的第一个偶数
            for (int j = 1; i + j < array.length; ++j) {
                // 后面是偶数，一直找到到下一个奇数
                if (!isJi(array[i + j])) {
                    continue;
                }
                // 找到奇数，则前移
                if (isJi(array[i + j])) {
                    // 奇数插入到偶数为止
                    int nowJPos = i + j;
                    int temp = array[nowJPos];
                    // 偶数位置整体后移一位
                    while (nowJPos > i) {
                        array[nowJPos] = array[nowJPos - 1];
                        --nowJPos;
                    }
                    array[i] = temp;
                    break;
                }
            }
        }
        return array;
    }

    // 时间复杂度o(n2) 和上面的改动是 删除了一个循环 把找偶数的i，和j的循环放在了一起
    public static int[] reOrderArrayV3(int[] array) {
        // write code here
        // i就是找到的第一个偶数 可以套在下面的循环里一起找
        int i = 0;
        for (int j = 0; j < array.length; ++j) {
            // 开头碰到奇数 i、j一起后移
            if (i == j && isJi(array[i])) {
                i++;
                continue;
            }
            // 后面是偶数，一直找到到下一个奇数
            if (!isJi(array[j])) {
                continue;
            }
            // 找到奇数，则前移
            if (isJi(array[j])) {
                // 奇数插入到偶数为止
                int nowJPos = j;
                int temp = array[nowJPos];
                // 偶数位置整体后移一位
                while (nowJPos > i) {
                    array[nowJPos] = array[nowJPos - 1];
                    --nowJPos;
                }
                array[i] = temp;
                ++i;
            }
        }
        return array;
    }

    // JZ22 链表中倒数最后k个结点 先走一个 然后两个指针一起走
    public ListNode FindKthToTail(ListNode pHead, int k) {
        // write code here
        ListNode qian = pHead;
        for (int i = 0; i < k; ++i) {
            if (null == pHead) {
                return null;
            }
            pHead = pHead.next;
        }
        ListNode hou = pHead;
        while (hou != null) {
            hou = hou.next;
            qian = qian.next;
        }
        return qian;
    }

    // JZ23 链表中环的入口结点
    // 快慢节点相遇之后，快节点回到起点，之后用相同速度走，相遇的时候就是环的入口，这里可以用数学公式证明
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (null == pHead) {
            return null;
        }
        // 自己成环
        if (pHead.next == pHead) {
            return pHead;
        }
        ListNode temp = pHead, fast = pHead, slow = pHead;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 上面循环是因为fast到null的时候 则证明两个指针没有相遇 也就是没有环
        if (null == fast || null == fast.next) {
            return null;
        }
        fast = temp;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    // JZ24 反转链表
    public ListNode ReverseList (ListNode head) {
        ListNode pre = null, after = head;
        while (after != null) {
            ListNode temp = after.next;
            after.next = pre;
            pre = after;
            after = temp;
        }
        return pre;
    }

    // JZ25 合并两个排序的链表
    public static ListNode Merge (ListNode pHead1, ListNode pHead2) {
        // write code here
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (null != pHead1 || null != pHead2) {
            if (null == pHead1 && null != pHead2) {
                head.next = pHead2;
                pHead2 = pHead2.next;
            }
            if (null == pHead2 && null != pHead1) {
                head.next = pHead1;
                pHead1 = pHead1.next;
            }
            if (null != pHead1 && null != pHead2) {
                if (pHead1.val > pHead2.val) {
                    head.next = pHead2;
                    pHead2 = pHead2.next;
                } else {
                    head.next = pHead1;
                    pHead1 = pHead1.next;
                }
            }
            head = head.next;
        }
        return temp.next;
    }

    // JZ26 树的子结构
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (null == root1 || null == root2) {
            return false;
        }
        boolean res = false;
        if (root1.val == root2.val) {
            res = deepHasSubtree(root1, root2);
        }
        // 没找到 则尝试找左子树
        if (!res) {
            res = HasSubtree(root1.left, root2);
        }
        // 没找到 再尝试找右子树
        if (!res) {
            res = HasSubtree(root1.right, root2);
        }
        return res;
    }

    public boolean deepHasSubtree(TreeNode root1, TreeNode root2) {
        if (null == root2) {
            return true;
        }
        // 相当于第一棵树已经遍历完了 但是第二棵树还没完事
        if (null == root1 && null != root2) {
            return false;
        }
        // 只要值不等就返回
        if (root1.val != root2.val) {
            return false;
        }
        else {
            return deepHasSubtree(root1.left, root2.left) &&
                    deepHasSubtree(root1.right, root2.right);
        }
    }

    // JZ27 二叉树的镜像
    public TreeNode Mirror(TreeNode pRoot) {
        // write code here
        TreeNode temp = pRoot;
        deepMirror(temp);
        return pRoot;
    }

    public void deepMirror(TreeNode pRoot) {
        if (null == pRoot) {
            return;
        }
        TreeNode left = pRoot.getLeft();
        TreeNode right = pRoot.getRight();
        pRoot.setLeft(right);
        pRoot.setRight(left);
        deepMirror(left);
        deepMirror(right);
    }

    // JZ28 对称的二叉树
    // 采用层次遍历的思想，遍历根节点下面的左右两棵子树
    public static boolean isSymmetrical(TreeNode pRoot) {
        // write code here
        if (null == pRoot) {
            return true;
        }
        LinkedList<TreeNode> queueLeft = new LinkedList<>();
        queueLeft.add(pRoot.left);
        LinkedList<TreeNode> queueRight = new LinkedList<>();
        queueRight.add(pRoot.right);
        while (!queueLeft.isEmpty() && !queueRight.isEmpty()) {
            TreeNode leftTemp = queueLeft.poll();
            TreeNode rightTemp = queueRight.poll();
            if (null == leftTemp && null == rightTemp) {
                continue;
            }
            // 左右不均衡
            if ((null == leftTemp && rightTemp != null) || (null == rightTemp && leftTemp != null)) {
                return false;
            }
//            if (leftTemp.val != rightTemp.val) {
            if (!leftTemp.val.equals(rightTemp.val)) {
                return false;
            }
            queueLeft.add(leftTemp.left);
            queueLeft.add(leftTemp.right);
            // 另一棵树反向遍历
            queueRight.add(rightTemp.right);
            queueRight.add(rightTemp.left);
        }
        return true;
    }

    // JZ29 顺时针打印矩阵
    static int count = 0;
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int size = matrix.length * matrix[0].length;
        myPrint(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, res, size);
        return res;
    }

    // 每次都要判断是否已经遍历所有元素！
    private static void myPrint(int[][] arr, int startHang, int startLie, int hangRight, int lieRight, ArrayList<Integer> res, int size) {
        if (count >= size) {
            return;
        }
        for (int i = startLie; i <= lieRight && count < size; ++i) {
            res.add(arr[startHang][i]);
            count++;
        }
        for (int i = startHang + 1; i <= hangRight && count < size; ++i) {
            res.add(arr[i][lieRight]);
            count++;
        }
        for (int i = lieRight - 1; i >= startLie && count < size; --i) {
            res.add(arr[hangRight][i]);
            count++;
        }
        for (int i = hangRight - 1; i > startHang && count < size; --i) {
            res.add(arr[i][startLie]);
            count++;
        }
        myPrint(arr, startHang + 1, startLie + 1, hangRight - 1, lieRight - 1, res, size);
    }

    // JZ30 包含min函数的栈 用一个min stack 做快照
    LinkedList<Integer> commonStack = new LinkedList<Integer>();
    LinkedList<Integer> minStack = new LinkedList<Integer>();
    public void push(int node) {
        if (commonStack.size() == 0) {
            commonStack.push(node);
            minStack.push(node);
            return;
        }
        commonStack.push(node);
        int nowMin = minStack.peek();
        if (node < nowMin) {
            minStack.push(node);
        } else {
            minStack.push(nowMin);
        }
    }

    public void pop() {
        commonStack.pop();
        minStack.pop();
    }

    public int top() {
        return commonStack.peek();
    }

    public int min() {
        return minStack.peek();
    }

    // JZ31 栈的压入、弹出序列
    // 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
    // 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
    public static boolean IsPopOrder (int[] pushV, int[] popV) {
        LinkedList<Integer> pushStack = new LinkedList<>();
        int popPos = 0;
        for (int i = 0; i< pushV.length; ++i) {
            pushStack.push(pushV[i]);
            int peek = pushStack.peek();
            // 不相等就接着塞
            if (peek != popV[popPos]) {
                continue;
            }
            // 相等就尝试出栈
            while (!pushStack.isEmpty() && popPos < popV.length) {
                peek = pushStack.peek();
                if (peek == popV[popPos]) {
                    pushStack.pop();
                    ++popPos;
                }
                // 不等的话停止 继续尝试入栈 再试一下
                else {
                    break;
                }
            }
        }
        if (!pushStack.isEmpty()) {
            return false;
        }
        return true;
    }

    // JZ32 从上往下打印二叉树 就是二叉树层次遍历
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            res.add(temp.val);
            if (null != temp.left) {
                queue.add(temp.left);
            }
            if (null != temp.right) {
                queue.add(temp.right);
            }
        }
        return res;
    }

    // JZ33 判定是否为二叉搜索树的后序遍历序列 左子树小 < 根 < 右子树
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        return VerifySquenceOfBSTDeep(0, sequence.length - 1, sequence);
    }

    public boolean VerifySquenceOfBSTDeep(int begin, int end, int [] sequence) {
        if (begin > end) {
            return true;
        }
        int root = sequence[end];
        // 找到第一个比root大的节点 就是右子树
        int split = end;
        for (int i = begin; i < end; ++i) {
            if (sequence[i] > root) {
                split = i;
                break;
            }
        }
        // 判定右子树中是否有比root小的
        for (int i = split + 1; i< end; ++i) {
            if (sequence[i] <= root) {
                return false;
            }
        }
        return VerifySquenceOfBSTDeep(begin, split - 1, sequence) && VerifySquenceOfBSTDeep(split, end - 1, sequence);
    }

    // JZ34 二叉树中和为某一值的路径(二)
    public ArrayList<ArrayList<Integer>> FindPath (TreeNode root, int target) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        if (root == null) {
            return res;
        }
        FindPathDeep(root, 0, target, res, temp);
        return res;
    }

    public void FindPathDeep(TreeNode root, int nowNumber, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp) {
        // 找到叶子结点
        if (root.left == null && root.right == null) {
            if (root.val + nowNumber == target) {
                temp.add(root.val);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
            }
            return;
        }
        temp.add(root.val);
        if (null != root.left) {
            FindPathDeep(root.left, nowNumber + root.val, target, res, temp);
        }
        if (null != root.right) {
            FindPathDeep(root.right, nowNumber + root.val, target, res, temp);
        }
        temp.remove(temp.size() - 1);
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{{'A', 'B', 'C', 'E', 'H', 'J', 'I', 'G'}
                , {'S', 'F', 'C', 'S', 'L', 'O', 'P', 'Q'}
                , {'A', 'D', 'E', 'E', 'M', 'N', 'O', 'E'}
                , {'A', 'D', 'I', 'D', 'E', 'J', 'F', 'M'}
                , {'V', 'C', 'E', 'I', 'F', 'G', 'G', 'S'}};
        String s = "SGGFIECVAASABCEHJIGQEM";
//        System.out.println(hasPath(arr, s));
//        System.out.println(movingCountAdd(122));
        int[] calArr = new int[5];
        System.out.println(calArr[4]);

        int[] arrInt = new int[]{1, 2, 2, 3, 2, 2, 5};
//        System.out.println(reOrderArrayV3(arrInt));

        for (int i = 0; i < arrInt.length; ++i) {
            System.out.println(arrInt[i]);
        }
//        Merge(new ListNode(1), new ListNode(2));

        TreeNode root = new TreeNode(1);
        TreeNode level_1_l = new TreeNode(2);
        TreeNode level_1_r = new TreeNode(2);
        TreeNode level_2_r = new TreeNode(3);
        TreeNode level_2_l = new TreeNode(3);
        root.left = level_1_l;
        root.right = level_1_r;
        level_1_l.right = level_2_r;
        level_1_r.left = level_2_l;
//        isSymmetrical(root);

        int[][] intArr = new int[][]{{1,2,3,4,5}};
//        printMatrix(intArr);

        IsPopOrder(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1});
    }


}
