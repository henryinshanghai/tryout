package com.henry.tryout.springBootInBlue.spring4.basics_01.Config_02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig_03 {

    // 配置内容： 把 指定的类型 配置为 Spring IoC容器所管理的bean
    // 好处：能够通过非耦合的方式 来 注入依赖
    @Bean
    public FunctionService_01 functionService_01() {
        return new FunctionService_01();
    }

    // 配置内容： 1 + 2
    @Bean // 1 配置为 Spring IoC容器的bean
    public UseFunctionService_02 useFunctionService_02() {

        UseFunctionService_02 useFunctionService_02 = new UseFunctionService_02();
        // 2 为bean 来 注入依赖项
        useFunctionService_02.setFunctionService_01(functionService_01());

        return useFunctionService_02;
    }

    /*
        总结：
            使用这个Java配置类 一共配置了哪些内容？
            1 把xxx配置为 Spring容器中的bean；
            2 把ooo配置为 Spring容器中的bean；
            3 把 ooo 注入到 filePathToBase64Str 中， 作为xxx的依赖项
     */

    // 另一种配置的方式 - 直接把依赖项 作为 返回Bean的方法的参数
    // Spring的特征 - 只要容器中存在某个Bean， 就可以 在另一个Bean的声明方法中，将之作为参数注入
//    @Bean
//    public UseFunctionService_02 useFunctionService_02(FunctionService_01 functionService_01) {
//        UseFunctionService_02 useFunctionService_02 = new UseFunctionService_02();
//        useFunctionService_02.setFunctionService_01(functionService_01);
//        return useFunctionService_02;
//    } // 不注释掉也没有问题吗？ Spring容器中添加 bean的规则是什么？ 获取bean的规则又是什么？
}
