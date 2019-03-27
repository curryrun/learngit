package com.example.demo.base;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhangdongrun
 * @date 2019/3/19 上午10:27
 */
public class Toutiao {

    private int a;

    private boolean b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public static int reverseBits(int n) {
        int res = 0;
        while (n != 0){
            // 如果是负数，取余也是负数，所以不用特殊处理
            int temp = n % 10;
            res = res * 10 + temp;
            n = n / 10;
        }
        return res;
    }

    public static String getByte(int n){
        StringBuilder sb = new StringBuilder();
        while (n != 0){
            int temp = n % 2;
            sb.append(temp);
            n = n / 2;
        }
        return sb.reverse().toString();
    }

    public static void copy(Object source, Object target) throws Exception{
        Class<?> sourceClazz = source.getClass();
        Class<?> targetClazz = target.getClass();
        Field[] sourceFields = sourceClazz.getDeclaredFields();
        Field[] targetFields = targetClazz.getDeclaredFields();
        for(Field sourceField: sourceFields){
            for(Field targetField: targetFields){
                if(!sourceField.getName().equalsIgnoreCase(targetField.getName())){
                    continue;
                }
                String getMethodName = "get" + sourceField.getName().substring(0, 1).toUpperCase() + sourceField.getName().substring(1);
                String setMethodName = "set" + targetField.getName().substring(0, 1).toUpperCase() + targetField.getName().substring(1);
                String sourceType = sourceField.getGenericType().toString();
                String targetType = targetField.getGenericType().toString();
                if("class java.lang.Boolean".equals(sourceType) && sourceType.equals(targetType)){
                    getMethodName = "is" + sourceField.getName().substring(0, 1).toUpperCase() + sourceField.getName().substring(1);
                    setMethodName = "set" + targetField.getName().substring(0, 1).toUpperCase() + targetField.getName().substring(1);
                }
                Method getMethod = sourceClazz.getMethod(getMethodName);
                Method setMethod = targetClazz.getMethod(setMethodName);
                Object value = getMethod.invoke(source);
                setMethod.invoke(target, value);
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        int a = -136792;
//        System.out.println(reverseBits(a));
//        System.out.println(getByte(-8));
//        System.out.println(Integer.toBinaryString(-8));
//        System.out.println(a<<32);
        Toutiao t = new Toutiao();
        t.setA(1);
        t.setB(true);
        BeanCopyTarget target = new BeanCopyTarget();
        copy(t, target);
        System.out.println(JSON.toJSONString(target));
    }
}
