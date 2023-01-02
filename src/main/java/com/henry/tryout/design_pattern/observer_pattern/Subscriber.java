package com.henry.tryout.design_pattern.observer_pattern;

// #2 订阅者（Subscriber）：用于接收发布者所发出的通知；
public interface Subscriber {

    // 接收 发布者所发出的通知
    void update(ContextParam contextParam);
}
