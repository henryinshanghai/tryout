package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringBeanInitAndDetory_03;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JSR250ApproachService_02 {

    @PostConstruct // 在构造器方法执行完成之后执行
    public void init() {
        System.out.println("jsr250-init-method");
    }

    public JSR250ApproachService_02() {
        super();
        System.out.println("初始化构造函数-JSR250ApproachService");
    }

    @PreDestroy // 在Bean实例被销毁之前执行
    public void destroy() {
        System.out.println("JSR250-destroy-method");
    }
}
