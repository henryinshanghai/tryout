package com.henry.tryout.design_pattern.template_pattern;

// QQ社交网络 #2 子类 - 在子类中，实现父类中定义的所有抽象步骤
public class QQNetWork extends NetWork{

    @Override
    protected boolean logOut() {
        System.out.println("QQNetwork: login success.");
        return true;
    }

    @Override
    protected boolean post(String message) {
        System.out.println("QQNetwork: post message success.");
        return true;
    }

    @Override
    protected boolean login() {
        System.out.println("QQNetwork: login success.");
        return true;
    }
}
