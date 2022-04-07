package com.henry.tryout.java_8_features.stream_02.filter_and_match_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.List;

public class truncate_and_skip_demo_02 {
    public static void main(String[] args) {

        List<Dish> menu = new Menu().getMenu();

        truncateStreamShorter(menu);

        System.out.println("===================");
        skipItemsInStream(menu);

    }

    private static void skipItemsInStream(List<Dish> menu) {
        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2) // 跳过流中的前N个元素
                .forEach(System.out::println);
    }

    private static void truncateStreamShorter(List<Dish> menu) {


        menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3) // 截断流，得到特定长度的元素集合
                .forEach(System.out::println);
    }
}
