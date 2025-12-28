package com.henry.tryout.easy_coding.data_structure_and_collection_06.fail_fast_07.expect_fast_fail_01.COW_03;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// 验证：COWList支持 在遍历时 删除列表元素
// 原理：对于 写操作，创建一个 列表的副本 进行操作，并在写操作完成后，把 原始引用 指向 该副本
// 而读操作 仍旧在原始列表上进行；这不会导致 读取的结果是过时的吗?
public class COWListWontFail {
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
