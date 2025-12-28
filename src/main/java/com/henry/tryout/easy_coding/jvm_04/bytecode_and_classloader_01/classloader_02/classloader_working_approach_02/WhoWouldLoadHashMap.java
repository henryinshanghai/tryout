package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02.classloader_working_approach_02;

import java.util.HashMap;
import java.util.Map;

// 验证：
// #1 可以通过 添加VM选项 -XX:+TraceClassLoading 来 查看 JVM启动时具体加载了哪些类？
// #2 可以使用 IDEA提供的条件断点功能 来 查看 特定类的加载上下文（避免从太多类信息中 找花眼）
public class WhoWouldLoadHashMap {
    public static void main(String[] args) {
        // 添加断点的位置 - Launcher类 175行 / loadClass()第一行
        // 条件断点内容 - var1.equals("java.util.HashMap")
        // 然后要怎么查看？HashMap的加载上下文呢？ 母鸡~
        ClassLoader currentUsingLoader = WhoWouldLoadHashMap.class.getClassLoader(); // AppClassLoader
        System.out.println("加载当前类 所使用的类加载器 为：" + currentUsingLoader);

        // 🐖 主动触发 加载HashMap（否则，JVM 不会主动加载 HashMap??）
        Map<String, String> map = new HashMap<>();

        map.put("henry", "95");
        map.put("Alicia", "95");
        map.put("ben", "90");
    }
}
