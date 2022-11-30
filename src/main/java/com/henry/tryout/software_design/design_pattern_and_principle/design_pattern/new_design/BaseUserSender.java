package com.henry.tryout.software_design.design_pattern_and_principle.design_pattern.new_design;


public class BaseUserSender implements UserSender {

    @Override
    public void send(User user) {

    }

    // 新增业务需求3：敏感信息过滤
    protected User sanitize(final User user) {
        return null;
    }

    // 新增业务需求4：收集消息，发送信息
    protected void collectMessageSent(final User user) {
        System.out.println("collect the message that been sent out");
    }
}

