package com.henry.tryout.java_8_features.stream_02.search_and_match_04;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.List;

// 验证: 借助Stream - 可以使用指定的条件 来 尝试从集合元素中匹配出结果；
// 手段：JDK提供了好几种用于匹配的方法，可以按需使用 - {anyMatch(boolean_condition), allMatch(boolean_condition), noneMatch(boolean_condition)}
// 语法：anyMatch(<boolean_condition>)
public class Match_01 {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 是否 至少有一个匹配
        if (isAtLeastOneMatch(menu)) {
            System.out.println("菜单中至少存在有一个 素食 的菜肴");
        }

        // 是否 所有元素全部匹配
        if (allDishMatches(menu)) {
            System.out.println("菜单中的所有菜肴都满足 低卡路里");
        }

        // 是否 没有元素匹配
        if (noneDishMatch(menu)) {
            System.out.println("菜单中没有任何一个菜肴满足 高卡路里");
        }
    }

    private static boolean noneDishMatch(List<Dish> menu) {
        return menu.stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);
    }

    private static boolean allDishMatches(List<Dish> menu) {
        return menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
    }

    private static boolean isAtLeastOneMatch(List<Dish> menu) {
        return menu.stream()
                .anyMatch(Dish::isVegetarian);
    }
}
