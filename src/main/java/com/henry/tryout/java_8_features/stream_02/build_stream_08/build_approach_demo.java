package com.henry.tryout.java_8_features.stream_02.build_stream_08;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 验证：创建一般流的3种方式 - {字面值, 数组, 文件}
// 字面值 - 手段： Stream.of(字面值)
// 数组(数值流) - 手段： Arrays.stream(arr)
// 文件(一般流) - 手段: Files.lines(path)
public class build_approach_demo {
    public static void main(String[] args) {

        // 使用字面值 来 创建流
        buildStreamViaLiteralValue();

        // 使用数组 来 创建流
        buildStreamViaArray();

        // 使用文件 来 创建流
        buildStreamViaFile();
    }

    private static void buildStreamViaFile() {
        long uniqueWords = 0;
        // 从文件中返回一个流 - 手段：Files.lines() 流中的每个元素都是 文件中的一行
        // 写在 try()中，流会自动关闭
        // 读取静态文件的手段 - Paths.get(<path_string>)
        try (Stream<String> lines =
                     Files.lines(
                             Paths.get("E:\\development_project\\tryout\\src\\main\\java\\com\\henry\\tryout\\java_8_features\\stream_02\\build_stream_08\\data"),
                             Charset.defaultCharset())) {
            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct() // 去除重复项
                    .count(); // 对不同的单词进行计数

            System.out.println("data文件中共有： " + uniqueWords + "个不同的单词");
        } catch (IOException e) {
            System.out.println("something wrong~");
        }
    }

    private static void buildStreamViaArray() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        IntStream intStream = Arrays.stream(numbers);
        int sum = intStream.sum();

    }

    private static void buildStreamViaLiteralValue() {
        Stream<String> stringStream = Stream.of("Java 8 ", "Lambdas", "In ", "Action");

        stringStream.map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
