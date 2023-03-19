package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 验证：当把列表size抽取出来作为局部变量时，程序执行会抛出IOB异常
// 原理：cursor不断增加，而list的item在不断减少。因此肯定会出现下标越界
public class usingForLoopWIthFixedSize_02 {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        // 创建一个 新的list对象
        List<String> editableNames = new ArrayList<>(names);

        // 使用for循环实现 ”在循环时删除特定元素“
        int size = editableNames.size();
        for (int cursor = 0; cursor < size; cursor++) {
            String currName = editableNames.get(cursor);
            if (currName.startsWith("李")) {
                editableNames.remove(cursor); // remove(index)
            }
        }

        // 打印删除元素后的列表
        editableNames.forEach(System.out::println);

    }
}
