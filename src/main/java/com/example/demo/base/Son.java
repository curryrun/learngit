package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/1/9 上午10:17
 */
public class Son extends Father {

    public static void funcStatic(){
        System.out.println("i am static son");
    }

    @Override
    public void func() {
        System.out.println("i am son");
    }

    public static void main1(String[] args) {

        StringBuilder sb = new StringBuilder();
        sb.append((String)null);
        System.out.println(sb.toString());

        String s = null;
        String s1 = "";
        s1.hashCode();
        System.out.println(s == s1);
        System.out.println(s instanceof String);

        // j=j++, 先暂存了一个原始j值0，然后j++，j变成1，最后j=赋值了一个j的原始值
        int j = 0;
        for(int i = 0; i< 100; ++i){
            j = j++;
        }
        System.out.println(j);
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println();
        son.func();
    }
}
