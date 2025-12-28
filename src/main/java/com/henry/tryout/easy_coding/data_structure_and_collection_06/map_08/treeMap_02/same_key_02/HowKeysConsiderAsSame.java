package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.treeMap_02.same_key_02;

import java.util.TreeMap;

// 验证：对于传入TreeMap的Key-Value，只要 两个Key的比较结果 为0，TreeMap就会 把它们视为 同一个Key
// 比较两个Key - 手段#1：比较器的compare()方法（如果传入的话）；手段#2：Key的compareTo()方法
// 手段#2的验证步骤：① 实现Comparable接口; ② 向map中 添加 key相同的key-value； ③ 获取并查看 map的size
public class HowKeysConsiderAsSame {
    public static void main(String[] args) {
        // 使用TreeMap 来 创建 map对象
//        HashMap keyToValueMap = new HashMap<>(); // HashMap是使用 hashCode 与 equals 来 实现去重的
        TreeMap keyToValueMap = new TreeMap(); // TreeMap是使用 Comparable或者Comparator 来 实现去重的

        MyKey myKey = new MyKey();
        keyToValueMap.put(myKey, "value one");
        keyToValueMap.put(myKey, "value two");

        // 排序的手段：比较；去重的手段：判断是否相等；
        System.out.println(keyToValueMap.size()); // 1 or 2，具体取决于 MyKey中 compareTo()方法的实现

        // 清除掉 map中所有的entry
        keyToValueMap.clear();
        System.out.println(keyToValueMap.size()); // 0

        // 重新添加 新的entry
        keyToValueMap.put(new MyKey(), "value three");
        keyToValueMap.put(new MyKey(), "value four");

        System.out.println(keyToValueMap.size()); // 1
    }
}

// TreeMap中Key的定义
class MyKey implements Comparable<MyKey> {

    // compareTo()的实现规则 会被TreeMap用来 判断Key是否重复
    @Override
    public int compareTo(MyKey o) {
        return -1;  // 不管传入的对象是什么，都认为 当前对象更小 - 这种方法实现 最终决定了 map的size=2
//        return 0; // 不管传入的对象是什么，都认为 与当前对象相等 - 这种实现 最终决定了 map的size = 1
    }

    // TreeMap在 判断Key是否重复 时，不会使用 hashCode() + equals()的组合；
    @Override
    public int hashCode() {
        return 1; // 所有对象的hash值都为1
    }

    @Override
    public boolean equals(Object obj) {
        return true; // equals()比较的结果也总是相等的
    }
}