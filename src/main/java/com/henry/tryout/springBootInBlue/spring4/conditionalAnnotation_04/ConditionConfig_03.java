package com.henry.tryout.springBootInBlue.spring4.conditionalAnnotation_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class ConditionConfig_03 {

    /* Mark：规定在特定的条件下，Spring容器才会执行此方法 创建对应的Bean实例 */
    @Bean
    @Conditional(WindowsCondition_0101.class) // 如果当前情况符合 WindowsCondition...
    public ListService_0201 windowsListService() {
        // 则 实例化xxx类型的bean
        return new WindowsListService_0202();
    }

    @Bean
    @Conditional(LinuxCondition_0102.class) // 如果当前情况符合 LinuxCondition...
    public ListService_0201 linuxListService() {
        // 则 实例化ooo类型的bean
        return new LinuxListService_0203();
    }


}
