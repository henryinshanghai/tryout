package com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02;

import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        // 新版本的 put()方法的实现已经有一些不一样，扩容的操作实现在 resize()方法中 有点子复杂的
        // 把 capacity参数 处理成为 2的次数的方法 - tableSizeFor()
        map.put("henry", "28");
        map.put("alicia", "28");
        map.put("quinta", "32");

    }
}
