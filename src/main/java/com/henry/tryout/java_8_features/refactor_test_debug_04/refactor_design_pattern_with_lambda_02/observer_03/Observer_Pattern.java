package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.observer_03;

public class Observer_Pattern {
    public static void main(String[] args) {
        /*
        观察者模式：
            应用场景：对象A的状态变化时，需要通知到其他的多个对象。
            特征：
                1 对象A叫做 主题；
                2 其他的对象叫做 观察者；
            实现手段：
                1 准备一个表示观察者的抽象类 + 准备一堆具体的观察者； - 这里的观察者类就是 僵化代码，可以使用Lambda表达式替代
                2 准备一个表示主题的接口 + 住呢比一个具体的实现类；
                3 在client代码中使用 主题对象 与 观察者对象，把 主题对象的改变通知给观察者。
         */
    }
}
