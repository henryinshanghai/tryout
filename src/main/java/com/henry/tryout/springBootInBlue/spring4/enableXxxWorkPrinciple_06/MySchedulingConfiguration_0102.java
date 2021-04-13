package com.henry.tryout.springBootInBlue.spring4.enableXxxWorkPrinciple_06;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@Configuration // 1 这是一个用于配置Bean的类型
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class MySchedulingConfiguration_0102 {

    // 2 向Spring容器中添加了/注入了 一个ScheduledAnnotationBeanPostProcessor类型的bean实例
    public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }

}

/*
那 为什么导入这个 配置类，就能够 开启 定时计划的特性呢？
答：可能 最终实现定时任务的最底层类型就是 这里添加到Spring容器中的 ScheduledAnnotationBeanPostProcessor实例吧
 */
