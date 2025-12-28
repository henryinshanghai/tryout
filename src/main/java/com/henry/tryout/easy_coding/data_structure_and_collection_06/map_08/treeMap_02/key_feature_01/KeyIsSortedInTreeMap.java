package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.treeMap_02.key_feature_01;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// 验证：TreeMap中的Key-Value 是 Key排列有序的
// 手段：从map中 取出key的集合，打印 key集合中的每一个元素 - 验证 key的打印结果 是不是有序的
public class KeyIsSortedInTreeMap {
    public static void main(String[] args) {
        Map<String, Integer> nameToAgeMap = new TreeMap<>();

        nameToAgeMap.put("bruce", 99);
        nameToAgeMap.put("henry", 25);
        nameToAgeMap.put("jane", 26);
        nameToAgeMap.put("ada", 28);
        nameToAgeMap.put("xiaogao", 38);
        nameToAgeMap.put("xinrui", 29);

        // treeMap中的 key是排列有序的 - 遍历时，得到的元素 是 自然排序的
        System.out.println("使用for-each语法👇");
        Set<String> names = nameToAgeMap.keySet();
        for (String name : names) {
            Integer age = nameToAgeMap.get(name);
            System.out.println(name + "->" + age);
        }

        // 遍历手段2： map.forEach(lambda表达式)
        System.out.println("使用map对象的forEach(<lambda>)方法👇");
        nameToAgeMap.forEach((key, value) -> {
            System.out.println("key: " + key + " -> " + value);
        });
    }
}
