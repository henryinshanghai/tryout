package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringBeanInitAndDetory_03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringBeanInitAndDetory_03")
public class PrePostConfig_03 {

    // 方式1 ： @Bean的属性xxx、ooo
    @Bean(initMethod = "init", destroyMethod = "destroy")
    BeanApproachService_01 beanApproachService01() {
        return new BeanApproachService_01();
    }

    // 方式2 : 在注入的Bean的类型定义中 自定义相关的生命周期操作
    @Bean
    JSR250ApproachService_02 jsr250ApproachService02() {
        return new JSR250ApproachService_02();
    }

}
