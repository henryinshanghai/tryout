package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.observer_03;

public interface Subject_02 {
    // 注册观察者
    void registerObserver(Observer_01 observer);

    // 通知观察者
    void notifyObserver(String tweet);
}
