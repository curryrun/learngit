package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/1/27 上午10:21
 */
public class LeetCodeWeek121 {

    public static void main(String[] args) {
        System.out.println(strWithout3a3b(1, 3));
    }

    public static String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();
        int start = A, end = B;
        String startStr = "a", endStr = "b";
        if(A< B){
            start =B;
            end = A;
            startStr = "b";
            endStr = "a";
        }
        int countB = 0;
        for (int i = 0; i < start; ++i) {
            if ((i + 1) % 3 == 0) {
                if(end - (countB + 2) >= 0){
                    sb.append(endStr).append(endStr);
                    ++countB;
                    ++countB;
                }else {
                    sb.append(endStr);
                    ++countB;
                }
            }
            sb.append(startStr);
        }
        for (int i = 0; i < end - countB; ++i) {
            sb.append(endStr);
        }
        return sb.toString();
    }

}
