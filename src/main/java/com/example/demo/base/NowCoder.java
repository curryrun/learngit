package com.example.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

//        LastRemaining_Solution(4, 2);
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
        FindPath(root1, 22);
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

    public static String ReverseSentence(String str) {
        if (null == str || str.length() < 1) {
            return str;
        }
        String[] arr = str.split(" ");
        if (0 == arr.length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(arr.length - 1);
        for (int i = arr.length - 2; i >= 0; --i) {
            sb.append(" ").append(arr[i]);
        }
        return sb.toString();
    }

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

}
