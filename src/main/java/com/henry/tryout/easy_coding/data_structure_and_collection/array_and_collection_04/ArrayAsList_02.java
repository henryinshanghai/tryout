package com.henry.tryout.easy_coding.data_structure_and_collection.array_and_collection_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAsList_02 {
    public static void main(String[] args) {
        String[] stringArray = new String[3];

        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";

        // 把数组转换成为集合
        List<String> stringList = Arrays.asList(stringArray);
        // 修改转换后的集合中的元素 - 成功
        stringList.set(0, "oneList");
        // 查看数组中的第一个元素 - 结果：oneList     说明：原始的数组也随之被改变
        System.out.println(stringArray[0]);

        // 对转换后的集合 添加元素 - 编译不会报错，但是运行会报错 UnsupportedOperationException
//        stringList.add("four");
//        stringList.remove(2);
//        stringList.clear();

        // 数组转集合安全的方式 - 手动new出一个李鬼来
        ArrayList<String> safeStringList = new ArrayList<>(Arrays.asList(stringArray));
        System.out.println(safeStringList.size());
        safeStringList.add("clint");
        System.out.println(safeStringList.size());
    }
}

/*
启示：
    1 Arrays.asList()方法 采用的是 适配器模式 - 后台使用的仍旧是原有数组；
    2 Arrays.asList()方法 返回的 ArrayList 是 Arrays类中定义的一个 内部类。
        查看源码，根据作用域就近的原则，这里返回的是 Arrays中的内部类；
        ArrayList源码解析：
            private final E[] a; // 变量a被final修饰，表示不能修改其引用

            public E set(int index, E element) {
                E oldValue = a[index];
                a[index] = element;
                return oldValue; // set()方法会有返回值，而且返回值是 旧值
            }
    3 UnsupportedOperationException 异常是从哪儿跑出来的？
        李鬼的父类： AbstractList

 */
