package com.henry.tryout.springBootInBlue.spring4.basics_01.DI_01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_04 {
    public static void main(String[] args) {

        // 创建 Spring容器对象
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DiConfig_03.class);


        // 从 Spring容器 中 获取到 指定的对象 - 使用对象所属的类型的Class对象来获取
        UseFunctionService_02 useFunctionService = context.getBean(UseFunctionService_02.class);

        // 调用获取到的对象上的方法
        System.out.println(useFunctionService.SayHello("world!"));

        // 关闭Spring容器对象
        context.close();
    }
}
