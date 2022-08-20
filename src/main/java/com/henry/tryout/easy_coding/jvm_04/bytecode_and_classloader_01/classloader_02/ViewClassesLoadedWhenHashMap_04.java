package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02;

import sun.misc.Launcher;

import java.util.HashMap;
import java.util.Map;

// 查看类启动时，哪些类被加载到了？ - 手段：添加VM选项 -XX:+TraceClassLoading
/*
控制台打印了太多jar路径，如果想要查看 特定类的加载上下文，怎么办？
手段： IDEA提供的条件断点功能；
 */
public class ViewClassesLoadedWhenHashMap_04 {
    public static void main(String[] args) {
        // 添加断点的位置 - loadClass()第一行
        // 条件断点内容 - var1.equals("java.util.HashMap")
        // 然后要怎么查看？HashMap的加载上下文呢？ 母鸡~
        Launcher launcher = Launcher.getLauncher();
        Map<String, String> map = new HashMap<>();

        map.put("henry", "95");
        map.put("Alicia", "95");
        map.put("ben", "90");
    }
}
