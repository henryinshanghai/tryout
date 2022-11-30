package com.henry.tryout.software_design.design_pattern_and_principle.design_pattern.original_design;

public class UserSMSSender implements UserSender {
    private String appKey;
    private String appSecret;
    private UserSMSChannel channel;

    @Override
    public void send(User user) {
        channel.send(user);
    }
}

class UserSMSChannel {
    public void send(User user) {
        System.out.println("send user from UserSMSChannel");

    }
}