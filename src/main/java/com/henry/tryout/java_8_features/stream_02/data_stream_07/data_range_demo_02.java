package com.henry.tryout.java_8_features.stream_02.data_stream_07;

import java.util.stream.IntStream;

// 验证：JDK为数值流提供了多种工具类，用于快速创建出需要的数值流。
// 创建指定范围内的整数所构成的数值流 - IntStream.rangeClosed(leftBar, rightBar)
public class data_range_demo_02 {
    public static void main(String[] args) {
        // 生成 [1, 100]数值区间的所有数字
        // 手段： 数值流IntStream对象的 rangeClosed()方法
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);

        // 调用终端操作 count()，以便从流中得到结果
        System.out.println(evenNumbers.count());
    }
}
