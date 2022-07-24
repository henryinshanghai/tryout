package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class ListEquals_04 {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);

        // 为什么这里的 equals()方法会返回true？ - 主要取决于 equals()方法的实现
        // equals()方法中判断了两点 - 1 是不是List的类型； 2 集合中的item的数量与内容是不是相同
        if (arrayList.equals(linkedList)) {
            System.out.println("equals is true");
        } else {
            System.out.println("equals is false");
        }

        if (Objects.equals(arrayList, linkedList)) { // 结果是相等的
            System.out.println("两个变量相等");
        } else {
            System.out.println("两个变量不相等");
        }
    }
}
/*
启示：
    1 JDK10中引入了 局部变量类型推断 （Local Variable Type Inference）;
        作用：初始化阶段，再处理var变量的时候，编译器会检查右边代码的返回值类型，并将其类型应用于左侧变量；
    2 尽量避免使用 实例对象 来调用equals()方法，否则容易抛出NPE；
        推荐使用 JDK7中引入的 Objects的equals()方法 - 能够有效地规避 NPE的问题
 */
