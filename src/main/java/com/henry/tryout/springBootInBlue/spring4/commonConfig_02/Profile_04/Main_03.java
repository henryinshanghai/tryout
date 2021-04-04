package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.Profile_04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_03 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 设置 当前活跃的profile - note：真实场景中，活跃的profile不会通过这种方式来手动设置
        context.getEnvironment().setActiveProfiles("dev");

        // 为 Spring容器设置 配置信息/配置类
        context.register(ProfileConfig_02.class);

        context.refresh(); // 刷新容器

        DemoBean_01 demoBean01 = context.getBean(DemoBean_01.class);
        System.out.println(demoBean01.getContent());

        context.close();
    }
}
