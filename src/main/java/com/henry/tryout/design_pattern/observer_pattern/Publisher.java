package com.henry.tryout.design_pattern.observer_pattern;

import java.util.ArrayList;
import java.util.List;

// #4 发布者 Publisher：用于 向其他对象发送 “值得关注的事件”；
public class Publisher {
    // #1 持有 订阅者的列表
    private List<Subscriber> subscribers = new ArrayList<>();

    // #2 发布者本身的状态
    private int mainState;

    public int getMainState() {
        return this.mainState;
    }

    // #3 订阅者管理
    public void subscribe(Subscriber subscriber) {
        System.out.println("新增订阅者： " + subscriber.hashCode());
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        System.out.println("取消订阅的订阅者： " + subscriber.hashCode());
        this.subscribers.remove(subscriber);
    }

    // #4 通知订阅者 “值得关注的事情”
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(new ContextParam(this.mainState));
        }
    }

    // #5 业务逻辑相关的方法 - 封装给客户端开发者使用
    public void mainBusinessLogic(int newState) {
        this.mainState = newState;
        System.out.println("发布者发送消息： " + this.mainState);
        // 把消息通知给 所有的订阅者
        notifySubscribers();
    }
}
