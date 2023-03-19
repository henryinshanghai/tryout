package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 验证：使用列表的Stream能够 把对列表的预期操作 转换为流式操作{转换、过滤、收集}
public class usingStreamToFilter_08 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        List<String> editableNames = new ArrayList<>(names);

        // 🐖 这里其实生成了一个新的列表
        editableNames = editableNames.stream()
                .filter(name -> !name.startsWith("李"))
                .collect(Collectors.toList());

        System.out.println(editableNames);
    }
}
