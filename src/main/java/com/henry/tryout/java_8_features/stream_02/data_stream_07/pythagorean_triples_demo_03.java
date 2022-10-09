package com.henry.tryout.java_8_features.stream_02.data_stream_07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

// 验证：使用JDK提供的工具类 能够创建出 勾股数三元组
// 手段：对于任意整数a，找到整数b - 使整数b满足 a^2 + b^2 的取根号结果为整数 - 取余1等于0
// 验证：能够从数值流上的元素映射出一个新的元素 - 手段：xxx.mapToObj(<create_new_item_base_exist_item>)
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
