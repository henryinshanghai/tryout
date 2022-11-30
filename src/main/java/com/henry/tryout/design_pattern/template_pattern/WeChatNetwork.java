package com.henry.tryout.design_pattern.template_pattern;

// 微信社交网络 #2 子类 - 在子类中，实现父类中定义的所有抽象步骤
public class WeChatNetwork extends NetWork{

    @Override
    protected boolean logOut() {
        System.out.println("WeChat Network: logOut success.");
        return true;
    }

    @Override
    protected boolean post(String message) {
        System.out.println("WeChat Network: post message success.");
        return true;
    }

    @Override
    protected boolean login() {
        System.out.println("WeChat Network login success.");
        return true;
    }
}
