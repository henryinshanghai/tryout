package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.report_service_demo.new_design;

import org.apache.commons.collections.ListUtils;

import java.util.List;

// client代码 - 设计的受益者
public class NewReportService {
    // 持有新添加的模型 作为成员属性
    private List<OrderStatisticsConsumer> consumers;

    void process() {
        // 获取到当天的订单
        List<Order> orders = fetchDailyOrders();
        // 生成订单的统计信息
        OrderStatistics statistics = generateOrderStatistics(orders);

        /* 重新设计后，原本的两个方法调用就可以被替换成为for循环 + 单个方法调用 */
        // 对于新增需求 - 把统计信息发送给另外一个内部系统(用作展示)，就只需要添加一个新的实现类
        for (OrderStatisticsConsumer consumer : consumers) {
            consumer.consume(statistics);
        }
    }

    private OrderStatistics generateOrderStatistics(List<Order> orders) {
        return null;
    }

    private List<Order> fetchDailyOrders() {
        return ListUtils.EMPTY_LIST;
    }
}

class OrderStatistics {

}
class Order {

}
