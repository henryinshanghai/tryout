package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.ApplicationEvent05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher_03 {

    @Autowired
    ApplicationContext applicationContext; // 注入ApplicationContext用来发布事件

    // 发布事件的方法
    public void publish(String msg) {
        // 发布 特定的事件；
        // 预期此方法被执行后，监听器 DemoListener_02中的 onApplicationEvent()方法也会被执行
        applicationContext.publishEvent(new DemoEvent_01(this, msg));
    }


}
