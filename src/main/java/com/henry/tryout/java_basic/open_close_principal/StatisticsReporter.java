package com.henry.tryout.java_basic.open_close_principal;

public class StatisticsReporter implements OrderStatisticsConsumer{


    @Override
    public void consume(OrderStatistics statistics) {
        generateStatisticsReport(statistics);
    }

    private void generateStatisticsReport(OrderStatistics statistics) {
        System.out.println("生成报表");

    }

}
