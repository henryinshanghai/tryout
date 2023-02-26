package com.henry.tryout.spring_clips_from_nanshan.bean_post_processor_02.usageDemo.without_BeanPostProcessor;

import com.henry.tryout.spring_clips_from_nanshan.bean_post_processor_02.usageDemo.VersionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

// 验证：不使用 BeanPostProcessor的特性时，开发者就只能使用 控制语句 来实现“选择正确的实现版本”
@RestController
public class HelloControllerWithoutUsingBeanPostProcessor {

    @Autowired
    private HelloServiceImplV1 helloServiceImplV1;

    @Autowired
    private HelloServiceImplV2 helloServiceImplV2;

    public void hello() {
        // 使用 控制语句 来实现“选择正确的实现版本”
        if ("V1".equals(VersionUtils.getVersion())) {
            helloServiceImplV1.sayHello();
        } else {
            helloServiceImplV2.sayHello();
        }
    }
}
