package com.henry.tryout.software_design.design_pattern_and_principle.design_pattern.new_design;

// 特征：UserSMSSender 与 UserCollectorSender类中 send()方法实现的重复度很高
// 可以把重复的部分抽取到公共父类中，在子类中实现特性的部分
public class UserSMSSender extends BaseUserSender {
    private String appKey;
    private String appSecret;
    private UserSMSChannel channel;

    public void send(final User user) {
        User sanitizedUser = sanitize(user);
        channel.send(appKey, appSecret, user);
        collectMessageSent(user);
    }
}

class UserSMSChannel {
    public void send(String appKay, String appSecret, User user) {
        System.out.println("send user from UserSMSChannel");
    }
}