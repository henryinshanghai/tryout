package com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action_01 { // 1 定义注解 - 用于设定拦截规则

    String name(); // 注解里面有一个抽象方法？
}
