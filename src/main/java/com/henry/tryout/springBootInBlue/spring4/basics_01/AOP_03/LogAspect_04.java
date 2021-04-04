package com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

// 4 编写切面类 - 在切面类中定义 共享的行为（然后在被拦截的类型上织入此行为）
@Aspect
@Component
public class LogAspect_04 {

    // 4-1 单独地声明切入点(where-approach01) - 以便在多个地方重用此切入点
    @Pointcut("@annotation(com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03.Action_01)") // 这里是某一种形式的表达式
    public void annotationPointCut() {};

    // 4-2 声明应用切面的时机 - 使用注解 来 定义切点
    @After("annotationPointCut()") // when(where)
    public void after(JoinPoint joinPoint) { // 一个载体，表示方法的执行点
        /* 公共行为： 打印出注解上name属性的值(作为日志)   手段：反射 */
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action_01 action_01 = method.getAnnotation(Action_01.class);
        System.out.println("注解式拦截到的结果为： " + action_01.name());
    }

    // 4-3 声明应用切面的时机 - 直接使用拦截规则 定义切点
    @Before("execution(* com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03.DemoMethodService_03.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截到的结果： " + method.getName());

    }


}
