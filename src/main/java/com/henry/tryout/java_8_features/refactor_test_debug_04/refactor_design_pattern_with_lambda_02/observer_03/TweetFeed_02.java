package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.observer_03;

import java.util.ArrayList;
import java.util.List;

public class TweetFeed_02 implements Subject_02 {
    // 准备一个 成员变量，用来存储所有 被注册的observer
    private final List<Observer_01> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer_01 observer) {
        this.observers.add(observer); // 这里的 this.observers 只是为了方便区分成员变量 与 传入的参数
    }

    @Override
    public void notifyObserver(String tweet) {
        observers.forEach(observer -> observer.receiveNotify(tweet));
    }

}
