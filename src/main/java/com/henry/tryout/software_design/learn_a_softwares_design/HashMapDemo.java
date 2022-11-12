package com.henry.tryout.software_design.learn_a_softwares_design;

import java.util.HashMap;
import java.util.Map;

// 验证HashMap的 模型、接口、实现
public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> nameToAge = new HashMap(16);

        // 接口1 - put()方法
        nameToAge.put("Henry", 28);
        nameToAge.put("Jane", 28);
        nameToAge.put("Cris", 30);

        // 接口2： entrySet()方法
        for (Map.Entry<String, Integer> entry : nameToAge.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
