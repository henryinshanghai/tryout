package com.henry.tryout.spring_clips_from_nanshan.bean_post_processor_02.usageDemo.using_BeanPostProcessor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerUsingBeanPostProcessor {

    @VersionInjected
    private HelloService helloService;

    @RequestMapping("/sayHello")
    public void sayHello() {
        helloService.sayHello();
    }
}
