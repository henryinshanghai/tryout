package com.henry.tryout.java_8_features.stream_02.reduce_05;

import java.util.Arrays;
import java.util.List;

// 验证：如果需要对集合中的元素进行反复计算，并最终得到单一结果，则：可以使用 reduce操作；
// 手段：list.stream().reduce(<repeatable_operation>); - 特征： reduce()本身就是终点操作
// 特征：累计计算并得到单一值的模式 可以用于描述很多常见的操作， 比如 累计求和, 累计乘积等
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
