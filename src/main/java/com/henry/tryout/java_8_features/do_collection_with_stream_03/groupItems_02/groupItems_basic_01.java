package com.henry.tryout.java_8_features.do_collection_with_stream_03.groupItems_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class groupItems_basic_01 {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 按照 菜品的类型进行分组 - 手段：向collect()方法传入一个特别的收集器
        // 获取到分组收集器 - 手段：groupingBy(<group_rule>)
        groupByDishType(menu);

        groupBySelfDefineRule(menu);
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }

    private static void groupBySelfDefineRule(List<Dish> menu) {
        Map<CaloricLevel, List<Dish>> groupByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        );

        for (CaloricLevel caloricLevel : groupByCaloricLevel.keySet()) {
            System.out.println(caloricLevel + "等级的菜品有： " + groupByCaloricLevel.get(caloricLevel));
        }
    }

    private static void groupByDishType(List<Dish> menu) {
        Map<Dish.Type, List<Dish>> typeToDishes = menu.stream()
                .collect(groupingBy(Dish::getType));

        for (Dish.Type type : typeToDishes.keySet()) {
            System.out.println(type + "分类下，有如下菜品：" + typeToDishes.get(type));
        }
    }
}
