package com.henry.tryout.java_basic.open_close_principal;


import java.util.Arrays;
import java.util.List;

public class ReportService_new_approach {

    // 持有 consumer 的列表
    
    public void process() {
        // 获取当前的订单
        List<Order> orders = fetchDailyOrders();

        // 生成统计信息
        OrderStatistics statistics = generateOrderStatistics(orders);

        // 使用统计信息 来 生成报表
        generateStatisticsReport(statistics);

        // 发送统计邮件
        sendStatisticsByMail(statistics);
    }

    private void sendStatisticsByMail(OrderStatistics statistics) {
        System.out.println("发送关于统计信息的邮件");
    }

    private void generateStatisticsReport(OrderStatistics statistics) {
        System.out.println("生成报表");

    }

    private OrderStatistics generateOrderStatistics(List<Order> orders) {

        System.out.println(orders.size());

        return new OrderStatistics();
    }

    private List<Order> fetchDailyOrders() {
        return Arrays.asList(new Order());
    }
}
