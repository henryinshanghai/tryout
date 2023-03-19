package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 验证：使用列表对象的 removeIf()方法， 能够成功实现 列表“遍历时删除”的操作
public class usingRemoveIf_09 {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        List<String> editableNames = new ArrayList<>(names);

        // 🐖 removeIf()是对列表对象本身进行操作，因此返回值不是列表，而是一个布尔值
        editableNames.removeIf(name -> name.startsWith("李"));

        System.out.println(editableNames);
    }
}
