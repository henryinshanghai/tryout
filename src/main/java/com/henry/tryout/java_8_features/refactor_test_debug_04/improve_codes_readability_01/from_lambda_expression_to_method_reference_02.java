package com.henry.tryout.java_8_features.refactor_test_debug_04.improve_codes_readability_01;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;
import com.henry.tryout.java_8_features.stream_02.search_and_match_04.CaloricLevel;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class from_lambda_expression_to_method_reference_02 {
    public static void main(String[] args) {
        // 原则上讲, 方法名往往能够更直观地表达代码的意图 purpose
        usingMethodReferenceReplaceLambda();

        // 尽量使用 JDK内置的具体静态辅助方法 + 方法引用,而不是 自己创建Lambda表达式 + 最一般化的方法
        usingBuildInStaticMethod();

        // 但是：完成同一个purpose,如果有太多的操作的话，也会导致使用者的混乱
    }

    private static void usingBuildInStaticMethod() {
        List<Dish> menu = new Menu().getMenu();

        // 使用 reduce()方法 来 归约计算菜品的总热量
        Integer totalCalories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (cal1, cal2) -> cal1 + cal2);

        // 使用 内置的集合类 来 完成同样的purpose
        Integer totalCalories2 = menu.stream()
                .collect(summingInt(Dish::getCalories));

        // 使用 IDEA提示的 数据流 来 完成同样的purpose
        IntStream totalCalories3 = menu.stream().mapToInt(Dish::getCalories);
    }

    private static void usingMethodReferenceReplaceLambda() {
        List<Dish> menu = new Menu().getMenu();

        // 使用 lambda表达式 来 传递代码片段
        Map<CaloricLevel, List<Dish>> dishesByCaloriesLevel = menu.stream()
                .collect(
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                );

        // 使用 方法引用 来 传递代码片段
        Map<CaloricLevel, List<Dish>> dishesByCaloriesLevel2 = menu.stream()
                .collect(
                        groupingBy(Dish::getCaloricLevel)
                );
    }
}
