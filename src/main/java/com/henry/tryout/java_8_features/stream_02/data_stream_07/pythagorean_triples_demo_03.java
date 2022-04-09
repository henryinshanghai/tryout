package com.henry.tryout.java_8_features.stream_02.data_stream_07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class pythagorean_triples_demo_03 {
    public static void main(String[] args) {
        // 生成 毕达哥拉斯三元组
        Stream<int[]> pythagorean_triples = IntStream.rangeClosed(1, 100).boxed() // 准备元素 a
                .flatMap( // 这里需要对流数组进行归并
                        a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0) // 准备元素 b
                                .mapToObj(b ->
                                        new int[]{a, b, (int) Math.sqrt(a * a + b * b)}) // 创建三元组
                );

        pythagorean_triples.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
