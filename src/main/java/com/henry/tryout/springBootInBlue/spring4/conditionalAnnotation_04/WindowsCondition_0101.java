package com.henry.tryout.springBootInBlue.spring4.conditionalAnnotation_04;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

// step1-01 定义判断条件 windowsCondition
public class WindowsCondition_0101 implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext,
                           AnnotatedTypeMetadata annotatedTypeMetadata) {
        // note：从spring容器对象中能够直接获取到当前系统的环境信息 - 不推荐在代码中使用
        return conditionContext.getEnvironment().getProperty("os.name").contains("Windows");
    }
}
