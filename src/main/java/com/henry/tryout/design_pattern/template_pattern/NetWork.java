package com.henry.tryout.design_pattern.template_pattern;

// 社交网络 - #1 Base类； 作用：提供模板方法 & 组成模板方法的步骤
public abstract class NetWork {

    void init() {
        System.out.println("Network: loading finished");
    }

    // 模板方法 - 联系朋友
    void contactFriends(String message) {
        // step1：初始化软件
        init();

        // step2: 登录
        if (login()) {
            // step3:发送消息
            post(message);
            // step4:登出
            logOut();
        }
    }

    protected abstract boolean logOut();

    // 发送消息
    protected abstract boolean post(String message);

    protected abstract boolean login();
}
