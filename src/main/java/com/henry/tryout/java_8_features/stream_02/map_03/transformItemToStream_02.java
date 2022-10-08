package com.henry.tryout.java_8_features.stream_02.map_03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 验证：如果中间操作得到了 多个流对象Stream<Stream<xxx>>，则：可以使用 flatMap()方法，连接多个流成单一流
// 手段：得到Stream<Stream<xxx>>的中间操作 - {字符串被拆分成字符数组, 组合两个list中的元素}
// tip：IDEA会对当前操作得到的结果类型做出提示，如果是Stream<Stream<xxx>>，则：可以尝试使用下flatMap
public class transformItemToStream_02 {
    public static void main(String[] args) {

        // 获取到 单词列表中的每个单词 所使用到的所有字母的集合
        printUniqueCharacterSet();

        System.out.println("=============");

        // 构建所有的数对组合
        printAllNumberPair();

        System.out.println("*************");

        // 返回 加和值可以被3整除 的数对组合
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

        // 目标：获取到所有单词 所使用的所有字母 的集合（去重）
        // 特征：使用map()得不到想要的List<String>结果
        // 验证如下 👇
        List<Stream<String>> collect = words.stream()
                .map(word -> word.split("")) // item: String -> String[]
                .map(Arrays::stream) // item: String[] -> Stream<String>
                .distinct()
                .collect(Collectors.toList());

        // 难点：需要把所有的流连接起来，得到单一的流
        // 手段： flatMap(Stream<Stream<String>>)
        // 验证如下 👇
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // 从多个Stream得到单一的Stream - 手段：flatMap()
                .collect(Collectors.toList());

        return uniqueCharacters;
    }
}
