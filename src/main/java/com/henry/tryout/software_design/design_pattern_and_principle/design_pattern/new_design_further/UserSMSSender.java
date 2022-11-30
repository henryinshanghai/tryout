package com.henry.tryout.software_design.design_pattern_and_principle.design_pattern.new_design_further;

public class UserSMSSender extends BaseUserSender{
    private String appKey;
    private String appSecret;
    private UserSMSChannel channel;

    // 在子类中重新实现 doSend()方法 - 委托给Channel来具体实现
    public void doSend(final User user) {
        channel.send(appKey, appSecret, user);
    }
}

class UserSMSChannel {
    void send(String appKey, String appSecret, User user) {
        System.out.println("send user information in UserSMSSender");
    }
}
