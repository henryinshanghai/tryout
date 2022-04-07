package com.henry.tryout.java_8_features.stream_02.search_and_match_04;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Search_02 {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 查找到随便一份素食菜肴 - 调用的结果一直都是 french fries
        Optional<Dish> anyVegetarian = searchAnyVegetarian(menu);
        anyVegetarian.ifPresent(dish -> System.out.println(dish.getName()));

        // 查找满足特定条件的结果中的第一个
        Optional<Integer> num = searchFirstItemMatchCondition();
        System.out.println("满足 平方值能够被3整除的第一个数字元素是： " + (num.orElse(null)));
    }

    // 第一个 平方值能够被3整除的数字
    private static Optional<Integer> searchFirstItemMatchCondition() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        return numbers.stream()
                .map(x -> x * x) // output_item: int
                .filter(x -> x % 3 == 0)
                .findFirst();
    }

    private static Optional<Dish> searchAnyVegetarian(List<Dish> menu) {
        return menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
}
