package com.henry.tryout.springBootInBlue.spring4.enableXxxWorkPrinciple_06;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.SchedulingConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// 原因在这里：因为一旦添加了这个注解，就会自动导入 Spring预先准备好的配置类型(aka 自动配置)
// 导入的方式1：直接导入 预先定义好的配置类型(SchedulingConfiguration)
@Import({SchedulingConfiguration.class}) // 点进去看一看
@Documented
public @interface MyEnableScheduling_0101 { // 为什么添加这个注解就能够 开启Spring对于某项特性的支持(比如...)
    /*
        对 AspectJ异步代理的支持
        对 异步方法的支持
        对 定时任务/计划任务 的支持

        对 WebMVC 的支持
        对 通过@ConfigurationProperties注解来配置Bean 的支持
        对 Spring Data JPA Repository 的支持
        对 注解式事务 的支持
        对 注解式缓存 的支持
     */

}
