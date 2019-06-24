package com.example.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * @author zhangdongrun
 * @date 2018/12/23 下午1:41
 */
public class NowCoder {

    public static void main(String[] args) {
        System.out.println(count(4, 2, 2));

        int[] arr1 = new int[]{5, 6, 1, 4, 3, 7, 88, 99, 22, 2};

        // 位运算
        int[] arr = new int[]{5, 5, 6, 7, 6, 7, 1, 2, 12, 2, 1};
//        int res = 0;
//        for (int i = 0; i < arr.length; ++i) {
//            res = res ^ arr[i];
//        }
//        System.out.println(res);

//        quickS(arr1, 0, arr1.length - 1);
//        for (int i = 0; i < arr1.length; ++i) {
//            System.out.println(arr1[i]);
//        }

//        TreeNode root = new TreeNode(2);
//        TreeNode t1 = new TreeNode(1);
//        TreeNode t2 = new TreeNode(4);
//        TreeNode t3 = new TreeNode(5);
//        TreeNode t5 = new TreeNode(3);
//        root.setLeft(t1);
//        root.setRight(t2);
//        t1.setLeft(t3);
//        t2.setLeft(t5);
//
//        TreeNode root2 = new TreeNode(1);
//        TreeNode root3 = new TreeNode(2);
//        TreeNode root4 = new TreeNode(3);
//        root2.setLeft(root3);
//        root2.setRight(root4);

//        flipMatchVoyageMy(root2, new int[]{1, 3, 2});

//        powerfulIntegers(2, 3, 10);

        LastRemaining_Solution(4, 2);
//        NumberOf1(-7);
//        System.out.println(Integer.toBinaryString(-7));

//        reOrderArray(new int[]{1, 2, 3, 4, 5, 6, 7});

//        int[][] arrEr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}};
//        printMatrix(new int[][]{{1}, {2}, {3}, {4}, {5}});
//        IsPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});

        // 树的结构 https://blog.csdn.net/My_Jobs/article/details/43451187
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

        TreeNode root1 = new TreeNode(10);
        TreeNode rr1 = new TreeNode(5);
        TreeNode rr2 = new TreeNode(4);
        TreeNode rr3 = new TreeNode(7);
        TreeNode rr4 = new TreeNode(12);
        root1.setLeft(rr1);
        root1.setRight(rr4);
        rr1.setLeft(rr2);
        rr1.setRight(rr3);
//        FindPath(root1, 22);
//        MoreThanHalfNum_Solution2(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2});
//        GetLeastNumbers_Solution2(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 8);
//        FindGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5});
//        NumberOf1Between1AndN_Solution(55);
//        GetUglyNumber_Solution(7);
//        System.out.println(find1From3(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4}));
//        System.out.println(match("".toCharArray(), new char[]{'.', '*'}));
//        Deserialize("1,2,4,#,#,5,7,#,#,8,#,#,3,#,6,#,#");
//        System.out.println(longestStr("[[(())]][[()))))"));
//        maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3);
        hasPath("ABCESFCSADEE".toCharArray(), 3, 4, "ABCCED".toCharArray());
//        System.out.println(ReverseSentence("I am a student."));
    }

//    public static List<Integer> powerfulIntegers(int x, int y, int bound) {
//
//        List<Integer> res = new ArrayList<>();
//        for (int i = 0; i <= bound; ++i) {
//            for (int j = 0; j <= bound; ++j) {
//                double sum = (double) Math.pow((double) x, (double) i) + (double) Math.pow((double) y, (double) j);
//                if (sum > bound) {
//                    break;
//                } else {
//                    if (!res.contains((int)sum)) {
//                        res.add((int)sum);
//                    }
//                }
//            }
//        }
//        return res;
//    }

