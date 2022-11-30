package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.report_service_demo.new_design;


// #1 抽取出公共的模型；
public interface OrderStatisticsConsumer {
    // 定义模型应该有的行为 - 消费统计数据，用来做某件事
    void consume(OrderStatistics statistics);
}


