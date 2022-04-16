package com.henry.tryout.java_8_features.refactor_test_debug_04.improve_codes_readability_01;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class from_command_style_codes_to_stream_code_03 {
    public static void main(String[] args) {
        usingStreamReplaceCommandStyleCode();
    }

    private static void usingStreamReplaceCommandStyleCode() {
        List<Dish> menu = new Menu().getMenu();

        // 命令式的代码 - 筛选 + 收集
        List<String> dishNames = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }

        // 使用 Stream 来 达到同样的功能
        List<String> dishNames2 = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
