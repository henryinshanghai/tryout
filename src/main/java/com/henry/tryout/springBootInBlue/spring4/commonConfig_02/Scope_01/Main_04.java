package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.Scope_01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_04 {
    public static void main(String[] args) {
        // 1
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig_03.class);

        // 2 连续两次使用getBean()从 Spring容器中获取到bean实例， 并判断得到的实例是不是同一个实例
        DemoSingletonService_01 bean01_of_singleton = context.getBean(DemoSingletonService_01.class);
        DemoSingletonService_01 bean02_of_singleton = context.getBean(DemoSingletonService_01.class);

        DemoPrototypeService_02 bean01_of_prototype = context.getBean(DemoPrototypeService_02.class);
        DemoPrototypeService_02 bean02_of_prototype = context.getBean(DemoPrototypeService_02.class);

        // 3 判断scope的真实表现是否合乎定义
        System.out.println("bean01_of_singleton 与 bean02_of_singleton 是否相等： " +
                bean01_of_singleton.equals(bean02_of_singleton));

        System.out.println("bean01_of_prototype 与 bean02_of_prototype 是否相等： " +
                bean01_of_prototype.equals(bean02_of_prototype));


    }
}
/*
scope的定义 与 含义：
- singleton 表示Spring容器中就只存在当前Bean的一个单一实例
- prototype 表示 每次开发者调用获取bean实例时， Spring容器就会为这一次调用创建该Bean的一个新实例
- request 表示 ...
- session 表示 ...
- globalSession 表示 ...
 */