package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.observer_03;

public class client_using_lambda_approach_03 {
    public static void main(String[] args) {

        TweetFeed_02 tweetFeed = new TweetFeed_02();

        // 由于 Observer接口类型其实是一个 函数式接口，所以这里可以 使用lambda表达式代替实现类
        tweetFeed.registerObserver((String tweet) ->
        {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        }); // 虽然避免了 对子类的创建，但由于没有对象名称，这里也丢失了一些语义

        tweetFeed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London... " + tweet);
            }
        });

        tweetFeed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        });

        tweetFeed.notifyObserver("The queen said her favorite book is Java 8 in Action!");
    }
} // client写的代码明显变得更复杂一些
