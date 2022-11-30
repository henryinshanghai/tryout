package com.henry.tryout.software_design.design_pattern_and_principle.design_pattern.new_design_further;

// 共性需求在父类中实现 - {sanitize, collectMessageSent}
// 特性需求交给子类来实现 - {doSend()}
public class BaseUserSender implements UserSender{

    // 共性需求：发送用户信息
    @Override
    public void send(User user) {
        User sanitizedUser = sanitize(user);
        doSend(user);
        collectMessageSent(user);
    }

    private void collectMessageSent(final User user) {
        System.out.println("send out information");
    }

    private void doSend(final User user) {
        System.out.println("doSend in BaseUserSender");
    }

    private User sanitize(final User user) {
        System.out.println("information 脱敏 in BaseUserSender");
        return null;
    }
}
