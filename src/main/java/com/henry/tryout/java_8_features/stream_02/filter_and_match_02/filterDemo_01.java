package com.henry.tryout.java_8_features.stream_02.filter_and_match_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class filterDemo_01 {
    public static void main(String[] args) {

        // 从菜单中筛选出 所有素食菜品
        filterDishesFromMenu();

        filterOutAllEvenNumbers();

    }

    private static void filterOutAllEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        numbers.stream()
                .filter(num -> num % 2 == 0)
                .distinct()
                .forEach(System.out::println); // forEach也是一个终端操作
    }

    private static void filterDishesFromMenu() {
        List<Dish> menu = new Menu().getMenu();

        List<Dish> vegetarianDishes = getVegetarianDishes(menu);

        vegetarianDishes.forEach(System.out::println);
    }

    private static List<Dish> getVegetarianDishes(List<Dish> menu) {
        List<Dish> vegetarianDishes = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        return vegetarianDishes;
    }
}
