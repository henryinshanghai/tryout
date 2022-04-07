package com.henry.tryout.java_8_features.stream_02.map_03;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                .map(number -> number * number)
                .collect(Collectors.toList());

        squares.forEach(System.out::println);
    }

    private static void transformStringToItsLength() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");

        words.stream()
                .map(String::length)
                .forEach(System.out::println);
    }

    private static void transformDishToName() {
        List<Dish> menu = new Menu().getMenu();

        // 把 item 转化成为 element
        /*
            转化的手段1： 方法引用 - 什么时候可以使用方法引用 来 简化lambda表达式来着？
                当lambda表达式做的事情只是 - 把参数转发给实际被执行的方法 时.
         */
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        dishNames.forEach(System.out::println);
    }
}
