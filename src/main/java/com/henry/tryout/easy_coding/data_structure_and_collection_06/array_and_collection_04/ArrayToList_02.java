package com.henry.tryout.easy_coding.data_structure_and_collection_06.array_and_collection_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 语法：把数组转化成为 list - 手段：Arrays.asList(<array>)
// 验证：转换后得到的list 其实只是原始数组的一个视图(适配器模式)
// 安全的使用方式：使用转换得到的list作为参数，手动new出一个新的list对象
public class ArrayToList_02 {
    public static void main(String[] args) {
        String[] stringArray = new String[3];

        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";

        // 把数组转换成为list集合 - 手段：Arrays.asList()
        List<String> stringList = Arrays.asList(stringArray);

        /* 对转换得到的list尝试各种操作 👇 */
        // ① 修改list中的元素 - 成功
        stringList.set(0, "oneList");
        // 验证原数组：查看数组中的第一个元素 - 结果：oneList     说明：原始的数组也随之被改变
        System.out.println(stringArray[0]);

        // ② 向list中 添加元素 - 编译不会报错，但是运行会报错 UnsupportedOperationException
//        stringList.add("four");
        // ③ 从list中 移除元素
//        stringList.remove(2);
        // ④ 清除list中的所有元素
//        stringList.clear();

        /*
            以上，说明: 转换后得到的list 其实只是原始数组的一个视图(适配器模式)。list本身存在诸多限制
                原理： Arrays.asList()返回是一个内部类ArrayList.
        */

        // 数组转集合安全的方式 - 手段：使用转换得到的list作为参数，手动new出一个新的list对象
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
