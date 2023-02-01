package com.siyu.messervice.test;

import java.lang.reflect.Method;
import java.util.Arrays;


public class Test  {
    public static void main(String[] args) throws Exception {
        test01();
    }
    /**
     * 获取成员方法
     */
    public static void test01() throws Exception {
        Class clazz = Class.forName("com.siyu.messervice.test.User");
        Method[] methods = clazz.getMethods();
        Arrays.stream(methods).forEach(m->{
            System.out.println(m);
            System.out.println(m.getName());
            System.out.println("--------------------------");
        });
    }
}


