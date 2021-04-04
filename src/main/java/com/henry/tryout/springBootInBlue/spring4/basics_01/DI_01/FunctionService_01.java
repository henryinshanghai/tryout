package com.henry.tryout.springBootInBlue.spring4.basics_01.DI_01;

import org.springframework.stereotype.Service;

@Service
public class FunctionService_01 { // 1 定义 提供能力的类型

    public String sayHello(String word) {
        return "Hello " + word + " !";
    }
}
