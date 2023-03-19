package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 验证：把指针放在列表最后面，一边删除，一边向前移动指针。 - 这是一种可行的做法
public class usingForLoopWIthTraverseOrder_03 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        // 创建一个 新的list对象
        List<String> editableNames = new ArrayList<>(names);

        // 使用for循环实现 ”在循环时删除特定元素“
        for (int backwardCursor = editableNames.size() - 1; backwardCursor > 0; backwardCursor--) {
            String currName = editableNames.get(backwardCursor);
            if (currName.startsWith("李")) {
                editableNames.remove(backwardCursor); // remove(index)
            }
        }

        // 打印删除元素后的列表
        editableNames.forEach(System.out::println);
        System.out.println(editableNames);
    }
}
