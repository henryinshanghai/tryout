package com.henry.tryout.leetcodes.Huawei.phase2.str.top_k_frequent_words_692.exe.small_moves;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 使用 compareTo() 来 自定义排序规则
// 升序规则：第一个参数.compareTo(第二个参数)；
// 降序规则：第二个参数.compareTo(第一个参数)；
public class CompareToUsageDemo {
    public static void main(String[] args) {
        // 快速构建列表   手段：Arrays.asList(<item1>, <item2>..)
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");

        // 定义升序规则 (A -> Z)
//        Collections.sort(names, (a, b) -> a.compareTo(b));
        Collections.sort(names, String::compareTo); // 等效写法
        printStrIn(names);
        // 结果: [Alice, Bob, Charlie]

        // 定义降序规则 (Z -> A)
//        Collections.sort(names, (a, b) -> b.compareTo(a));
        Collections.sort(names, Comparator.reverseOrder()); // 等效写法
        printStrIn(names);
        // 结果: [Charlie, Bob, Alice]
    }

    private static void printStrIn(List<String> names) {
        for (String currentName : names) {
            System.out.print(currentName + " ");
        }
        System.out.println();
    }
}
