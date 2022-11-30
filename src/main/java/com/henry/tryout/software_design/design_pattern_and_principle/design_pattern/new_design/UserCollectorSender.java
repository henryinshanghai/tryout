package com.henry.tryout.software_design.design_pattern_and_principle.design_pattern.new_design;

public class UserCollectorSender extends BaseUserSender{
    private UserCollectorChannel channel;

    public void send(final User user) {
        User sanitizedUser = sanitize(user);
        channel.send(sanitizedUser);
        collectMessageSent(user);
    }
}

class UserCollectorChannel {
    void send(User user) {
        System.out.println("send user in UserCollectorChannel");
    }
}
