package com.henry.tryout.software_design.design_pattern_and_principle.design_pattern.original_design;

// 把用户信息 发送给 后台数据汇总模块
public class UserCollectorSender implements UserSender {
    private UserCollectorChannel channel;

    public void send(final User user) {
        channel.send(user);
    }
}

class UserCollectorChannel {
    void send(User user) {
        System.out.println("send user in UserCollectorChannel");
    }
}
