package com.henry.tryout.java_8_features.do_collection_with_stream_03.groupItems_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class groupItem_multiple_rules_02 {
    public static void main(String[] args) {

        // 多级分组：先按照规则1分成N个子组，然后在子组中 再按照规则2分组
        // 手段：groupingBy(<param1>, <param2>)
        List<Dish> menu = new Menu().getMenu();

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupByDishType = groupByDishTypeAndCaloriesLevel(menu);
        printItemInGroup(groupByDishType);

        groupByDishTypeAndCounting(menu);

        groupByDishTypeAndHighestCalories(menu);
    }

    private static void groupByDishTypeAndHighestCalories(List<Dish> menu) {
        Map<Dish.Type, Optional<Dish>> dishTypeToDish = menu.stream().collect(
                groupingBy(Dish::getType,
                        maxBy(Comparator.comparingInt(Dish::getCalories)))
        );

        for (Dish.Type type : dishTypeToDish.keySet()) {
            System.out.println(type + "分组中，热量最高的菜品为：" + dishTypeToDish.get(type).orElse(null));
        }
    }

    private static void groupByDishTypeAndCounting(List<Dish> menu) {
        Map<Dish.Type, Long> dishTypeToAmount = menu.stream()
                .collect( // 分类函数， 收集器
                        groupingBy(Dish::getType, counting()
        ));

        for (Dish.Type type : dishTypeToAmount.keySet()) {
            System.out.println(type + "分组中，菜品的数量为： " + dishTypeToAmount.get(type));
        }
    }

    private static void printItemInGroup(Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupByDishType) {
        for (Dish.Type type : groupByDishType.keySet()) {
            System.out.println(type + "类型中，有如下菜品： ");
            Map<CaloricLevel, List<Dish>> groupByCaloricLevel = groupByDishType.get(type);

            for (CaloricLevel caloricLevel : groupByCaloricLevel.keySet()) {
                System.out.println("        " + caloricLevel + "等级下，有如下菜品： " + groupByCaloricLevel.get(caloricLevel));
            }
        }
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupByDishTypeAndCaloriesLevel(List<Dish> menu) {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupByDishType =
                menu.stream().collect(
                    groupingBy(Dish::getType, // 分类函数
                            groupingBy(dish -> { // 二级分类函数
                                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT;
                            })
                    )
        );
        return groupByDishType;
    }

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }
}

