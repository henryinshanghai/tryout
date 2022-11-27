package com.henry.tryout.software_design.design_pattern_and_principle.dependency_inversion.new_design_and_client_usage;


// 具体实现#1中设计的抽象
public class KafkaMessageSender implements MessageSender {
    // #3 具体实现中 持有低层模块 - KafkaProducer
    private KafkaProducer producer;

    @Override
    public void send(Message message) {
        this.producer.send(new KafkaRecord("topic", message));
    }
}

class KafkaProducer {
    void send(KafkaRecord kafkaRecord) {
        System.out.println("kafka do something based on kafka recorder");
    }
}

class KafkaRecord {
    private String info;
    private Message message;

    public KafkaRecord(String info, Message message) {
        this.info = info;
        this.message = message;
    }
}
