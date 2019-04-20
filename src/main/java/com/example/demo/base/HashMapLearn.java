package com.example.demo.base;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangdongrun
 * @date 2019/1/11 下午2:32
 */
public class HashMapLearn {

    public final int[] arrTest = new int[]{1, 2, 3};

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
        ConcurrentHashMap c = new ConcurrentHashMap();
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

        HashMapLearn learn = new HashMapLearn();
        learn.arrTest[1] = 99;
        for(int i = 0; i< learn.arrTest.length; ++i){
            System.out.println(learn.arrTest[i]);
        }

        int[] arr2 = new int[]{5, 5, 4};
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.get();
//        threadLocal.set();
        JSON.toJSONString(p1);
        System.out.println(testInt());
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

    public static int testInt(){
        int i = 1;
        try {
            i = 2;
            return i;
        }catch (Throwable th){
            return i;
        }finally {
            i = 3;
        }
    }

    private void test(){
        try {
            for (;;) {
                if (retries++ == RETRIES_BEFORE_LOCK) {
                    for (int j = 0; j < segments.length; ++j)
                        ensureSegment(j).lock(); // force creation
                }
                sum = 0L;
                size = 0;
                overflow = false;
                for (int j = 0; j < segments.length; ++j) {
                    Segment<K,V> seg = segmentAt(segments, j);
                    if (seg != null) {
                        sum += seg.modCount;
                        int c = seg.count;
                        if (c < 0 || (size += c) < 0)
                            overflow = true;
                    }
                }
                if (sum == last)
                    break;
                last = sum;
            }
        } finally {
            if (retries > RETRIES_BEFORE_LOCK) {
                for (int j = 0; j < segments.length; ++j)
                    segmentAt(segments, j).unlock();
            }
        }
    }

}
