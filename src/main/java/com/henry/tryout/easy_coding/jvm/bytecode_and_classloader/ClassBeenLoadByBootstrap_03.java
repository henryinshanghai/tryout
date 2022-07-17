package com.henry.tryout.easy_coding.jvm.bytecode_and_classloader;

import sun.misc.Launcher;

import java.net.URL;

public class ClassBeenLoadByBootstrap_03 {
    public static void main(String[] args) {
        // Bootstrap类加载器 所加载的 类路径列表
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }
    }
}
/*
1 Bootstrap 默认有自己加载的类路径；
2 开发者可以 向 Bootstrap中添加 新的需要加载的类路径：
    手段：添加 JVM启动参数；
    -Xbootclasspath/a:/Users/henry/book/easyCoding/byJdk11/src
3 查看 JVM启动时加载了哪些jar包中的哪些类：
    手段： 添加 -XX:+TraceClassLoading 参数 —— 这在解决冲突时很有用
 */