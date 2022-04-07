package com.henry.tryout.java_8_features.stream_02.map_03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class transformItemToStream_02 {
    public static void main(String[] args) {

        // 获取到 单词列表中 所有使用到的字母集合
        printUniqueCharacterSet();

        System.out.println("=============");

        // 创建所有的数对组合
        printAllNumberPair();

        System.out.println("*************");

        // 返回 加和值可以被3整除的数对组合
        printPairsDivisibleByThree();

    }

    private static void printPairsDivisibleByThree() {
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);

        List<int[]> pairsDivisibleByThree = nums1.stream()
                .flatMap(num1 -> nums2.stream()
                        .filter(num2 -> (num1 + num2) % 3 == 0) // output_item: integer
                        .map(num2 -> new int[]{num1, num2}) // output_item: int[]
                )
                .collect(Collectors.toList());

        printPairs(pairsDivisibleByThree);
    }

    private static void printAllNumberPair() {
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);

        // 手段： flatMap(Stream<Stream<String>>)
        List<int[]> allPairs = nums1.stream()
                .flatMap(num1 -> nums2.stream()
                        .map(num2 -> new int[]{num1, num2}))
                .collect(Collectors.toList());

        printPairs(allPairs);
    }

    private static void printPairs(List<int[]> allPairs) {
        for (int[] pair : allPairs) {
            System.out.println("(" + pair[0] + "," + pair[1] + ")");
        }
    }

    private static void printUniqueCharacterSet() {
        List<String> uniqueCharacters = getUniqueCharacterSet();

        uniqueCharacters.forEach(System.out::println);
    }

    private static List<String> getUniqueCharacterSet() {
        List<String> words = Arrays.asList("Hello", "World");

        // 获取到所有单词所使用的字母的集合（去重）
        // 使用map()得不到想要的List<String>结果
        List<Stream<String>> collect = words.stream()
                .map(word -> word.split("")) // item: String -> String[]
                .map(Arrays::stream) // item: String[] -> Stream<String>
                .distinct()
                .collect(Collectors.toList());

        // 把所有的流连接起来，得到单一的流
        // 手段： flatMap(Stream<Stream<String>>)
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        return uniqueCharacters;
    }
}
