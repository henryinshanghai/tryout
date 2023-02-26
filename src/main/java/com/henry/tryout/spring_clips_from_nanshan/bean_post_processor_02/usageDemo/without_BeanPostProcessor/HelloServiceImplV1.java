package com.henry.tryout.spring_clips_from_nanshan.bean_post_processor_02.usageDemo.without_BeanPostProcessor;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImplV1 implements  HelloService{

    @Override
    public void sayHello() {
        System.out.println("Hello from V1!");
    }
}
