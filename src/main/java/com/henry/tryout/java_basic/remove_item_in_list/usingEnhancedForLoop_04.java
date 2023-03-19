package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 验证：使用增强for循环的语法进行 ”遍历时删除“的操作，会导致 ”并发修改异常“
// fact01： 增强for循环的语法，底层使用的是 迭代器模式 来遍历list的；iterator()方法
// fact02: ArrayList的iterator()方法中， 返回了 类中自定义的迭代器；
// fact03: 迭代器类所需要实现的接口 - {hasNext(), next(), remove()}
// fact04: 在next()方法的第一步，就要 checkForComodification()
// 上述方法中，会比较 列表的修改次数modCount 与 期待列表的修改次数expectedModCount。如果两个值不相同，就会抛出 并发修改异常
// 而在remove()方法的实现中，只更新了 modCount，而没有同步更新 expectedModCount. 因此会抛出异常
public class usingEnhancedForLoop_04 {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        // 创建一个 新的list对象
        List<String> editableNames = new ArrayList<>(names);

        // 使用增强for循环实现 ”在循环时删除特定元素“
        for (String name : editableNames) {
            if (name.startsWith("李")) {
                editableNames.remove(name); // remove(<item>)
            }
        }

        // 打印删除元素后的列表
        editableNames.forEach(System.out::println);
        System.out.println(editableNames);
    }
}
