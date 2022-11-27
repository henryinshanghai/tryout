package com.henry.tryout.software_design.design_pattern_and_principle.dependency_inversion.original_design_and_client_usage;

// #1 高层模块 OriginalHandler
public class OriginalHandler {
    // #2 在高层模块中，引入/持有 低层模块(Kafka所提供的组件)
    private KafkaProducer producer;

    // #3 直接使用引入的组件 编写代码
    void execute() {
        Message message = new Message("info from original_handler");
        producer.send(new KafkaRecord<>("topic", message));
    }
}

// Kafka所提供的组件
class KafkaProducer {
    void send(KafkaRecord kafkaRecord) {
        System.out.println("kafka do something based on kafka recorder");
    }
}

class Message {
    String info;

    public Message(String info) {
        this.info = info;
    }
}

class KafkaRecord<T> {
    String name;
    Message message;

    public KafkaRecord(String name, Message message) {
        this.name = name;
        this.message = message;
    }
}
