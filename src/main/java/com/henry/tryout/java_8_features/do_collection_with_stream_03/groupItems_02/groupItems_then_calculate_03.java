package com.henry.tryout.java_8_features.do_collection_with_stream_03.groupItems_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

// 验证：在使用groupingby()对集合进行分组后，可以使用 快捷函数 来实现快速统计/汇总
// 手段：collectingAndThen()、summingInt()等
public class groupItems_then_calculate_03 {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 按照type分组，并获取到每个子组中热量最高的Dish
        // 手段：collect(groupingby(<group_rule>, collectingAndThen(xxx, ooo)))
        groupByTypeAndRetrieveHottestDish(menu);

        // 先按照type分组，再使用收集器进行汇总操作
        groupingByAndItsBuddiesCollector(menu);
    }

    private static void groupingByAndItsBuddiesCollector(List<Dish> menu) {
        // 分组后，计算每个子组的总热量 - 手段：collect(groupingby(<group_rule>, <calc_method>))
        calculateTotalCaloriesPerSubgroup(menu);

        // 分组后，计算每个子组的卡路里水平 - 手段：???
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
