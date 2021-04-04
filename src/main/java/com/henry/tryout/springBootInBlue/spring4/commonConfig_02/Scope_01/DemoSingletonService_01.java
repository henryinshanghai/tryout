package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.Scope_01;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

// 1 定义一个scope为 singleton的Bean
@Service
@Scope("singleton") // Spring的默认设置就是 singleton(这里其实不需要显式编码) - Spring容器中就只会有一个该Bean的实例
public class DemoSingletonService_01 {

}
