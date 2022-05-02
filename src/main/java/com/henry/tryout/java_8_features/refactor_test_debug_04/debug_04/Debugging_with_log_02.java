package com.henry.tryout.java_8_features.refactor_test_debug_04.debug_04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Debugging_with_log_02 {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);

        // 手段1：使用打印语句；
        // 特征： 一旦使用forEach(),流就会整个被消耗。我们没有办法查看每一个步骤的输出结果
        numbers.stream()
                .map(x -> x + 17)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println);

        // 手段2： peek()方法；
        // 作用： 获取到的流中每一个操作所产生的输出；
        // 原理： 在流的每个元素恢复运行之前，插入一个动作。。
        // 特征： 不会恢复整个流的运行，而是把操作顺延到下一个操作。
        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(Collectors.toList());


    }
}
