package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.Scope_01;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype") // 声明当前bean的 scope为 prototype - aka 每次调用，Spring容器都会新建一个bean的实例
public class DemoPrototypeService_02 {

}
