package com.henry.tryout.java_8_features.do_collection_with_stream_03.partitionItems_03;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class partitionWithPredicate_01 {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 分区的作用：返回true子组 + false子组
        /*
            特征：
                1 分类函数是一个 Predicate - 返回boolean类型的值
                2 它会保留 true与false 两套流的列表
         */
        // 简单分区
        partitionMenuToVegetarianAndNot(menu);

        // 对分区结果再进行分组 - 素食 + 菜品类型
        partitionMenuThenGroup(menu);

        // 计算素食 & 非素食中热量最高的菜
        calculateTheHighestDishInPartitionMenu(menu);

    }

    private static void calculateTheHighestDishInPartitionMenu(List<Dish> menu) {
        Map<Boolean, Dish> partitionToDish = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, // partitioningBy()的重载方法
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )));

        for (Boolean partition : partitionToDish.keySet()) {
            System.out.println(partition + "分区中，最高热量的菜品是： " + partitionToDish.get(partition));
        }
    }

    private static void partitionMenuThenGroup(List<Dish> menu) {
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianToDishes = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));

        for (Boolean vegetarianStatus : vegetarianToDishes.keySet()) {
            System.out.println(vegetarianStatus + "分区的如下： ");
            Map<Dish.Type, List<Dish>> dishTypeToDishes = vegetarianToDishes.get(vegetarianStatus);
            for (Dish.Type type : dishTypeToDishes.keySet()) {
                System.out.println("        " + type + "分组的结果为：" + dishTypeToDishes.get(type));
            }
        }
    }

    private static void partitionMenuToVegetarianAndNot(List<Dish> menu) {
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));// 用来实现分区的收集器方法 partitioningBy

        for (Boolean partition : partitionedMenu.keySet()) {
            System.out.println(partition + "分区的结果为：" + partitionedMenu.get(partition));
        }
    }
}
