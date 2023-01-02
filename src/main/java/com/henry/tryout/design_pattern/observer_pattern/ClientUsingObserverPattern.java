package com.henry.tryout.design_pattern.observer_pattern;

public class ClientUsingObserverPattern {
    public static void main(String[] args) {
        // 创建发布者
        Publisher publisher = new Publisher();

        // 创建订阅者
        ConcreteSubscriber subscriber1 = new ConcreteSubscriber();
        ConcreteSubscriber subscriber2 = new ConcreteSubscriber();

        // 订阅者 订阅 发布者的事件
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        // 调用发布者所提供的 封装了业务逻辑的方法
        publisher.mainBusinessLogic(1);

        System.out.println("====================");

        // 订阅者2 取消订阅
        publisher.unsubscribe(subscriber1);

        // 发布者 发送消息2 - 预期就只有订阅者1接收到消息
        publisher.mainBusinessLogic(2);
    }
}
