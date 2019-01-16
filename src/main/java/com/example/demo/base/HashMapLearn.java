package com.example.demo.base;

import java.util.HashMap;

/**
 * @author zhangdongrun
 * @date 2019/1/11 下午2:32
 */
public class HashMapLearn {

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

    }

}
