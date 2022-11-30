package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.report_service_demo.new_design;

// #2 实现模型的具体类 - 具体的消费者3：发送静态数据 到另一个系统
public class StatisticsSender implements OrderStatisticsConsumer{

    @Override
    public void consume(OrderStatistics statistics) {
        sendStatisticsToOtherSystem(statistics);
    }

    private void sendStatisticsToOtherSystem(OrderStatistics statistics) {

    }
}
