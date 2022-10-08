package com.henry.tryout.java_8_features.stream_02.filter_and_match_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 验证：使用list的流对象 能够完成 元素的过滤操作
// 手段: list.stream().filter(boolean_condition).<terminal_operation>()
public class filterDemo_01 {
    public static void main(String[] args) {

        // 从菜单中筛选出 所有素食菜品
        filterDishesFromMenu();

        filterOutAllEvenNumbers();

    }

    private static void filterOutAllEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        numbers.stream()
                .filter(num -> num % 2 == 0) // 过滤条件: item为偶数
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
                .filter(Dish::isVegetarian) // isVegetarian()方法 返回 boolean类型的值
                .collect(Collectors.toList());
        return vegetarianDishes;
    }
}
