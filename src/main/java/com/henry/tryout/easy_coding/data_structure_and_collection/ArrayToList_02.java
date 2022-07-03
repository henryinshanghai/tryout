package com.henry.tryout.easy_coding.data_structure_and_collection;

import java.util.Arrays;
import java.util.List;

public class ArrayToList_02 {
    public static void main(String[] args) {

        String[] stringArr = new String[3];
        stringArr[0] = "one";
        stringArr[1] = "two";
        stringArr[2] = "three";

        // Arrays.asList()方法返回的是一个 内部类ArrayList
        List<String> stringList = Arrays.asList(stringArr);

        // 修改集合中的第一个元素 - 数组中的第一个元素随之改变
        stringList.set(0, "oneList");
        System.out.println(stringArr[0]);

        // 对转换得到的列表 本身进行操作 - 编译不会报错，但是运行会报错 UnsupportedOperationException
        stringList.add("four");
        stringList.remove(2);
        stringList.clear();
    }
}
