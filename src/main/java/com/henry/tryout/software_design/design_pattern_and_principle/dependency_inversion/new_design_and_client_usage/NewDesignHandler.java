package com.henry.tryout.software_design.design_pattern_and_principle.dependency_inversion.new_design_and_client_usage;


// 高层模块 aka client
public class NewDesignHandler {
    // #2 在高层模块中，持有新引入的抽象/模型
    private MessageSender sender;

    void execute() {
        Message message = new Message("info from new_design_handler");
        // 使用“抽象” 来 发送消息
        sender.send(message);
    }
}

class Message {
    private String info;

    public Message(String info) {
        this.info = info;
    }
}
