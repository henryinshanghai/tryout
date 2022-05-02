package com.henry.tryout.lambda_test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static com.henry.tryout.java_8_features.refactor_test_debug_04.test_lambda_expression_03.high_level_function.filter;

@SpringBootTest
public class High_Level_Lambda_Expression_Test {

    @Test
    public void testFilter() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        // 手段：传入多个不同的lambda对 高阶函数进行测试，以此来保证高阶函数的正确性
        List<Integer> even = filter(numbers, i -> i % 2 == 0);
        List<Integer> smallerThanThree = filter(numbers, i -> i < 3);

        Assert.assertEquals(Arrays.asList(2, 4), even);
        Assert.assertEquals(Arrays.asList(1, 2), smallerThanThree);
    }
}
