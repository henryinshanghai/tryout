package com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03")
@EnableAspectJAutoProxy // 用来开启 Spring对AspectJ的支持
public class AopConfig_05 {

}
