package com.henry.tryout.leetcodes.Huawei.phase2.str.top_k_frequent_words_692.exe.small_moves;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 验证：可以使用 Collections.reverse(<list>) 来 实现对list中元素的 原地逆序
public class ReverseItemInListDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Annie", "Bob", "Cris", "David", "Eric",
                "Frank", "Greg"
        );

        // 对列表元素进行逆序
        Collections.reverse(names);
        printItemIn(names);
    }

    private static void printItemIn(List<String> names) {
        for (String currName : names) {
            System.out.print(currName + " ");
        }
        System.out.println();
    }
}
