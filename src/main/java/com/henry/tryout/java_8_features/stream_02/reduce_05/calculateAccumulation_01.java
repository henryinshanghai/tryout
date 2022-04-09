package com.henry.tryout.java_8_features.stream_02.reduce_05;

import java.util.Arrays;
import java.util.List;

// reduce操作 用于 反复累计的模式
public class calculateAccumulation_01 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        printSum(numbers);

        System.out.println("====================");

        printProduct(numbers);
    }

    private static void printProduct(List<Integer> numbers) {
        Integer product = numbers.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println("numbers中元素的乘积为： " + product);
    }

    private static void printSum(List<Integer> numbers) {
        Integer sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("numbers中各个元素的累计和为：" + sum);
    }
}
