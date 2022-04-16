package com.henry.tryout.java_8_features.do_collection_with_stream_03.partitionItems_03;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class partition_a_dataStream_02 {
    public static void main(String[] args) {

        // 把前n个自然数，拆分成为 质数 & 非质数
        int n = 30;
        partitionIntoPrimeAndNot(n);
    }

    private static void partitionIntoPrimeAndNot(int n) {
        Map<Boolean, List<Integer>> partitionIntoPrimeAndNot =  IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(num -> isPrime(num)));

        for (Boolean partition : partitionIntoPrimeAndNot.keySet()) {
            System.out.println(partition +  "分区中的数： " + partitionIntoPrimeAndNot.get(partition));
        }
    }

    // 定义一个谓语  - 用来判断数字是不是质数
    private static boolean isPrime(int number) {
        return IntStream.range(2, number)
                .noneMatch(i -> number % i == 0); // 不存在任何一个能够将 number整除的数字
    }
}
