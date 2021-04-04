package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.ApplicationEvent05;

import org.springframework.context.ApplicationEvent;

// step01 自定义事件,继承ApplicationEvent
public class DemoEvent_01 extends ApplicationEvent { // ApplicationEvent中没有默认的构造器，因此自定义类中也就没有默认的构造器


    private static final long serialVersionUID = -1687856335147597406L;
    private String msg;

    // 所以需要添加自己的构造器方法
    public DemoEvent_01(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
