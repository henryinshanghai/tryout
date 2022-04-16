package com.henry.tryout.java_8_features.do_collection_with_stream_03.groupItems_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class groupItem_collect_from_subGroup_03 {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 按照type分组，并获取到每个子组中热量最高的Dish
        groupByTypeAndRetrieveHottestDish(menu);

        groupingByAndItsBuddiesCollector(menu);
    }

    private static void groupingByAndItsBuddiesCollector(List<Dish> menu) {
        // 求每个子组中的菜品热量总和
        // 传入的收集器是为了执行 进一步的归约操作
        calculateTotalCaloriesPerSubgroup(menu);

        // 抽象作用 & 实际作用
        calculateCaloriesLevelPerSubgroup(menu);
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }

    private static void calculateCaloriesLevelPerSubgroup(List<Dish> menu) {
        // 书上的例子不好用，有编译错误
//        menu.stream()
//                .collect(groupingBy(Dish::getType),
//                        mapping(dish -> {
//                            if ( dish.getCalories() <= 400) return CaloricLevel.DIET;
//                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//                            else return CaloricLevel.FAT;
//                        }, toSet()));

    }

    private static void calculateTotalCaloriesPerSubgroup(List<Dish> menu) {
        Map<Dish.Type, Integer> dishTypeToTotalCalories = menu.stream()
                .collect(
                        groupingBy(Dish::getType, summingInt(Dish::getCalories))
                );

        for (Dish.Type type : dishTypeToTotalCalories.keySet()) {
            System.out.println(type + "分组中，所有菜品的总热量为： " + dishTypeToTotalCalories.get(type));
        }
    }

    private static void groupByTypeAndRetrieveHottestDish(List<Dish> menu) {
        Map<Dish.Type, Dish> mostCaloriesByType =
                menu.stream()
                    .collect(
                            groupingBy(Dish::getType, // 分类函数
                                    // 作用：对现有的收集器做一个包装 - 手段：xxx(<exist_collector>, <transform_method>)
                                    collectingAndThen( // 经过包装后的收集器
                                            maxBy(Comparator.comparingInt(Dish::getCalories)),
                                            Optional::get) // 转换函数
                                    )
                            );

        for (Dish.Type type : mostCaloriesByType.keySet()) {
            System.out.println(type + "分组中，热量最高的菜肴为： " + mostCaloriesByType.get(type));
        }

    }
}
