package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 验证：使用for循环对list执行 ”循环时删除“的操作，无法得到预期的效果
// 原理：指针与列表都在发生变化
public class usingForLoop_01 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        // 创建一个 新的list对象
        List<String> editableNames = new ArrayList<>(names);

        // 使用for循环实现 ”在循环时删除特定元素“
        for (int cursor = 0; cursor < editableNames.size(); cursor++) {
            String currName = editableNames.get(cursor);
            if (currName.startsWith("李")) {
                editableNames.remove(cursor); // remove(index)
            }
        }

        // 打印删除元素后的列表
        editableNames.forEach(System.out::println);
    }
}
