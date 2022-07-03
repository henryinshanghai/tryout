package com.henry.tryout.easy_coding.data_structure_and_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray_03 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(3);

        list.add("one");
        list.add("two");
        list.add("three");

        // 使用 toArray()转换成为数组 - 结果：原集合元素的泛型丢失
        Object[] array1 = list.toArray();

        // 使用有参数的toArray()方法转换成为数组 - 传入的数组参数容量不够
        String[] array2 = new String[2];
        list.toArray(array2);
        System.out.println(Arrays.asList(array2));

        // 使用有参数的toArray()方法转换成为数组 - 传入的数组参数容量与集合元素数量相等
        String[] array3 = new String[3];
        list.toArray(array3);
        System.out.println(Arrays.asList(array3));
    }
}
/*
结论：
1 不要使用无参的toArray()方法转化得到数组 - 泛型信息会丢失；
2
 */
