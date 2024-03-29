package com.henry.tryout.easy_coding.uint_test.write_unit_test_03.test_framework_01;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

// 对单元测试进行分组 - @Tag
@DisplayName("售票器测试类型")
public class TicketSellerTest_03 {

    @Test
    @Tag("fast") // 使用 Tag注解对 单测进行分组
    @DisplayName("售票后,余票会减少")
    public void shouldReduceInventoryWhenTicketBeenSold() {
        System.out.println("from fast group");
    }

    @Test
    @Tag("slow")
    @DisplayName("一次性购买20张车票")
    public void shouldSuccessWhenBuy20TicketsOnce() {
        System.out.println("from slow group");
    }
}
