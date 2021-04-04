package com.henry.tryout.springBootInBlue.spring4.basics_01.AOP_03;

import org.springframework.stereotype.Service;

// 被拦截的类型 × 1
@Service
public class DemoAnnotationService_02 { // 2 在Service类型上， 使用自定义的注解 在其上应用拦截规则，实现“在一组类上共享行为”

    @Action_01(name = "使用 注解式拦截 来 拦截对add()方法的调用")
    public void add() {

    }

}
