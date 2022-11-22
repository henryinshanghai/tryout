package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.report_service_demo.original_design;

import org.apache.commons.collections.ListUtils;

import java.util.List;

// 代码中常见的模式 - 一整套僵化且完备的处理流程 这种写法对于新的需求很不友好
// 现有模式中的步骤 - {获取订单, 生成订单的统计信息, 生成统计信息的报表, 发送统计信息的邮件}
// 新的需求 - 把统计信息发送给另外一个内部系统(用作展示)
public class OriginalReportService {

    public void process() {
        // 获取到当天的订单
        List<Order> orders = fetchDailyOrders();
        // 生成订单的统计信息
        OrderStatistics statistics = generateOrderStatistics(orders);

        /* 这两个操作其实都是在消费统计信息statistics -> 可以抽取出一个公共模型 */
        // 生成统计报表
        generateStatisticsReport(statistics);
        // 发送统计邮件
        sendStatisticsByMail(statistics);
    }

    private void sendStatisticsByMail(OrderStatistics statistics) {

    }

    private void generateStatisticsReport(OrderStatistics statistics) {

    }

    private OrderStatistics generateOrderStatistics(List<Order> orders) {
        return null;
    }

    private List<Order> fetchDailyOrders() {
        return ListUtils.EMPTY_LIST;
    }
}

class Order {

}

class OrderStatistics {

}

