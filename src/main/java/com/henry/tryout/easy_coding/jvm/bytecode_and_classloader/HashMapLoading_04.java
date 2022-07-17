package com.henry.tryout.easy_coding.jvm.bytecode_and_classloader;

import java.util.HashMap;
import java.util.Map;

public class HashMapLoading_04 {
    public static void main(String[] args) {
        // 怎么添加 VM options的参数？
        Map<String, String> map = new HashMap<>();

        // 在 Launcher.java 的 loadClass(String, boolean)上添加 条件断点  var1.equals("java.util.HashMap")就能够 拦截到 HashMap的加载过程
        map.put("henry", "95");
        map.put("Alicia", "95");
        map.put("ben", "90");
    }
}
