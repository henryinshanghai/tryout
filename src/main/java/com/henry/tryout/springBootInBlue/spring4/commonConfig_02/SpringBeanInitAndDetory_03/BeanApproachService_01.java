package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringBeanInitAndDetory_03;

// Spring 为 开发者对Bean的生命周期进行操作 提供支持的方式1 - @Bean的 initMethod 与 destroyMethod方法
public class BeanApproachService_01 {

    public void init() {
        System.out.println("@Bean-init-method");
    }

    public BeanApproachService_01() {
        super();
        System.out.println("用于初始化的构造函数 - BeanApproachService");
    }

    public void destroy() {
        System.out.println("@Bean-destroy-method");
    }
}
