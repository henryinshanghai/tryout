package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.ApplicationEvent05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_05 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig_04.class);

        // 获取发布者类型的Bean实例
        DemoPublisher_03 demoPublisher = context.getBean(DemoPublisher_03.class);

        // 调用publish()方法
        /*
            预期：当指定的事件被发布时，监听者能够对这个事件的发布动作做出响应。
            这是Bean之间通信的一种方式 - 参数绑定在event上，在多个Bean之间传递
         */
        demoPublisher.publish("hello application event");

        context.close();
    }
    /*
        事件：不同的Bean之间可以使用 事件 这种手段来进行通信。
        事件在Bean A（发布者）中发布后， Bean B（监听者）能够感知到这个动作，并执行预先定义好的行为。
     */

}
