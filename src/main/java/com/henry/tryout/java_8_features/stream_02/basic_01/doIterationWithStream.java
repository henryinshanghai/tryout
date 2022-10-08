package com.henry.tryout.java_8_features.stream_02.basic_01;

import com.henry.tryout.java_8_features.stream_02.Dish;
import com.henry.tryout.java_8_features.stream_02.Menu;

import java.util.List;
import java.util.stream.Collectors;

// 验证：可以使用流对象 来 完成集合中元素的遍历工作
// 语法：list.stream().xxx().ooo()
public class doIterationWithStream {
    public static void main(String[] args) {
        List<Dish> menu = new Menu().getMenu();

        // 使用 Stream来 迭代集合中的元素
        List<String> dishNames = menu.stream()
                .map(d -> d.getName()) // map - 转换操作
                .collect(Collectors.toList()); // collect - 收集操作

        dishNames.forEach(System.out::println);
    }
}