    public static List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> result = new HashSet<>();
        for (int i = 1; i < bound; i *= x) {
            for (int j = 1; i + j <= bound; j *= y) {
                result.add(i + j);
                if (y == 1) {
                    break;
                }
            }
            if (x == 1) {
                break;
            }
        }
        return new ArrayList<>(result);
    }

    public static List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {

        List<Integer> res = new ArrayList<>();
        int count = 0;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode node = root;
        while (null != node || !linkedList.isEmpty()) {
            if (null != node) {
                // 叶子节点什么都不做
                if (node.left == null && node.right == null) {

                } else if (node.left != null && node.right == null) {
                    if (node.val != voyage[count] && node.left.val != voyage[count + 1]) {
                        res.clear();
                        res.add(-1);
                        return res;
                    }
                } else if (node.right != null && node.left == null) {
                    if (node.val != voyage[count] && node.right.val != voyage[count + 1]) {
                        res.clear();
                        res.add(-1);
                        return res;
                    }
                } else if (node.right != null && node.left != null) {
                    if (node.left.val == voyage[count + 2] && node.right.val == voyage[count + 1]) {
                        res.add(node.val);
                    }
                }

                linkedList.push(node);
                node = node.left;
                count++;
            } else {
                TreeNode treeNode = linkedList.pop();
                node = treeNode.right;
            }
        }
        return res;

    }

    static List<Integer> res = new ArrayList<>();

    static int i = 0;

    public static List<Integer> flipMatchVoyageTest(TreeNode root, int[] v) {
        return dfs(root, v) ? res : Arrays.asList(-1);
    }

    public static Boolean dfs(TreeNode node, int[] v) {
        if (node == null) return true;
        if (node.val != v[i]) return false;
        ++i;
        if (node.left != null && node.left.val != v[i]) {
            res.add(node.val);
            return dfs(node.right, v) && dfs(node.left, v);
        }
        return dfs(node.left, v) && dfs(node.right, v);
    }

    public static int count(int T, int S, int q) {
        int count = 0;
        double myQ = q;
        double v = (myQ - 1) / myQ;
//        double pre = S * v;
        int j = 0;
        for (double i = 0; i <= T; ++i) {
            if ((S + j) * v >= T) {
                break;
            }
            if (i >= (S + j) * v) {
                i = 0;
                ++count;
            }
            ++j;
        }
        return count;
    }

    public static int sort(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                --right;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                ++left;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        return left;
    }

    public static void quickS(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = sort(arr, left, right);
        quickS(arr, left, mid - 1);
        quickS(arr, mid + 1, right);
    }

    public static int ccc = 0;

    public static List<Integer> flipMatchVoyageMy(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        if (!digui(res, root, voyage)) {
            res.clear();
            res.add(-1);
            return res;
        }
        return res;
    }

    public static boolean digui(List<Integer> list, TreeNode root, int[] arr) {
        if (null == root) {
            return true;
        }
        if (root.val != arr[ccc++]) {
            return false;
        }
        // 做交换 只关注当前节点和当前节点的左孩子
        if (null != root.left && root.left.val != arr[ccc]) {
            list.add(root.val);
            return digui(list, root.right, arr) && digui(list, root.left, arr);
        }
        return digui(list, root.left, arr) && digui(list, root.right, arr);
    }

    // 和为S的两个数
    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array.length < 2) {
            return new ArrayList<>();
        }
        ArrayList<Integer> res = new ArrayList<>();
        int left = 0, right = array.length - 1;
        while (left < right) {
            if (array[left] + array[right] == sum) {
                res.add(array[left]);
                res.add(array[right]);
                return res;
            } else if (array[left] + array[right] > sum) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    // 和为S的连续正数序列
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int left = 1, right = 2;
        while (right > left) {
            int cur = (right + left) * (right - left + 1) / 2;
            if (sum == cur) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = left; i <= right; ++i) {
                    temp.add(i);
                }
                res.add(temp);
                left++;
            } else if (cur < sum) {
                right++;
            } else {
                left++;
            }
        }
        return res;
    }

    // “student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”
    // 字符串里的单词反转
    public static String ReverseSentence(String str) {
        if (null == str || str.length() < 1) {
            return str;
        }
        String[] arr = str.split(" ");
        if (0 == arr.length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(arr.length - 1);
        for (int i = arr.length - 1; i >= 0; --i) {
            sb.append(" ").append(arr[i]);
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }

    // 循环左移字符串
    public static String LeftRotateString(String str, int n) {
        if (null == str || str.length() == 0) {
            return str;
        }
        int time = n % str.length();
        StringBuilder sb = new StringBuilder(str.substring(time));
        sb.append(str.substring(0, time));
        return sb.toString();
    }

    // 瓜子三面题 约瑟夫环问题
    public static int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            list.add(i);
        }
        // 下一个删除的点 = 上一个删除的点加上 m-1
        // 有可能超出总长度 所以取余
        int pre = 0;
        while (list.size() > 1) {
            int cur = (pre + m - 1) % list.size();
            list.remove(cur);
            pre = cur;
        }
        return list.pop();
    }

    // 猿辅导 三面题
    // 求一堆括号里 最长合法的串
    public static String longestStr(String str) {
        String res = "";
        int max = 0;
        LinkedList<Character> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        stack1.push(str.charAt(0));
        stack2.push(0);
        for (int i = 1; i < str.length(); ++i) {
            Character top = stack1.peek();
            Character now = str.charAt(i);
            if (!stack1.isEmpty() && '[' == top && ']' == now) {
                stack1.pop();
                int pre = stack2.pop();
                if (i - pre + 1 > max) {
                    max = i - pre + 1;
                    res = str.substring(pre, i + 1);
                }
            } else if (!stack1.isEmpty() && '(' == top && ')' == now) {
                stack1.pop();
                int pre = stack2.pop();
                if (i - pre + 1 > max) {
                    max = i - pre + 1;
                    res = str.substring(pre, i + 1);
                }
            } else {
                stack1.push(now);
                stack2.push(i);
            }
        }
        return res;
    }

    // 扑克牌顺子
    public boolean isContinuous2(int[] numbers) {
        if (numbers.length == 0) {
            return false;
        }
        Arrays.sort(numbers);
        int i = 0, count = 0;
        for (; i < numbers.length; ++i) {
            if (numbers[i] == 0) {
                count++;
            } else {
                break;
            }
        }
        int count2 = 0, pre = numbers[i];
        for (int j = i + 1; j < numbers.length; ++j) {
            // 下一个正好比上一个大1
            if (numbers[j] == pre + 1) {
                pre = numbers[j];
            }
            // 数组中有重复数字 一定不是顺子
            else if (numbers[j] == pre) {
                return false;
            } else {
                // 计算如果需要癞子的情况
                count2 = count2 + numbers[j] - pre - 1;
                pre = numbers[j];
            }
        }
        if (count2 > count)
            return false;
        else
            return true;
    }

    // 斐波那契
    public int Fibonacci(int n) {
        int f1 = 1, f2 = 1;
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int f = 0;
        for (int i = 2; i < n; ++i) {
            f = f1 + f2;
            f1 = f2;
            f2 = f;
        }
        return f;
    }

    // 跳台阶
    // 对于本题,前提只有 一次 1阶或者2阶的跳法。
    // a.如果两种跳法，1阶或者2阶，那么假定第一次跳的是一阶，那么剩下的是n-1个台阶，跳法是f(n-1);
    // b.假定第一次跳的是2阶，那么剩下的是n-2个台阶，跳法是f(n-2)
    // 所以说还是斐波那契数列
    public static int JumpFloor(int target) {
        int count = 0, sum = 0;
        return count;
    }

    // 变态跳台阶
    // f(n) = f(n-1)+f(n-2)+...+f(n-(n-1)) + f(n-n) => f(0) + f(1) + f(2) + f(3) + ... + f(n-1)

    // f(n-1) = f(0) + f(1)+f(2)+f(3) + ... + f((n-1)-1) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2)

    // f(n) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2) + f(n-1) = f(n-1) + f(n-1)

    // 可以得出 f(n) = 2*f(n-1)
    public int JumpFloorII(int target) {
        if (target < 0) {
            return -1;
        }
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return 2 * JumpFloorII(target - 1);
        }
    }

    // 矩形覆盖
    public int RectCover(int target) {
        if (target < 1) {
            return 0;
        }
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return RectCover(target - 1) + RectCover(target - 2);
        }
    }

    // 输出一个整数的二进制 1的个数 负数使用补码 补码 = 反码 + 1
    public static int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    // 数组里 奇数在前，偶数在后
    public static void reOrderArray(int[] array) {
        int preOu = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] % 2 == 0) {
                preOu = i;
                break;
            }
        }
        int now = preOu + 1;
        while (now < array.length) {
            if (array[now] % 2 == 1) {
                move(preOu, now, array);
                preOu++;
            }
            now++;
        }
    }

    // 从后往前移动
    private static void move(int left, int right, int[] arr) {
        int temp = arr[right];
        while (right >= left) {
            arr[right] = arr[right - 1];
            right--;
        }
        arr[left] = temp;
        return;
    }

    // 链表中倒数第K个 思路 让一个指针先走K步， 然后另一个指针从头走，两个指针一起走
    public static ListNode FindKthToTail(ListNode head, int k) {
        ListNode node = head;
        for (int i = 0; i < k; ++i) {
            if (null == node) {
                return null;
            }
            node = node.next;
        }
        while (node != null) {
            head = head.next;
            node = node.next;
        }
        return head;
    }

    // 反转链表
    public ListNode ReverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 判断r2是不是r1的子结构树
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (null == root1 || null == root2) {
            return false;
        }
        boolean res = false;
        if (root1.val == root2.val) {
            res = isContain(root1, root2);
        }
        if (!res) {
            res = HasSubtree(root1.left, root2);
        }
        if (!res) {
            res = HasSubtree(root1.right, root2);
        }
        return res;
    }

    // 我日 有可能是子树在 父亲树的中间位置是相同的
    public boolean isContain(TreeNode root1, TreeNode root2) {
        // 这个地方是关键 因为有可能子树已经到底了，但是父亲树还是没有到底
        if (null == root2) {
            return true;
        }
        // 如果父亲已经到底，但是子树没到底
        if (root1 == null && root2 != null) {
            return false;
        }
        // 如果父亲没到底，但是子树到底了
        if (root1 != null && root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isContain(root1.left, root2.left) && isContain(root1.right, root2.right);
    }

    // 镜像二叉树
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode now = stack.pop();
            // 左右孩子不同时为空
            if (!(null == now.left && null == now.right)) {
                TreeNode temp = now.left;
                now.left = now.right;
                now.right = temp;
            }
            if (null != now.left) {
                stack.push(now.left);
            }
            if (null != now.right) {
                stack.push(now.right);
            }
        }
    }

    static int count = 0;

    // 顺时针打印二维数组
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

    // 判断出栈顺序
    // 第一层循环先入栈 第二层循环 判断栈顶元素是否等于当前pop的元素 如果相等则进入循环 相当于循环出栈
    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        LinkedList<Integer> stack = new LinkedList<>();
        if (popA.length == 0 || pushA.length == 0) {
            return false;
        }
        for (int i = 0, j = 0; i < pushA.length; ++i) {
            // 先入栈
            stack.push(pushA[i]);
            // 如果栈顶 == pop第一位 则进行出栈 循环进行
            while (j < popA.length && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        // 如果已经全部入栈了，但是出栈没有出完
        return stack.isEmpty();
    }

    // 二叉树层次遍历
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode top = queue.poll();
            res.add(top.val);
            if (null != top.left) {
                queue.add(top.left);
            }
            if (null != top.right) {
                queue.add(top.right);
            }
        }
        return res;
    }

    // 二叉搜索树的后序遍历序列
    // 后序遍历特点 可以通过最后一个节点（即根节点）,把整个数组拆分成两部分, 一部分比根大，另外一部分比根小
    // 也就是说一半是左子树，一半是右子树 ，子树也满足这个条件，就可以做递归判断
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        return isRight(0, sequence.length - 1, sequence);
    }

    public boolean isRight(int left, int right, int[] arr) {
        if (left >= right) {
            return true;
        }
        int i = left;
        while (i < right) {
            if (arr[i] > arr[right]) {
                break;
            }
            ++i;
        }
        int pos = i;
        while (i < right) {
            if (arr[i] < arr[right]) {
                return false;
            }
            ++i;
        }
        return isRight(left, pos - 1, arr) && isRight(pos + 1, right, arr);
    }

    //输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
    // 我服了，自定义栈太难写了 试试递归8```
    public ArrayList<ArrayList<Integer>> FindPath1(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 初始化信息
        stack.add(root);
        int count = root.val;
        ArrayList<Integer> itemList = new ArrayList<>();
        itemList.add(root.val);
        // 上一个被出栈的节点
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            if (target == count) {
                res.add(new ArrayList<>(itemList));
                pre = stack.pop();
                itemList.remove(itemList.size() - 1);
                count = count - pre.val;
                if (null != temp.right) {
                    stack.push(temp.right);
                    count = count + temp.right.val;
                    itemList.add(temp.right.val);
                }
                if (null != temp.left) {
                    stack.push(temp.left);
                    count = count + temp.left.val;
                    itemList.add(temp.left.val);
                }
            } else if (target > count || ((temp.right == null && pre == temp.left) || (temp.right == pre) || (temp.left == null && temp.right == null))) {
                pre = stack.pop();
                itemList.remove(itemList.size() - 1);
                count = count - pre.val;
            } else {
                if (null != temp.right) {
                    stack.push(temp.right);
                    count = count + temp.right.val;
                    itemList.add(temp.right.val);
                }
                if (null != temp.left) {
                    stack.push(temp.left);
                    count = count + temp.left.val;
                    itemList.add(temp.left.val);
                }
            }
        }
        ArrayList<ArrayList<Integer>> res2 = new ArrayList<>();
        for (int i = res.size() - 1; i >= 0; --i) {
            res2.add(res.get(i));
        }
        return res2;
    }

    //输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        find(root, target);
        return treeRes;
    }

    static ArrayList<ArrayList<Integer>> treeRes = new ArrayList<>();
    static ArrayList<Integer> itemList = new ArrayList<>();
    static int treeCount = 0;

    // 思路是每次递归的时候，节点都加到List里
    // 如果值相等就添加到结果里
    // 如果值小于target就继续递归
    // 在函数最后的时候出list 也就是每次递归返回的时候都做出list操作
    public static void find(TreeNode root, int target) {
        if (null == root) {
            return;
        }
        // 先把当前节点加到List里
        int temp = root.val + treeCount;
        treeCount = temp;
        itemList.add(root.val);

        // 如果是满足条件
        if (temp == target && root.left == null && root.right == null) {
            treeRes.add(new ArrayList<>(itemList));
        }
        // 如果当前值较小则继续递归
        else if (temp < target) {
            find(root.left, target);
            find(root.right, target);
        }
        // 如果当前值较大 则不处理，等到末尾出List
        else {

        }
        // 退出的时候从list移除
        treeCount = treeCount - itemList.get(itemList.size() - 1);
        itemList.remove(itemList.size() - 1);
    }

    // 二叉搜索树转换为 双向链表
    // 本质上利用了中序遍历
    // 思路 先循环访问到最左边的节点，这个节点就是双向链表的起始节点，同时把这些节点都入栈 第一次访问到最左节点就记下来，同时初始化pre
    // 然后就是 让pre的right指向根，根的左孩子指向pre，然后把当前节点变成pre，整个节点再向右子树遍历
    public TreeNode Convert1(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 只是个初始化，以后会被覆盖
        TreeNode pre = root, res = root;
        boolean isFirst = true;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 第一次来的时候temp就是最左节点 要保存下来这个节点当做链表的头，同时也记录下一次遍历时，上次遍历的节点
            TreeNode temp = stack.pop();
            if (isFirst) {
                pre = temp;
                res = temp;
                isFirst = false;
            }
            //
            else {
                pre.right = temp;
                temp.left = pre;
                pre = temp;
            }
            root = temp.right;
        }
        return res;
    }

    // 复杂链表复制
    // 链表里有个随机指向的指针
    // 思路是 现在原链表的每个节点后面都复制一个新节点
    // 再遍历一遍复制原节点的随机指针
    // 最后遍历 把新老节点拆开，拆成两个链表
    public RandomListNode Clone(RandomListNode pHead) {
        if (null == pHead) {
            return null;
        }
        RandomListNode node = pHead;
        // 第一次遍历把链表扩大为两倍，在每个旧节点后面插入一个新节点
        while (node != null) {
            RandomListNode newNode = new RandomListNode(node.label);
            newNode.next = node.next;
            node.next = newNode;
            node = newNode.next;
        }
        node = pHead;
        while (node != null) {
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }
        RandomListNode cur = pHead;
        RandomListNode pCloneHead = cur.next;
        while (null != cur) {
            RandomListNode clone = cur.next;
            cur.next = clone.next;
            clone.next = clone.next == null ? null : clone.next.next;
            cur = cur.next;
        }
        return pCloneHead;
    }

    // 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba
    // todo 这个不对 等大佬讲讲
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (null == str || "".equals(str)) {
            return res;
        }
        char[] nums = str.toCharArray();
        boolean[] usedArr = new boolean[nums.length];
        subSet(nums, new StringBuilder(), res, usedArr);
        return res;
    }

    public void subSet(char[] nums, StringBuilder sb, ArrayList<String> list, boolean[] usedArr) {
        if (sb.toString().length() == nums.length) {
            list.add(new String(sb.toString()));
        }
        for (int i = 0; i < nums.length; ++i) {
            // 去除非同层的重复，也就是同一次内自己不能使用两次
            if (usedArr[i]) {
                continue;
            }
            // 同层的判断 如果前一个为false(这就意味着之前那个相同的元素已经被考虑过了)并且和当前相等 则跳过
            if (i - 1 >= 0 && !usedArr[i - 1] && nums[i - 1] == nums[i]) {
                continue;
            }
            sb.append(nums[i]);
            usedArr[i] = true;
            subSet(nums, sb, list, usedArr);
            sb.deleteCharAt(sb.length() - 1);
            usedArr[i] = false;
        }
    }

    // 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
    // 方法一 使用额外空间做判断
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; ++i) {
            if (map.containsKey(array[i])) {
                int temp = map.get(array[i]) + 1;
                if (temp > array.length / 2) {
                    return array[i];
                }
                map.put(array[i], temp);
            } else {
                map.put(array[i], 1);
            }
        }
        return 0;
    }

    // 方法二 不使用额外空间做判断
    // 对数组同时去掉两个不同的数字，到最后剩下的一个数就是该数字。如果剩下两个，那么这两个也是一样的，就是结果），
    // 在其基础上把最后剩下的一个数字或者两个回到原来数组中，将数组遍历一遍统计一下数字出现次数进行最终判断。
    // 我日，如果要申请N的空间为啥不用 hashMap
    public static int MoreThanHalfNum_Solution2(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        int pre = array[0], right = array.length - 1, prePos = 0, i = prePos + 1;
        while (i <= right) {
//            i = prePos + 1;
            if (pre != array[i]) {
                int temp = array[right];
                array[right] = pre;
                pre = temp;
                array[prePos] = temp;

                int temp2 = array[right - 1];
                array[right - 1] = array[i];
                array[i] = temp2;
                right = right - 2;
                i = prePos + 1;
            } else {
                ++i;
            }
            if (right < 4) {

            }
        }
        int res = array[right - 1], count = 0;
        for (int j = 0; j < array.length; ++i) {
            if (res == array[j]) {
                count++;
                if (count > array.length / 2) {
                    return res;
                }
            }
        }
        return 0;
    }

    // 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4
    // 我日 写反了 写成最大的前K个数了
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k + 1);
        for (int i = 0; i < k; ++i) {
            queue.add(input[i]);
        }
        for (int i = k; i < input.length; ++i) {
            int now = input[i];
            if (now > queue.peek()) {
                queue.add(now);
                queue.poll();
            } else {
                continue;
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

    // 最小的前K个数
    public static ArrayList<Integer> GetLeastNumbers_Solution1(int[] input, int k) {
        if (k > input.length) {
            return new ArrayList<>();
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length; ++i) {
            if (queue.size() < input.length - k) {
                queue.add(input[i]);
            } else {
                int temp = input[i];
                queue.add(temp);
                res.add(queue.poll());
            }
        }
        return res;
    }

    // 最小的前K个数 使用快排思想
    public static ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
        if (k > input.length) {
            return new ArrayList<>();
        }
        ArrayList<Integer> res = new ArrayList<>();
        int left = 0, right = input.length - 1;
        while (left < right) {
            int mid = quickSS(input, left, right);
            if (mid == k) {
                break;
            } else if (mid > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        for (int i = 0; i < k; ++i) {
            res.add(input[i]);
        }
        return res;
    }

    public static int quickSS(int[] input, int left, int right) {
        int temp = input[left];
        while (left < right) {
            while (input[right] >= temp && left < right) {
                right--;
            }
            input[left] = input[right];
            while (input[left] <= temp && left < right) {
                left++;
            }
            input[right] = input[left];
        }
        input[left] = temp;
        return left;
    }

    /*链接：https://www.nowcoder.com/questionTerminal/459bd355da1549fa8a49e350bf3df484

使用动态规划
F（i）：以array[i]为末尾元素的子数组的和的最大值，子数组的元素的相对位置不变
F（i）=max（F（i-1）+array[i] ， array[i]）
res：所有子数组的和的最大值
res=max（res，F（i））

如数组[6, -3, -2, 7, -15, 1, 2, 2]
初始状态：
    F（0）=6
    res=6
i=1：
    F（1）=max（F（0）-3，-3）=max（6-3，-3）=3
    res=max（F（1），res）=max（3，6）=6
i=2：
    F（2）=max（F（1）-2，-2）=max（3-2，-2）=1
    res=max（F（2），res）=max（1，6）=6
i=3：
    F（3）=max（F（2）+7，7）=max（1+7，7）=8
    res=max（F（2），res）=max（8，6）=8
i=4：
    F（4）=max（F（3）-15，-15）=max（8-15，-15）=-7
    res=max（F（4），res）=max（-7，8）=8
以此类推
最终res的值为8 */

    // 数组内 最大连续子序列之和
    // 动态规划
    public static int FindGreatestSumOfSubArray(int[] array) {
        if (null == array || 0 == array.length) {
            return 0;
        }
        int res = array[0];
        int nowSum = array[0];
        for (int i = 1; i < array.length; ++i) {
            // 为什么不和原来的自己比较，为了有机会抛弃掉之前的结果 可以选择一个新的起点
            // 有可能当前这个数很大，比前面加和的最大值还大，这个时候就以这个数作为新的起点
            nowSum = Math.max(nowSum + array[i], array[i]);
            res = Math.max(res, nowSum);
        }
        return res;
    }

    // 从1 到 n 中1出现的次数
    public static int NumberOf1Between1AndN_Solution(int n) {
        int count = 0, res = 0;
        while (n > 0) {
            int temp = n % 10;
            int now = 0;
            if (temp < 1) {
                now = 0;
            } else {
                now = 1;
            }
            res = now + now * count * 10 + res;
            count++;
            n = n / 10;
        }
        return res;
    }

    // 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    public String PrintMinNumber(int[] numbers) {
        String[] stringArr = new String[numbers.length];
        for (int i = 0; i < stringArr.length; ++i) {
            stringArr[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(stringArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringArr.length; ++i) {
            sb.append(stringArr[i]);
        }
        return sb.toString();
    }

    // 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    public int GetUglyNumber_Solution(int index) {
        if (0 == index) {
            return 0;
        }
        int[] arr = new int[index];
        arr[0] = 1;
        int pos2 = 0, pos3 = 0, pos5 = 0, count = 1;
        while (count < index) {
            int temp2 = 2 * arr[pos2];
            int temp3 = 3 * arr[pos3];
            int temp5 = 5 * arr[pos5];
            int next = Math.min(Math.min(temp2, temp3), temp5);
            arr[count] = next;
            count++;
            if (temp2 == next) {
                pos2++;
            }
            if (temp3 == next) {
                pos3++;
            }
            if (temp5 == next) {
                pos5++;
            }
        }
        return arr[index - 1];
    }

    // 完全看不懂啊
    static final int d[] = {2, 3, 5};

    public static int GetUglyNumber_Solution_1(int index) {
        if (index == 0) return 0;
        int a[] = new int[index];
        a[0] = 1;
        int p[] = new int[]{0, 0, 0};
        int num[] = new int[]{2, 3, 5};
        int cur = 1;

        while (cur < index) {
            int m = finMin(num[0], num[1], num[2]);
            if (a[cur - 1] < num[m])
                a[cur++] = num[m];
            p[m] += 1;
            num[m] = a[p[m]] * d[m];
        }
        return a[index - 1];
    }

    private static int finMin(int num2, int num3, int num5) {
        int min = Math.min(num2, Math.min(num3, num5));
        return min == num2 ? 0 : min == num3 ? 1 : 2;
    }

    public int FirstNotRepeatingChar(String str) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            Character temp = str.charAt(i);
            if (!map.containsKey(temp)) {
                map.put(temp, 1);
            } else {
                int tempInt = map.get(temp);
                map.put(temp, ++tempInt);
            }
        }
        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey());
            }
        }

        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (map.get(c) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
    // 如果数组内后面的一个数比前面的一个数大，就是一个逆序对，求这个的总数
    // mid - i + 1
    // 归并的思想来做
    public int InversePairs(int[] array) {
        int[] temp = new int[array.length];
        guibingS(array, 0, array.length - 1, temp);
        return guibingCount;
    }

    int guibingCount = 0;

    public void guibingS(int[] array, int left, int right, int[] temp) {
        // 这个判断千万别忘了写
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        guibingS(array, left, mid, temp);
        guibingS(array, mid + 1, right, temp);
        merge(array, left, mid, right, temp);
    }

    public void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                ++i;
                ++k;
            } else {
                temp[k] = array[j];
                ++j;
                ++k;
                // 这个是精髓所在 核心代码
                // 如果右边的这个数小，那么右边的这个数肯定比左数组后面的数小，个数就是mid- i,在加上当前这个数本身 就是 mid- i + 1
                guibingCount = (guibingCount + mid - i + 1) % 1000000007;
            }
        }
        while (i <= mid) {
            temp[k] = array[i];
            ++k;
            ++i;
        }
        while (j <= right) {
            temp[k] = array[j];
            ++k;
            ++j;
        }
        int m = 0;
        while (left <= right) {
            array[left] = temp[m];
            ++m;
            ++left;
        }
    }

    // 统计一个数字在排序数组中出现的次数
    // 二分
    public int GetNumberOfK(int[] array, int k) {
        int pos = find(array, k, 0, array.length - 1);
        if (-1 == pos) {
            return 0;
        }
        int count = 0, i = pos;
        while (i >= 0) {
            if (array[i] != k) {
                break;
            } else {
                count++;
            }
            --i;
        }
        i = pos + 1;
        while (i < array.length) {
            if (array[i] != k) {
                break;
            } else {
                count++;
            }
            ++i;
        }
        return count;
    }

    public int find(int[] array, int k, int left, int right) {
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (array[mid] == k) {
                return mid;
            } else if (k > array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 获取二叉树的深度
    // 层次遍历
    // 从第一层开始记录这一层的元素数量
    public int TreeDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int deep = 0;
        // 记录当前这一层访问了多少个节点
        int count = 0;
        // 当前这层一共有多少个节点 初始的时候肯定是只有一个节点 就是根节点
        int nowLimit = 1;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            count++;
            if (null != temp.left) {
                queue.add(temp.left);
            }
            if (null != temp.right) {
                queue.add(temp.right);
            }
            // 如果这层已经遍历结束了
            if (count == nowLimit) {
                deep++;
                // 下一层的元素个数 = 当前队列内的元素个数
                nowLimit = queue.size();
                // 当前层数遍历元素数量清零
                count = 0;
            }
        }
        return deep;
    }

    // 输入一棵二叉树，判断该二叉树是否是平衡二叉树
    // 最直接的做法，遍历每个结点，借助一个获取树深度的递归函数，根据该结点的左右子树高度差判断是否平衡，然后递归地对左右子树进行判断。
    // 在判断上层结点的时候，会多次重复遍历下层结点，增加了不必要的开销。
    // todo 还有个更优化的写法
    public boolean IsBalanced_Solution(TreeNode root) {
        if (null == root) {
            return true;
        }
        int maxLeft = getDeep(root.left);
        int maxRight = getDeep(root.right);
        if (Math.abs(maxLeft - maxRight) > 1) {
            return false;
        }
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    public int getDeep(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return 1 + Math.max(getDeep(root.left), getDeep(root.right));
    }

    // 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
    // 实在是太秀了
    // 对整个数组内的元素之间做一次异或，最后得到的数肯定就是两个只出现一次的数字的异或结果
    // 然后找这个数字中找到第一个为1的位,这两个数肯定在这个位上，一个是0，一个是1
    // 那么就可以把数组按照这个位划分为两个数组
    // 这两个数组内分别循环异或，就找到了这两个数字
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (null == array || 0 == array.length) {
            return;
        }
        // 循环异或整个数组
        // temp就是两个不同数字的异或结果
        int temp = 0;
        for (int i = 0; i < array.length; ++i) {
            temp = temp ^ array[i];
        }
        // 找到异或结果的从右往左数，的第一个为1的位数
        int count = 0;
        while (temp != 0) {
            if (1 == (temp & 1)) {
                break;
            }
            temp = temp >>> 1;
            count++;
        }
        int n1 = 0, n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            // 拆分成两个数组
            if (posIsOne(array[i], count)) {
                n1 = n1 ^ array[i];
            } else {
                n2 = n2 ^ array[i];
            }
        }
        num1[0] = n1;
        num2[0] = n2;
    }

    // 对普通元素无符号右移和temp定位的位置一样的，找到这位是0还是1
    public boolean posIsOne(int num, int pos) {
        int temp = num >>> pos;
        int t = temp & 1;
        if (1 == t) {
            return true;
        } else {
            return false;
        }
    }

    // 一个整型数组里除了1个数字之外，其他的数字都出现了3次。请写程序找出这个只出现一次的数字。
    // 可以延伸出现n次的数组里找只出现一次的
    // 申请了32位数组,然后把原数组中的每一个数字,展开成二进制,哪一位为1,那么bits[]那一位就+1. 最终,判断bit中每一位是否是3的倍数(或者是0),如果是,那么我们要找的数字在这一位肯定为0,反之为1
    public static int find1From3(int[] arr) {
        int[] bits = new int[32];
        // 把原数组中的每一个数字,展开成二进制,哪一位为1,那么bits[]那一位就+1
        for (int i = 0; i < arr.length; ++i) {
            int temp = arr[i];
            for (int j = 0; j < 32; ++j) {
                // 获取第几位是1
                int t = (temp >>> j) & 1;
                if (1 == t) {
                    ++bits[j];
                }
            }
        }

        int res = 0;
        for (int i = 0; i < bits.length; ++i) {
            if (bits[i] % 3 != 0) {
                res = res | (1 << i);
            }
        }
        return res;
    }

    // 不用四则运算 算加法
    // 肯定是位运算了
    // a^b 结果就是不考虑进位的结果
    // todo 进位结果
    // 进位的结果 = (a & b) << 1
    public int Add(int num1, int num2) {
        while (0 != num2) {
            // 不考虑进位的情况
            int nojinwei = num1 ^ num2;
            // 计算进位情况
            num2 = (num1 & num2) << 1;
            // 给num1 赋值进位结果，等待下次循环再加一下
            num1 = nojinwei;
        }
        return num1;
    }

    // String->int
    // todo 判断Int是否溢出
    public int StrToInt(String str) {
        if (null == str || 0 == str.length()) {
            return 0;
        }
        char[] arr = str.trim().toCharArray();
        boolean isZhengshu = true;
        int start = 0;
        if (arr[0] == '+') {
            start = 1;
        }
        if (arr[0] == '-') {
            start = 1;
            isZhengshu = false;
        }
        int res = 0;
        for (int i = start; i < arr.length; ++i) {
            char c = arr[i];
            if (c < '0' || c > '9') {
                return 0;
            }
            int temp = c - '0';
            res = res * 10 + temp;
        }
        if (!isZhengshu) {
            res = -res;
        }
        return res;
    }

    // 找出数组中任意一个重复的数字
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (0 == length) {
            duplication[0] = -1;
            return false;
        }
        int[] arr = new int[length];
        for (int i = 0; i < numbers.length; ++i) {
            arr[numbers[i]]++;
        }
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] > 1) {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }

    // 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
    // 就是除去自己 B[i] = A元素全乘在一起 但是排除A[i]
    // b0 = 1 a1 a2 ... an
    // b1 = a0 1 a2 ... an
    // ...
    // bn = a0 a1 a2 ... an-1 1
    // 按照1划分为上半部分和下半部分 b[i] 分成两段来算
    // 先算下半部分
    public int[] multiply(int[] A) {
        int[] res = new int[A.length];
        // 上半部分
        res[0] = 1;
        for (int i = 1; i < res.length; ++i) {
            res[i] = res[i - 1] * A[i - 1];
        }
        // 下半部分
        int temp = 1;
        for (int i = res.length - 2; i >= 0; --i) {
            temp = temp * A[i + 1];
            res[i] = res[i] * temp;
        }
        return res;
    }

    // 实现一个函数用来匹配包括'.'和'*'的正则表达式
    // 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）
    // 还是不对
    public static boolean match(char[] str, char[] pattern) {
        int i = 0, j = 0;
        while (i < pattern.length) {
            if (i + 1 >= pattern.length) {
                break;
            }
            if ('*' == pattern[i + 1]) {
                if (pattern[i] == str[j]) {
                    while (pattern[i] == str[j] && i < pattern.length && j < str.length) {
                        j++;
                    }
                    i = i + 2;
                } else {
                    i = i + 2;
                }
            }
            if ('.' == pattern[i]) {
                i++;
                j++;
                continue;
            }
            if (pattern[i] != str[j]) {
                return false;
            }
            i++;
            j++;

        }
        if (i != pattern.length || j != str.length) {
            return false;
        }
        return true;
    }

    // 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode quick = pHead, slow = pHead;
        while (null != quick && null != slow) {
            slow = slow.next;
            quick = quick.next == null ? null : quick.next.next;
            if (null == slow || null == quick) {
                return null;
            }
            if (slow == quick) {
                quick = pHead;
                while (quick != slow) {
                    quick = quick.next;
                    slow = slow.next;
                }
                return quick;
            }
        }
        return null;
    }

    // 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    public ListNode deleteDuplication(ListNode pHead) {
        if (null == pHead) {
            return pHead;
        }
        ListNode tempHead = new ListNode(-1);
        tempHead.next = pHead;
        ListNode pre = tempHead;
        while (pHead != null) {
            ListNode next = pHead.next;
            if (null == next) {
                break;
            }
            if (pHead.val == next.val) {
                // 一直往后找 找到下一个不一样的 也就是next
                while (null != pHead && next != null && pHead.val == next.val) {
                    pHead = pHead.next;
                    next = next.next;
                }
                pre.next = next;
                pHead = next;
            } else {
                pre = pHead;
                pHead = pHead.next;
            }
        }
        return tempHead.next;
    }

    // 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针（next指针）

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (null == pNode) {
            return pNode;
        }
        // 如果节点右孩子存在，则找右孩子的最左节点
        if (null != pNode.right) {
            TreeLinkNode temp = pNode.right;
            while (temp != null) {
                if (temp.left == null) {
                    return temp;
                }
                temp = temp.left;
            }
        }
        // 当节点不是根节点 一直找到和pNode是左孩子的父亲节点
        while (null != pNode.next) {
            TreeLinkNode temp = pNode.next;
            if (temp.left == pNode) {
                return temp;
            }
            pNode = pNode.next;
        }
        return null;
    }

    // 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    public boolean isSymmetrical(TreeNode pRoot) {
        if (null == pRoot) {
            return true;
        }
        return help(pRoot.left, pRoot.right);
    }

    public boolean help(TreeNode left, TreeNode right) {
        if (null == left && null == right) {
            return true;
        }
        if ((null == left && null != right) || (null != left && null == right)) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        // 镜像是最左==最右 所以应该是left.left == right.right
        // 中间的等于中间的 left.right == right.left
        return help(left.left, right.right) && help(left.right, right.left);
    }

    // 层次遍历 如果要按顺序 就是用堆， 如果要之字形输出 就用两个栈
    // 按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        if (null == pRoot) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        int cengLimit = 1, nowCount = 0;
        boolean isZheng = false;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            tempList.add(temp.val);
            nowCount++;
            if (null != temp.left) {
                queue.add(temp.left);
            }
            if (null != temp.right) {
                queue.add(temp.right);
            }
            if (nowCount == cengLimit) {
                cengLimit = queue.size();
                nowCount = 0;
                // 正序
                if (isZheng) {
                    res.add(new ArrayList<>(tempList));
                }
                // 倒序反转list
                else {
                    ArrayList<Integer> a = new ArrayList<>(tempList);
                    Collections.reverse(a);
                    res.add(a);
                }
                tempList.clear();
                isZheng = !isZheng;
            }
        }
        return res;
    }

    // 按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
    // 通过两个栈做
    public ArrayList<ArrayList<Integer>> PrintV2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        if (null == pRoot) {
            return res;
        }
        // 奇数层
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        // 偶数层
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        stack1.add(pRoot);
        boolean isJi = true;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (isJi) {
                while (!stack1.isEmpty()) {
                    TreeNode temp = stack1.pop();
                    tempList.add(temp.val);
                    if (null != temp.left) {
                        stack2.push(temp.left);
                    }
                    if (null != temp.right) {
                        stack2.push(temp.right);
                    }
                }
                res.add(new ArrayList<>(tempList));
                tempList.clear();
            } else {
                while (!stack2.isEmpty()) {
                    TreeNode temp = stack2.pop();
                    tempList.add(temp.val);
                    if (null != temp.right) {
                        stack1.push(temp.right);
                    }
                    if (null != temp.left) {
                        stack1.push(temp.left);
                    }
                }
                res.add(new ArrayList<>(tempList));
                tempList.clear();
            }
            isJi = !isJi;
        }
        return res;
    }

    // 对于序列化：使用前序遍历，递归的将二叉树的值转化为字符，并且在每次二叉树的结点不为空时，在转化val所得的字符之后添加一个' ， '作为分割。对于空节点则以 '#' 代替。
    public String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (null == root) {
            return "";
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (null == temp) {
                sb.append("#,");
                continue;
            }
            sb.append(temp.val).append(",");
            if (null == temp.left && null == temp.right) {
                sb.append("#,#,");
                continue;
            }
            stack.push(temp.right);
            stack.push(temp.left);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    //对于反序列化：按照前序顺序，递归的使用字符串中的字符创建一个二叉树

    public static int index = 0;

    public static TreeNode Deserialize(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        String[] arr = str.split(",");
        TreeNode root = null;
        if (!"#".equalsIgnoreCase(arr[index])) {
            root = new TreeNode(Integer.valueOf(arr[index]));
            ++index;
            root.left = Deserialize(str);
            ++index;
            root.right = Deserialize(str);
        }
        return root;
    }

    public TreeNode KthNode(TreeNode pRoot, int k) {
        if (null == pRoot) {
            return null;
        }
        int count = 0;
        TreeNode res = null;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode node = pRoot;
        while (null != node || !linkedList.isEmpty()) {
            while (null != node) {
                linkedList.push(node);
                node = node.left;
            }
            TreeNode treeNode = linkedList.pop();
            count++;
            if (count == k) {
                res = treeNode;
                break;
            }
            node = treeNode.right;
        }
        return res;
    }


    public PriorityQueue<Integer> small = null;
    public PriorityQueue<Integer> big = null;

    // 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    // 读取数据流
    public void Insert(Integer num) {
        if (null == small && null == big) {
            init();
        }
        if (!small.isEmpty() && num > small.peek()) {
            small.add(num);
        } else if (!big.isEmpty() && num < big.peek()) {
            big.add(num);
        } else {
            big.add(num);
        }
        int smallSize = small.size(), bigSize = big.size();
        if (bigSize - smallSize > 0) {
            small.add(big.poll());
        } else if (smallSize - bigSize > 0) {
            big.add(small.poll());
        }
    }

    // 获取当前读取数据的中位数
    public Double GetMedian() {
        if (null == small && null == big) {
            init();
        }
        int size = small.size() + big.size();
        if (size % 2 == 1) {
            if (small.size() > big.size()) {
                return Double.valueOf(small.peek());
            } else {
                return Double.valueOf(big.peek());
            }
        } else {
            return (small.peek() + big.peek()) / 2.0;
        }
    }

    public void init() {
        small = new PriorityQueue<>();
        big = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    // 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) {
            return res;
        }
        int max = 0, maxPos = -1;
        for (int i = 0; i < num.length - size + 1; ++i) {
            if (i > maxPos) {
                max = Integer.MIN_VALUE;
                for (int j = i; j < i + size; ++j) {
                    if (num[j] >= max) {
                        max = num[j];
                        maxPos = j;
                    }
                }
            } else {
                if (num[i + size - 1] >= max) {
                    max = num[i + size - 1];
                    maxPos = i;
                }
            }
            res.add(max);
        }
        return res;
    }

    // 用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
    // 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
    // 例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
    // 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
    public static boolean has = false;

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        char[][] arr = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; ++j) {
                // 好蠢啊```这里是乘 列！！！！
                arr[i][j] = matrix[i * cols + j];
            }
        }
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                if (str[0] == arr[i][j]) {
                    boolean[][] used = new boolean[rows][cols];
                    used[i][j] = true;
                    helpHasPath(arr, str, used, 1, i, j);
                    if (has) {
                        return true;
                    }
                }
            }
        }
        return has;
    }

    public static void helpHasPath(char[][] arr, char[] str, boolean[][] used, int index, int i, int j) {
        if (index == str.length) {
            has = true;
            return;
        }
        if (has) return;
        if (!has && i - 1 >= 0 && !used[i - 1][j] && arr[i - 1][j] == str[index]) {
            used[i - 1][j] = true;
            // 要用index + 1 不能用++index 因为下面的方法还要用这个index
            helpHasPath(arr, str, used, index + 1, i - 1, j);
            used[i - 1][j] = false;
        }
        if (!has && i + 1 < arr.length && !used[i + 1][j] && arr[i + 1][j] == str[index]) {
            used[i + 1][j] = true;
            helpHasPath(arr, str, used, index + 1, i + 1, j);
            used[i + 1][j] = false;
        }
        if (!has && j - 1 >= 0 && !used[i][j - 1] && arr[i][j - 1] == str[index]) {
            used[i][j - 1] = true;
            helpHasPath(arr, str, used, index + 1, i, j - 1);
            used[i][j - 1] = false;
        }
        if (!has && j + 1 < arr[0].length && !used[i][j + 1] && arr[i][j + 1] == str[index]) {
            used[i][j + 1] = true;
            helpHasPath(arr, str, used, index + 1, i, j + 1);
            used[i][j + 1] = false;
        }
    }

    public int sum = 1;

    public int movingCount(int threshold, int rows, int cols) {
        if(threshold < 0){
            return 0;
        }
        boolean[][] used = new boolean[rows][cols];
        used[0][0] = true;
        helpMovingCount(used, 0, 0, rows, cols, threshold);
        return sum;
    }

    public void helpMovingCount(boolean[][] used, int i, int j, int rows, int cols, int threshold) {
        if (i - 1 >= 0 && !used[i - 1][j]) {
            if (getSum(i - 1) + getSum(j) <= threshold) {
                used[i -1][j] = true;
                sum++;
                helpMovingCount(used, i -1, j, rows, cols, threshold);
            }
        }
        if (i + 1 < rows && !used[i + 1][j]) {
            if (getSum(i + 1) + getSum(j) <= threshold) {
                used[i+ 1][j] = true;
                sum++;
                helpMovingCount(used, i +1, j, rows, cols, threshold);
            }
        }
        if (j - 1 >= 0 && !used[i][j - 1]) {
            if (getSum(i ) + getSum(j - 1) <= threshold) {
                used[i][j - 1] = true;
                sum++;
                helpMovingCount(used, i, j - 1, rows, cols, threshold);
            }
        }
        if (j + 1 < cols && !used[i][j + 1]) {
            if (getSum(i) + getSum(j + 1) <= threshold) {
                used[i][j + 1] = true;
                sum++;
                helpMovingCount(used, i, j + 1, rows, cols, threshold);
            }
        }
    }

    public int getSum(int i) {
        int sum = 0;
        while (i != 0) {
            sum = sum + i % 10;
            i = i / 10;
        }
        return sum;
    }

}
