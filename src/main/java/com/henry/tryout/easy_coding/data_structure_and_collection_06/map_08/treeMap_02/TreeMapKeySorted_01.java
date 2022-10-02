package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.treeMap_02;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// 验证：TreeMap中的Key-Value 是Key排列有序的
// 手段：从map中取出key的集合，打印key集合中的每一个元素 - 验证key的打印结果是不是有序的
public class TreeMapKeySorted_01 {
    public static void main(String[] args) {
        Map<String, Integer> nameToAgeMap = new TreeMap<>();

        nameToAgeMap.put("henry", 25);
        nameToAgeMap.put("jane", 26);
        nameToAgeMap.put("ada", 28);

        // treeMap中的key是排列有序的 - 遍历时，得到的元素是自然排序的
        Set<String> names = nameToAgeMap.keySet();
        for (String name : names) {
            Integer age = nameToAgeMap.get(name);
            System.out.println(name + "->" + age);
        }

        // 遍历手段2： map.forEach(lambda表达式)
        nameToAgeMap.forEach((key, value) -> {
            System.out.println("key: " + key + " -> " + value);
        });
    }
}
