package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02.classloader_working_approach_02;

import sun.misc.Launcher;

import java.net.URL;

// 验证：可以通过 Launcher.getBootstrapClassPath().getURLs() 来 查看 由 BootStrap类加载器 所加载的所有的类
public class ViewAllJarsLoadByBootstrap {
    public static void main(String[] args) {
        // 获取 Bootstrap类加载器 所加载的 类路径列表 - 手段：Launcher.getBootstrapClassPath().getURLs()
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }
    }
}
/*
1 Bootstrap 默认有自己加载的类路径；
2 开发者可以 向 Bootstrap中添加 新的需要被加载的类路径：
    手段：添加 JVM启动参数；
        -Xbootclasspath/<classes_path>
3 查看 JVM启动时 加载了 哪些jar包中的 哪些类：
    手段： 添加 -XX:+TraceClassLoading 参数 —— 这在解决冲突时很有用;
    特征： 这样会有 很多的类 被打印到 控制台；
 */