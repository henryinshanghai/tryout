package com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_06 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig_05.class);

        DemoAnnotationService_02 demoAnnotationService = context.getBean(DemoAnnotationService_02.class);

        DemoMethodService_03 demoMethodService = context.getBean(DemoMethodService_03.class);

        System.out.println("=========== what will happen in here? =========");
        demoAnnotationService.add(); // 当add()方法被执行后(joinPoint对象所表示的时机)，在after()方法中定义的行为就会被执行

        System.out.println("************ what will happen in here? **********");
        demoMethodService.add(); // 当add()方法被执行前(joinPoint对象所表示的时机)，在before()方法中定义的行为就会被执行
    }
}
/*
AOP：在不改动已存在的代码结构的前提下，为代码添加新的行为；
核心：when - where - what；
SOP：
    1 定义 where - 02、03；
    2 定义 when & what - 04；
    3 对类型进行配置 - 05；
    4 运行查看结果 - 06

声明切入点的两种方式：
    - 1 使用注解的语法  @annotation(xxx)；
    - 2 使用 表达式的语法  execution(xxx);

具体语法不做深究
 */
