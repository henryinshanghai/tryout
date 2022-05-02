package com.henry.tryout.java_basic.open_close_principal;

// 基于共性所引入的新模型
public interface OrderStatisticsConsumer {

    // 行为 - 消费 数据
    void consume(OrderStatistics orderStatistics);

}

