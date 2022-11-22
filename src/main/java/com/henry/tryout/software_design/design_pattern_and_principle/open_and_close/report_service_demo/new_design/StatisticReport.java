package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.report_service_demo.new_design;


// 具体的消费者01 - 使用统计数据来生成报告
class StatisticReport implements OrderStatisticsConsumer {

    @Override
    public void consume(OrderStatistics statistics) {
        generateStatisticsReport(statistics);
    }

    private void generateStatisticsReport(OrderStatistics statistics) {

    }
}
