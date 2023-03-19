package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 验证：使用 forEach的语法 来 进行列表的“遍历时删除”操作，会出现 “并发修改异常”
// 原因：forEach语法底层使用的是 “增强for循环”, 底层是迭代iterator - 因此对列表进行remove()时会抛出异常
public class usingForEachGrammar_07 {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        List<String> editableNames = new ArrayList<>(names);
        editableNames.forEach(
               (name) -> {
                   if (name.startsWith("李")) {
                       editableNames.remove(name);
                   }
               }
        );

        System.out.println(editableNames);

    }
}
