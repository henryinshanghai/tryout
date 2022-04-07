package com.henry.tryout.java_8_features.stream_02.basic_01;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.List;
import java.util.stream.Collectors;

public class doIterationWithStream {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 使用 Stream来 迭代集合中的元素
        List<String> dishNames = menu.stream()
                .map(d -> d.getName())
                .collect(Collectors.toList());

        dishNames.forEach(System.out::println);
    }
}
