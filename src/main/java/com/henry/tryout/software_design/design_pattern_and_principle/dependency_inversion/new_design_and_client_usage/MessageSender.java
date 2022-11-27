package com.henry.tryout.software_design.design_pattern_and_principle.dependency_inversion.new_design_and_client_usage;

// #1 设计一个模型/抽象 - 用来表示 低层模块(Kafka)在高层模块(Handler)中承担的角色：消息发送者
public interface MessageSender {
    void send(Message message);
}
