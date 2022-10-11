package com.henry.tryout.java_8_features.do_collection_with_stream_03.reduce_and_accumulation_01;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

// 验证：使用Stream的collect() OR reduce()方法，能够对集合中的元素执行归约的操作
public class availableCollector_in_Collector_02 {
    public static void main(String[] args) {
        // Collector类中提供了工厂方法 - 从这些工厂方法中可以直接创建收集器  用于collect()方法的参数
        /*
            收集器的功能：
                1 把 流中的item 归约和汇总成一个值；
                2 对 流中的item 进行分组；
                3 对 流中的item 进行分区。
         */

        generateAResultFromItemsInStream();
    }

    private static void generateAResultFromItemsInStream() {
        List<Dish> menu = new Menu().getMenu();
        // 收集菜单中有多少菜品 - 计数目的
        howManyDishesInMenu(menu);

        // 收集菜单中热量最高的菜品 - 最值 purpose
        getTheDishWithHighestCalories(menu);

        // 收集菜单中所有菜品的总热量/平均热量 - 统计 purpose
        calculateTotalCaloriesOfMenu(menu);
        calculateAverageCaloriesOfMenu(menu);

        // 收集菜单中所有菜品的一般统计值 - 统计 purpose
        calculateAllAspectOfMenu(menu);

        // 收集菜单中所有菜品的名称到一个字符串 - 连接字符串 purpose
        joinDishNameTogether(menu);

        // 使用更一般性的手段 实现相同的目的 - 计算菜单中所有菜品的总热量 collect(reduce(xxx))
        calculateTotalCaloriesOfMenuWithReducing(menu);

        // 计算菜单中菜品的总热量 collect() VS. reduce() - 很多时候，殊途同归   获取菜总热量的N种方式
        calculateTotalCaloriesOfMenuWithReduceMethod(menu);
    }

    private static void calculateTotalCaloriesOfMenuWithReduceMethod(List<Dish> menu) {
        // reduce approach
        Integer totalCalories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (i, j) -> i + j);

        // data stream approach
        totalCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        System.out.println("菜单中所有菜品的总热量： " + totalCalories);

    }

    private static void calculateTotalCaloriesOfMenuWithReducing(List<Dish> menu) {
        Integer totalCalories = menu.stream()
                // 归约操作的语义很不好描述 - 这里需要的三个参数都各自有用
                // 特征：那些更好用的方法（比如counting()），底层的实现也是使用的 reducing()
                .collect( // 初始值， 转换函数， 累积函数
                        reducing(0, Dish::getCalories, (i, j) -> i + j)
                );

        System.out.println("所有菜品的总热量为： " + totalCalories);
    }

    private static void joinDishNameTogether(List<Dish> menu) {
        String shortMenu = menu.stream()
                .map(Dish::getName)
                .collect(joining());

        // 使用重载版本 接受字符串之间的分隔符
        shortMenu = menu.stream()
                .map(Dish::getName)
                .collect(joining(", "));

        System.out.println("所有菜品的名字： " + shortMenu);
    }

    private static void calculateAllAspectOfMenu(List<Dish> menu) {
        IntSummaryStatistics menuStatistics = menu.stream()
                .collect(summarizingInt(Dish::getCalories));

        System.out.println("对菜单的统计结果为： " + menuStatistics);
    }

    private static void calculateAverageCaloriesOfMenu(List<Dish> menu) {
        Double averageCalories = menu.stream()
                .collect(averagingInt(Dish::getCalories));

        System.out.println("菜单中所有菜品的平均热量为： " + averageCalories);
    }

    private static void calculateTotalCaloriesOfMenu(List<Dish> menu) {
        Integer totalCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));

        System.out.println("菜单中所有菜品的总热量： " + totalCalories);
    }

    private static void getTheDishWithHighestCalories(List<Dish> menu) {
        // 获取item中的最大item - 手段：maxBy()方法
        // 特征：需要传入一个自定义的比较器，用来说明 item之间的比较规则

        // 创建一个 Comparator对象 - 手段： Comparator的静态方法 comparingInt()
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> highestCaloriesDish = menu.stream()
                .collect(maxBy(dishCaloriesComparator));

        System.out.println("卡路里最高的菜品： " + highestCaloriesDish.orElse(null));
    }

    private static void howManyDishesInMenu(List<Dish> menu) {

        // 对菜单中的菜品进行计数 - 从菜单流中生成一个整数值
        long dishAmount =
                menu.stream()
                        .collect(Collectors.counting());// 传入一个Collector对象 - 手段：调用Collectors的静态方法 counting()

        System.out.println("菜单中共有" + dishAmount + "个菜品");
    }
}
