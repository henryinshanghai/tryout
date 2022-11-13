package com.henry.tryout.software_design.interface_of_program_language;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newArrayListWithCapacity;

// 验证：Guava相比于JDK提供了 更有语义的ArrayList的初始化方法
public class InitiateAClass {
    public static void main(String[] args) {
        // 示例：构造方法没办法有效地表示 "构造逻辑"
        // #1 创建有两个元素的列表
        ArrayList<String> listWithElements = new ArrayList();
        listWithElements.add("foo");
        listWithElements.add("bar");

        // #2 创建一个初始容量为10的数组
        ArrayList<String> listWithCapacity = new ArrayList<>(10);

        // #3 Guava提供了 “更好的构造器”
        // 特征：构造器有了更加清晰的构造语义
        // 手段：使用静态方法代替了 构造函数
        ArrayList<String> elements = newArrayList("foo", "bar");
        ArrayList<String> elementsWithExceptCapacity = newArrayListWithCapacity(10);


    }
}
