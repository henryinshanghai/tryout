package com.henry.tryout.springBootInBlue.spring4.annotation_05;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration // 把 元注解@Configuration 组合到 自定义的注解中
@ComponentScan // 把 元注解@ComponentScan 组合到 自定义的注解中
public @interface WiselyConfiguration_01 {

    // 自定义注解里的???
    // 看不懂这里的语法     作用：覆盖value参数
    String[] value() default{};
}
