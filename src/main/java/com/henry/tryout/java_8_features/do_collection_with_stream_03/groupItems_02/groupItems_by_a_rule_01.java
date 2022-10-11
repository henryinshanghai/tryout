package com.henry.tryout.java_8_features.do_collection_with_stream_03.groupItems_02;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

// 验证：可以用 collect() + groupingby(<group_rule>) 来实现 对集合中元素的分组
// #2 groupingby(<self_define_rule>)的参数 允许传入自定义的分组规则
public class groupItems_by_a_rule_01 {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 按照 菜品的类型进行分组 - 手段：向collect()方法传入一个特别的收集器
        // 获取到分组收集器 - 手段：groupingBy(<group_rule>)
        groupByDishType(menu);

        // 按照自定义的rule进行分组 - 手段：collect(groupingby(<self_rule>))
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
