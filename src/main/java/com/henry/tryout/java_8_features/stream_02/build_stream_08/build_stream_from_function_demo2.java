package com.henry.tryout.java_8_features.stream_02.build_stream_08;

import java.util.stream.Stream;

// 验证：使用JDK提供的静态方法 能够创建出一些常见的流
// 无限流 - 手段：Stream.iterate(seed, formula)
// 斐波那契流 - 手段：Stream.iterate(seed, formula)
// 定制流 - 手段：Stream.generate(lambda_expression)
public class build_stream_from_function_demo2 {
    public static void main(String[] args) {
        // 创建无限流
        buildUnlimitedStreamWithIterateMethod();

        // 创建斐波那契数列
        buildFibonacciNumbers();

        // 创建满足特定条件的流
        buildStreamWithGivenDemand();
    }

    private static void buildStreamWithGivenDemand() {
        // 只是生成 满足给定条件的元素
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    private static void buildFibonacciNumbers() {
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    private static void buildUnlimitedStreamWithIterateMethod() {
        // 创建无限流 - 手段： Stream.iterate()
        /*
            特征：
                1 需要配合limit()方法使用，否则会有无穷多的元素
                2 给定一个seed, iterate会在每一个item上应用 lambda表达式
         */
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }
}
