package com.henry.tryout.java_basic.open_close_principal;

public class StatisticsByMailer implements OrderStatisticsConsumer{

    @Override
    public void consume(OrderStatistics statistics) {
        // 发送统计邮件
        sendStatisticsByMail(statistics);
    }

    private void sendStatisticsByMail(OrderStatistics statistics) {
        System.out.println("发送关于统计信息的邮件");
    }
}
