package com.henry.tryout.design_pattern.observer_pattern;

// #3 具体订阅者：用于接收发布者所发出的通知，并做出自己的响应。
public class ConcreteSubscriber implements Subscriber{

    @Override
    public void update(ContextParam contextParam) {
        System.out.println("订阅者： " + this.hashCode()
                + " 接收到 订阅信息： " + contextParam.getMainState());
    }
}
