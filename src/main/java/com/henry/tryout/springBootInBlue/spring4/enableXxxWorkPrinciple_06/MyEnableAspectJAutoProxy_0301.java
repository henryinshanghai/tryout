package com.henry.tryout.springBootInBlue.spring4.enableXxxWorkPrinciple_06;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import({AspectJAutoProxyRegistrar.class} // note: AspectJAutoProxyRegistrar这个类型发生了编译期报错
// 预期可能是因为spring4 中并没有这个类型 暂时不想找到解决方案，先跳过吧 😳
public @interface MyEnableAspectJAutoProxy_0301 {

}
