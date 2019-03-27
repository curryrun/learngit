package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author zhangdongrun
 * @date 2019/3/10 下午4:39
 */
public class Ti {
    public static void main(String[] args) {
//        System.out.println('y' < 'a');
//        System.out.println(get("xaBXy"));
//        System.out.println(removeDuplicateLetters("xaBXy"));
        int[][] queries = new int[][]{{6, 3}, {1, 1}, {3, 5}, {4, 8}, {6, 4}, {10, 3}, {11, 2}};
//        System.out.println(maxSum(queries));
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[][] arr = new int[length][2];
        int d = sc.nextInt();
        for(int i = 0; i< length; ++i){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        int i = 0;
        int max = 0;
        int addValue;
//        int num = arr[0][0], d = arr[0][1];
        for(int j = 1; j < arr.length; j++){
            if(Math.abs(arr[i][0] - arr[j][0]) > d) {
                addValue = arr[i][1] + arr[j][1];
                if (addValue > max)
                    max = addValue;
                if (arr[j][1] - arr[i][1] > 0)
                    i = j;//记录更大的arr[i]
            }
        }
        System.out.println(max);
    }

    public static String get(String input) {
        HashMap<String, Integer> map = new HashMap<>();
        int length = input.length();
        for (int i = 0; i < length; ++i) {
            String now = String.valueOf(input.charAt(i)).toLowerCase();
            if (!map.containsKey(now)) {
                map.put(now, i);
            } else {
                int prePos = map.get(now);
                if (length == i + 1) {
                    input = removeCharAt(input, i);
                    length--;
                } else {
                    char preC = String.valueOf(input.charAt(prePos + 1)).toLowerCase().charAt(0);
                    char nowC = String.valueOf(input.charAt(i + 1)).toLowerCase().charAt(0);
                    if (nowC < preC) {
                        input = removeCharAt(input, i);
                        length--;
                    } else {
                        input = removeCharAt(input, prePos);
                        length--;
                    }
                }
            }
        }
        return String.valueOf(input.charAt(0));
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 0)
            return "";
        else {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++)
                count[String.valueOf(s.charAt(i)).toLowerCase().charAt(0) - 'a']++;
            int pos = 0;
            for (int i = 0; i < s.length(); i++) {
                if (String.valueOf(s.charAt(i)).toLowerCase().charAt(0) < s.charAt(pos))
                    pos = i;
                count[String.valueOf(s.charAt(i)).toLowerCase().charAt(0) - 'a']--;
                if (count[String.valueOf(s.charAt(i)).toLowerCase().charAt(0) - 'a'] == 0)
                    break;
            }
            System.out.println(s.charAt(pos)+"     "+s.substring(pos+1).replace(""+s.charAt(pos),""));
            String res = s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replace("" + s.charAt(pos), ""));
            System.out.println(res);
            return String.valueOf(res.charAt(0));
        }
    }

//    public static int getMax(int[][] arr){
//        int max = 0;
//        int num = arr[0][0], d = arr[0][1];
//        for(int i = 1; i< arr.length; ++i){
//            for(int j = 1; j< arr.length; ++j){
//                if(i == j){
//                    continue;
//                }
//                if(Math.abs(arr[i][0] - arr[j][0]) > d){
//                    int temp = arr[i][1] + arr[j][1];
//                    if(temp > max){
//                        max = temp;
//                    }
//                }
//            }
//        }
//        return max;
//    }

    public static int getMax(int[][] arr){
        int i = 1;
        int max = 0;
        int addValue;
        int num = arr[0][0], d = arr[0][1];
        for(int j = 1; j < arr.length; j++){
            if(Math.abs(arr[i][0] - arr[j][0]) > d) {
                addValue = arr[i][1] + arr[j][1];
                if (addValue > max)
                    max = addValue;
            }
            if (arr[j][1] - arr[i][1] > 0)
                i = j;//记录更大的arr[i]
        }
        return max;
    }
}
