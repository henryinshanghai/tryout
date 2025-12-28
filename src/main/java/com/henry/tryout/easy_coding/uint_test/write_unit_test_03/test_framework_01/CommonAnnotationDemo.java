package com.henry.tryout.easy_coding.uint_test.write_unit_test_03.test_framework_01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


// 目的：指定用例 在测试报告中的显示名称 - 手段：@Display注解
@DisplayName("售票器类型测试")
public class CommonAnnotationDemo {

    // 定义待测试类的实例
    private TicketSeller ticketSeller;

    // 定义 在整个测试类开始之前 需要执行的操作
    @BeforeAll
    public static void init() {
        // 全局与外部资源的创建、初始化工作

    }

    // 定义 整个测试类完成之后 需要执行的操作
    @AfterAll
    public static void cleanUp() {
        // 全局与外部资源的释放、销毁
    }

    // 定义 每个测试用例开始之前 需要执行的操作
    @BeforeEach // 这个注解并没有生效耶~~~
    public void create() {
        // 基础数据、运行环境的准备
        this.ticketSeller = new TicketSeller();
        System.out.println("create()方法运行了");
    }

    // 定义 每个测试用例运行完成后 需要执行的操作
    @AfterEach
    public void destroy() {

    }

    // 测试用例1 - 当车票售出后，剩余的票应该减少
    @Test
    @DisplayName("售票后,余票应该减少")
    public void shouldReduceInventoryWhenTicketSoldOut() {
        ticketSeller = new TicketSeller();
        ticketSeller.setInventory(10);
        ticketSeller.sell(1);
        Assert.assertEquals(ticketSeller.getInventory(), 9);
    }

    // 测试用例2 - 余票不足时，应该报错
    @Test
    @DisplayName("余票不足时,应该报错")
    public void shouldThrowExceptionWhenNoEnoughInventory() {
        ticketSeller = new TicketSeller();
        ticketSeller.setInventory(0);

        // 如果在 assertJ的依赖中声明了scope为 test,由于这里的Test类的写法没有遵守惯例，则：这里的assertJ无法引用到 AssertJ中的方法
        assertThatExceptionOfType(TicketException.class)
                .isThrownBy(() -> { ticketSeller.sell(1);})
                .withMessageContaining("all tickets sold out")
                .withNoCause();


    }

    // 禁用测试用例 - 手段：Disabled注解
    // 特征：这个测试用例会出现在最终的报告中，但是不会被执行
    @Disabled
    @Test
    @DisplayName("有退票时余票增加")
    public void shouldIncreaseInventoryWhenTicketRefund() {
        ticketSeller = new TicketSeller();
        ticketSeller.setInventory(10);
        ticketSeller.refund(1);
        assertThat(ticketSeller.getInventory()).isEqualTo(11);
    }
}
