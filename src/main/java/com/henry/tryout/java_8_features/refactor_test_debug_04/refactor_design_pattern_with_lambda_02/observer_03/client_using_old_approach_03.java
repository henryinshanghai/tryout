package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.observer_03;

public class client_using_old_approach_03 {
    public static void main(String[] args) {
        // 1 获取 subject对象
        TweetFeed_02 tweetFeed = new TweetFeed_02();

        // 2 向 subject对象中 添加具体的观察者
        tweetFeed.registerObserver(new NYTimes_01());
        tweetFeed.registerObserver(new Guardian_01());
        tweetFeed.registerObserver(new LoMonde_01());

        // 3 通过 subject对象 来 通知到所有的观察者
        tweetFeed.notifyObserver("the queen said her favorite book is Java 8 in Action!");
    }
}
