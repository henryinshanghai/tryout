package com.henry.tryout.springBootInBlue.spring4.annotation_05;

import org.springframework.stereotype.Service;

@Service
public class DemoService_02 {

    public void outputResult() {
        System.out.println("通过 组合注解的配置方式 同样获取到了bean实例");
    }
}
