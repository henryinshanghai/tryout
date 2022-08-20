package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02;

// 获取到当前类 的 类加载器列表
public class ViewClassLoaders_02 {
    public static void main(String[] args) {
        ClassLoader c = ViewClassLoaders_02.class.getClassLoader();
        System.out.println(c);
        ClassLoader c1 = c.getParent();
        System.out.println(c1);
        ClassLoader c2 = c1.getParent();
        System.out.println(c2); // 这里会打印null - 因为Bootstrap是使用C/C++实现的，不在JVM的体系中。
    }
}
