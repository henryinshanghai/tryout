package com.henry.tryout.easy_coding.uint_test.write_unit_test_03.test_framework_01;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// 数据驱动测试 - @TestFactory
@DisplayName("售票器类型测试")
public class ExchangeRateConverterTest_04 {
    private TicketSeller ticketSeller;

    // 把数据的输入输出 与 测试逻辑 分开 - 作用：一次性对各种类型的输出、输出结果进行验证
    @TestFactory
    @DisplayName("时间售票检查")
    Stream<DynamicTest> oddNumberDynamicTestWithStream() {
        ticketSeller = new TicketSeller();
        ticketSeller.setCloseTime(LocalTime.of(12, 20, 25, 0));
        // 使用指定的元素来创建顺序流 - 手段： Stream.of()
        return Stream.of(
                        Lists.list("提前购票", LocalTime.of(12, 20, 24, 0), true),
                        Lists.list("准点购买", LocalTime.of(12, 20, 25, 0), true),
                        Lists.list("晚点购票", LocalTime.of(12, 20, 26, 0), false)
                ) // 顺序处理 流中的元素data
                .map(data ->
                        DynamicTest.dynamicTest(
                                (String) data.get(0), // 第一个元素是 String
                                () -> // 第二个元素是一个lambda表达式：data.get(1)是 LocalTime对象、data.get(2)是boolean对象
                                        assertThat(ticketSeller.cloudSellAt(data.get(1))).isEqualTo(data.get(2))));
    }
}
