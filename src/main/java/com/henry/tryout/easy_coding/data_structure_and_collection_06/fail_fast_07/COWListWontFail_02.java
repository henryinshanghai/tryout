package com.henry.tryout.easy_coding.data_structure_and_collection_06.fail_fast_07;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// 验证： COWList支持 在遍历时删除列表元素
// 原理：对于写操作，创建一个列表的副本进行操作
public class COWListWontFail_02 {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");

        for (String s : list) {
            if (s.equals("one")) {
                list.remove(s);
            }
        }

        System.out.println(list); // [two, three]
    }
}
