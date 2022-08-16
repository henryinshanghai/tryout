package com.henry.tryout.easy_coding.uint_test.write_unit_test_03.test_framework_01;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

@DisplayName("交易服务测试")
public class TransactionServiceTest_02 {

    // 第一层-1
    @Nested
    @DisplayName("用户交易测试")
    class UserTransactionTest {
        // 第二层-1
        @Nested
        @DisplayName("正向测试用例")
        class PositiveCase {
            @Test
            @DisplayName("交易检查通过")
            public void shouldPassCheckWhenParameterValid() {

            }
        }

        // 第二层-2
        @Nested
        @DisplayName("负向测试用例")
        class NegativeCase {

        }
    }

    // 第一层-2
    @Nested
    @DisplayName("商家交易测试")
    class CompanyTransactionTest {

    }

}
