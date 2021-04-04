package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.ApplicationEvent05;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// step02 自定义事件监听器，实现ApplicationListener
// 实现ApplicationListener接口，并指定 需要监听的事件类型 - 通过泛型参数来指定
@Component
public class DemoListener_02 implements ApplicationListener<DemoEvent_01> {

    @Override
    public void onApplicationEvent(DemoEvent_01 event01) {
        String msg = event01.getMsg();

        System.out.println("我(beanListener)接受到了 bean-demoPublisher 所发步的消息： " + msg);
    }
}
