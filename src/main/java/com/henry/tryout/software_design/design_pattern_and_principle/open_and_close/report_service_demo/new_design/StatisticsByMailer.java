package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.report_service_demo.new_design;


// #2 实现模型的具体类 - 具体的消费者02：使用统计数据 来 发送邮件
public class StatisticsByMailer implements OrderStatisticsConsumer {
    @Override
    public void consume(OrderStatistics statistics) {
        sendStatisticsByMail(statistics);
    }

    private void sendStatisticsByMail(OrderStatistics statistics) {

    }
}
