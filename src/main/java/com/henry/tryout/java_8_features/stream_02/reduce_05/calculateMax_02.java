package com.henry.tryout.java_8_features.stream_02.reduce_05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class calculateMax_02 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        printMaxItem(numbers);

        System.out.println("===================");

        printMinItem(numbers);
    }

    private static void printMinItem(List<Integer> numbers) {
        Optional<Integer> minItem = numbers.stream()
                .reduce(Integer::min);

        System.out.println("numbers中的最小元素为： " + minItem.orElse(null));
    }

    private static void printMaxItem(List<Integer> numbers) {
        Optional<Integer> maxNum = numbers.stream()
                .reduce(Integer::max);

        System.out.println("numbers中的最大值为：" + (maxNum.orElse(null)));
    }
}
