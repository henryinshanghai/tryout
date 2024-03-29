package com.henry.tryout.java_8_features.stream_02.data_stream_07;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 验证：对数值的流式处理，JDK中提供了快捷方式 - 数值流 & 数值流上的快捷api
// 把一般流转化成为数值流 - 手段：mapToInt(lambda_return_int)
// 把数值流转换回一般流 - 手段：intStream.boxed()
// 获取数值流中的最大值 - 手段： xxx.max();
public class DataStreamDemo {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 计算菜单中所有菜品的卡路里之和
        printCaloriesSum(menu);

        // 把 数值流 转化回去成 一般流 - boxed()方法
        transformStream(menu);

        // 找到热量最高的菜品，并获取到它的卡路里
        printMaxDishCalories(menu);
    }

    private static void printMaxDishCalories(List<Dish> menu) {
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories) // 转化为数值流对象后， 数值流类型提供了很多好用的API
                .max();

        System.out.println("热量最高的菜品的热量为： " + maxCalories.orElse(1));
    }

    private static void transformStream(List<Dish> menu) {
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);

        Stream<Integer> streamOfInteger = intStream.boxed();
    }

    private static void printCaloriesSum(List<Dish> menu) {
        int calories = menu.stream()
                .mapToInt(Dish::getCalories) // 把 Stream<T> 转化成为 数值流，从而避免装箱成本
                .sum();

        System.out.println("菜单中所有菜品的总热量为： " + calories);
    }
}
