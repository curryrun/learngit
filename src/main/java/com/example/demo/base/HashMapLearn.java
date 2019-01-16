package com.example.demo.base;

import java.util.HashMap;

/**
 * @author zhangdongrun
 * @date 2019/1/11 下午2:32
 */
public class HashMapLearn {

    public static class Person{
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 1);
        map.put("数学", 2);
        map.put("英语", 3);
        map.put("历史", 4);
        map.put("政治", 5);
        map.put("地理", 6);
        map.put("生物", 7);
        map.put("化学", 8);
        map.get("语文");

        String str = new String("1111zzzzz");
        System.out.println(System.identityHashCode(str));
        change(str);
        System.out.println(str);

        Person p1 = new Person("xpp");
        change(p1);
        System.out.println(p1.getName());
    }

    public static void change(String ss){
        System.out.println(System.identityHashCode(ss));
        ss = new String("7777771111");
    }

    public static void change(Person p2){
        System.out.println(p2.getName());
        p2 = new Person("zzz");
        System.out.println(p2.getName());
    }

}
