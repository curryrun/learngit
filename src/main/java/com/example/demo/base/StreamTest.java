package com.example.demo.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangdongrun
 * @date 2019/1/10 下午3:11
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(1);
        List<Integer> list2 = list.parallelStream().filter(item -> item.equals(1)).collect(Collectors.toList());
        System.out.println(list2);

        // 1. Individual values
        Stream stream = Stream.of("a", "b", "c");
        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list3 = Arrays.asList(strArray);
        stream = list3.stream();

        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println(concat);
    }

}
