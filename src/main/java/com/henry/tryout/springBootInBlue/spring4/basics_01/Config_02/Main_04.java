package com.henry.tryout.springBootInBlue.spring4.basics_01.Config_02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_04 {
    public static void main(String[] args) {
        // 1 创建 Spring容器对象
        // 手段： 使用 AnnotationConfigApplicationContext 类型提供的构造方法， 传入 配置类的Class对象作为参数
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfig_03.class);

        // 2 获取到 Spring容器中的 指定的bean
        UseFunctionService_02 useFunctionService_02 = context.getBean(UseFunctionService_02.class);

        // 3 调用 bean的实例方法， 完成功能
        System.out.println(useFunctionService_02.sayHello("Java config"));
    }
}
