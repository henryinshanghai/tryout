package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.report_service_demo.new_design;

public class StatisticsSender implements OrderStatisticsConsumer{

    @Override
    public void consume(OrderStatistics statistics) {
        sendStatisticsToOtherSystem(statistics);
    }

    private void sendStatisticsToOtherSystem(OrderStatistics statistics) {

    }
}
