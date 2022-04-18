package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.observer_03;

public class NYTimes_01 implements Observer_01{

    @Override
    public void receiveNotify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
