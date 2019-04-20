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

        TreeNode root = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(5);
        TreeNode t5 = new TreeNode(3);
        root.setLeft(t1);
        root.setRight(t2);
        t1.setLeft(t3);
        t2.setLeft(t5);

        TreeNode root2 = new TreeNode(1);
        TreeNode root3 = new TreeNode(2);
        TreeNode root4 = new TreeNode(3);
        root2.setLeft(root3);
        root2.setRight(root4);

        flipMatchVoyageMy(root2, new int[]{1, 3, 2});

        powerfulIntegers(2, 3, 10);

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
        int left =1, right = 2;
        while (right > left){
            int cur = (right + left) * (right - left + 1) / 2;
            if(sum == cur){
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = left; i<= right ; ++i){
                    temp.add(i);
                }
                res.add(temp);
                left++;
            }else if(cur < sum){
                right++;
            }else {
                left++;
            }
        }
        return res;
    }

    public static String ReverseSentence(String str) {
        if(null == str || str.length() < 1){
            return str;
        }
        String[] arr = str.split(" ");
        if(0 == arr.length){
            return str;
        }
        StringBuilder sb = new StringBuilder(arr.length - 1);
        for(int i = arr.length - 2; i >= 0; --i){
            sb.append(" ").append(arr[i]);
        }
        return sb.toString();
    }

    public static String LeftRotateString(String str,int n) {
        if(null == str || str.length() == 0){
            return str;
        }
        int time = n % str.length();
        StringBuilder sb = new StringBuilder(str.substring(time));
        sb.append(str.substring(0, time));
        return sb.toString();
    }

}
