package com.henry.tryout.springBootInBlue.spring4.enableXxxWorkPrinciple_06;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AsyncConfigurationSelector.class}) // 这个 AsyncConfigurationSelector.java 又有什么特征？点进去看看
public @interface MyEnableAsync_0201 {

    /* 自定义注解里面的这些语句到底是什么语法？ */
    Class<? extends Annotation> annotation() default Annotation.class;

    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;

    int order() default 2147483647;

}
