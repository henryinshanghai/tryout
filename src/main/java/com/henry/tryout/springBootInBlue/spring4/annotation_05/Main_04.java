package com.henry.tryout.springBootInBlue.spring4.annotation_05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_04 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig_03.class);

        // 这里配置类上使用的是自定义的 组合注解
        DemoService_02 demoService = context.getBean(DemoService_02.class);

        // 通过调用bean实例的方法 来 验证 bean实例是否被成功创建 - 从而验证自定义组合注解的有效性
        demoService.outputResult();

        context.close();
    }
}
// 结果：方法被成功调用 -> bean实例被成功创建 -> 自定义的组合注解有效