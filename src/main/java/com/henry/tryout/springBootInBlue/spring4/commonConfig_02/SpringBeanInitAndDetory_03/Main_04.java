package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringBeanInitAndDetory_03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_04 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig_03.class);

        // 获取bean实例时，一定会有初始化的过程
        BeanApproachService_01 beanApproachService01 = context.getBean(BeanApproachService_01.class);
        JSR250ApproachService_02 jsr250ApproachService02 = context.getBean(JSR250ApproachService_02.class);

        // 在关闭Spring容器的时候，一定会有销毁bean的过程 销毁bean时不分先后顺序
        context.close();
    }
}
/*
Spring中 Bean的 生命周期；
    初始化 - [生活] - 销毁

两个特殊的阶段：
    初始化之后；
    销毁之前；

管理这两个阶段的手段；
手段1：在定义Bean的时候就定义好 init() 与 destroy()方法，然后 在配置时通过@Bean()的 initMethod 与 destroyMethod 属性来指定
手段2：在定义Bean的时候就直接使用 JSR250规范提供的注解 @PostConstruct、@PreDestroy 来 注解相应的方法
 */