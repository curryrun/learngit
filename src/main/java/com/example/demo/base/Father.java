package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/1/9 上午10:17
 */
public class Father {

    int age= 33;

    public static void funcStatic(){
        System.out.println("i am static father");
    }

//    public Father(int age) {
//        this.age = age;
//    }

    public void func(){
        System.out.println("i am father");
    }

}
