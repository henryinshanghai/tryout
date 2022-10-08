package com.henry.tryout.java_8_features.stream_02.map_03;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 验证：使用stream能够把集合中的元素 转化成为新的元素；
// 手段: list.stream().map(<transform_operation_via_lambda_expression>).xxx()
public class transformItemToElement_01 {
    public static void main(String[] args) {
        transformDishToName();

        System.out.println("===================");

        transformStringToItsLength();

        System.out.println("*******************");
        transformItemToItsSquare();
    }

    private static void transformItemToItsSquare() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squares = numbers.stream()
                .map(number -> number * number) // 使用lambda表达式 来表示 数值的转变过程: 乘方
                .collect(Collectors.toList());

        squares.forEach(System.out::println);
    }

    private static void transformStringToItsLength() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");

        words.stream()
                .map(String::length) // 字符串的长度
                .forEach(System.out::println);
    }

    private static void transformDishToName() {
        List<Dish> menu = new Menu().getMenu();

        // 把 item 转化成为 element
        /*
            lambda表达式的语法糖： 方法引用；
                如果 lambda表达式做的事情只是“把参数转发给实际被执行的方法”，则：可以使用方法引用的语法。
         */
        List<String> dishNames = menu.stream()
                .map(Dish::getName) // dish对象的属性
                .collect(Collectors.toList());

        dishNames.forEach(System.out::println);
    }
}
