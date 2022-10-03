package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.treeMap_02;

import java.util.TreeMap;

// 特性：TreeMap中的key是排列有序的，并且不能重复；- 怎么实现去重的呢？
// 验证：TreeMap是通过 Comparable或者Comparator 来 实现去重的 - 根据key的排序结果 来 实现去重. aka compareTo()返回0，则：认为重复
// 手段：#1 自定义“Key对象相等/Key对象排序”的规则 - 手段：实现Comparable接口; #2 向map中添加key相同的key-value； #3 获取到map的size
public class TreeMapRepeatKey_02 {
    public static void main(String[] args) {
        // 使用TreeMap来创建 map对象
        TreeMap keyToValueMap = new TreeMap(); // 2 - 说明 TreeMap是通过 Comparable或者Comparator来实现去重的
//        HashMap keyToValueMap = new HashMap<>(); // 1 - 说明 HashMap是通过 hashCode 与 equals来实现去重的

        Key myKey = new Key();
        keyToValueMap.put(myKey, "value one");
        keyToValueMap.put(myKey, "value two"); // 即便添加的是相同的key，但：key也会被排序，然后根据排序结果去重

        System.out.println(keyToValueMap.size()); // 1 - size取决于key-value的数量, key-value的数量取决于key是否重复, key是否重复取决于key的排序规则 - key的排序规则由compareTo()方法实现

        keyToValueMap.clear();
        System.out.println(keyToValueMap.size()); // 0

        keyToValueMap.put(new Key(), "value three");
        keyToValueMap.put(new Key(), "value four");

        System.out.println(keyToValueMap.size()); // 1
    }
}

// TreeMap中Key的定义 - 判断元素是否是重复元素的手段 - 相等的元素视为重复元素
class Key implements Comparable<Key> {

    @Override
    public int compareTo(Key o) {
//        return -1;  // 不管传入的对象是什么，当前对象 与 任何传入的参数相比，都认为当前对象更小 - 这种方法实现最终决定了 map的size=2
        return 0; // 不管传入的对象是什么，都认为与当前对象相等 - 这种实现最终决定了 map的size = 1
    }

    // 验证：TreeMap取出重复key的手段 不是“对象是否相等” - hashCode() + equals()
    @Override
    public int hashCode() {
        return 1; // 所有对象的hash值都为1
    }

    @Override
    public boolean equals(Object obj) {
        return true; // equals()比较的结果也总是相等的
    }
}
/*
启示：
1 TreeMap是通过 Comparable或者Comparator 来 实现去重的；
2 HashMap是通过 hashCode 与 equals来实现去重的；
3 TreeMap 对Key进行排序 所使用的方法：
    final int compare(Object k1, Object k2) {
        return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
            : comparator.compare((K)k1, (K)k2);
    }
    三目运算符的含义：
        如果comparator不为null，则：优先使用 比较器的compare()方法；
        如果为null，则：使用 Key中所实现的compareTo()方法。
        而如果两者都无法满足？？？则：抛出ClassCastException异常
 */