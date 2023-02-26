package com.henry.tryout.spring_clips_from_nanshan.bean_post_processor_02.usageDemo.using_BeanPostProcessor;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface VersionSwitch {
    String value() default "";
}
