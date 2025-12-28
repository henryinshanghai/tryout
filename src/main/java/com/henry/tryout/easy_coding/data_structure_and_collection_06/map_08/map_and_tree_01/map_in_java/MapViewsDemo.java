package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.map_and_tree_01.map_in_java;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 验证：对于HashMap由entrySet(), keySet(), values()方法 得到的视图，只能对视图做相当有限的编辑操作
public class MapViewsDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> nameToAge = new HashMap<>();

        nameToAge.put("henry", 31);
        nameToAge.put("jack", 32);
        nameToAge.put("chengyu", 29);
        nameToAge.put("lvpeng", 30);

        /*********************
         * 获取map对象的几个视图
         *********************/
        /* 由keySet()得到的视图 names */
        Set<String> names = nameToAge.keySet();

        // 遍历 names
        System.out.println("查看由keySet()方法得到的视图👇");
        for (String currentName : names) {
            System.out.print(currentName + " ");
        }
        System.out.println();

        // 增删改
//        names.add("nina"); // UnsupportedOperationException
        names.remove("henry"); // remove()调用成功
        // set没有修改元素的API

        // 编辑操作后，查看视图
        System.out.println("对names视图编辑操作后的names👇");
        for (String name : names) {
            System.out.print(name);
        }
        System.out.println();

        // 编辑操作后，查看原始的map
        System.out.println("对names视图编辑操作后，查看原始的map👇");
        for (Map.Entry<String, Integer> currentEntry : nameToAge.entrySet()) {
            System.out.print(currentEntry.getKey() + "->" + currentEntry.getValue() + "; ");
        }
        System.out.println();

        /* 由values()得到的视图 ages */
        Collection<Integer> ages = nameToAge.values();

        // 遍历ages
        for (Integer age : ages) {
            System.out.print(age + ", ");
        }
        System.out.println();

        // 增删改
//        ages.add(100); // UnsupportedOperationException
        ages.remove(32); // remove()调用成功
        // Collection没有提供 修改元素的API

        // 编辑之后，查看视图
        System.out.println("对ages视图编辑操作之后的ages👇");
        for (Integer age : ages) {
            System.out.print(age + ", ");
        }
        System.out.println();

        // 编辑之后，查看原始的map
        System.out.println("对ages视图编辑操作之后 原始的map");
        for (Map.Entry<String, Integer> currentEntry : nameToAge.entrySet()) {
            System.out.print(currentEntry.getKey() + "->" + currentEntry.getValue() + ", ");
        }
        System.out.println();

        /* keySet()视图 */
        Set<Map.Entry<String, Integer>> entrySet = nameToAge.entrySet();

        // 遍历
        System.out.println("查看 由entrySet()方法 得到的视图👇");
        for (Map.Entry<String, Integer> currentEntry : entrySet) {
            System.out.print(currentEntry.getKey() + "->" + currentEntry.getValue() + ", ");
        }
        System.out.println();

        // 增删改
//        entrySet.add(new AbstractMap.SimpleEntry<>("nina", 32)); // UnsupportedOperationException
        entrySet.remove("lvpeng"); // 调用成功
        for (Map.Entry<String, Integer> currentEntry : entrySet) {
            if ("chengyu".equals(currentEntry.getKey())) {
                currentEntry.setValue(99);
            }
        }

        // 编辑操作后，查看由entrySet()得到的视图
        System.out.println("编辑操作后的entrySet👇");
        for (Map.Entry<String, Integer> currentEntry : entrySet) {
            System.out.print(currentEntry.getKey() + "->" + currentEntry.getValue() + ", ");
        }
        System.out.println();

        System.out.println("编辑操作后的原始map👇");
        for (Map.Entry<String, Integer> currentEntry : nameToAge.entrySet()) {
            System.out.print(currentEntry.getKey() + "->" + currentEntry.getValue() + ", ");
        }
        System.out.println();
    }
}
