package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.SpringAware_01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_03 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig_02.class);

        // 获取到 自定义并且注册成为Bean的AwareService_01.java类型的实例
        AwareService_01 awareService01 = context.getBean(AwareService_01.class);

        // 调用 实例方法， 检查结果是否符合预期
        // 预期结果：获取到Bean实例的名称 + 读取到静态资源的内容
        awareService01.outputResult();

        context.close();
    }
}
/*
惊喜：竟然中文没有出现 乱码的情况
 */
