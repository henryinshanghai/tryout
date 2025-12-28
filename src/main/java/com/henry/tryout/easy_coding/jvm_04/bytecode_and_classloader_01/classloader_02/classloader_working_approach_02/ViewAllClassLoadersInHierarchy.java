package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02.classloader_working_approach_02;

// 验证：
// #1 使用 类的Class对象的 getClassLoader()方法 能够得到 用于加载当前类的 类加载器
// #2 使用 类加载器的getParent()方法 能够得到 类加载器的父加载器；
public class ViewAllClassLoadersInHierarchy {
    public static void main(String[] args) {
        // 打印 用于加载当前类 的 类加载器
        ClassLoader currentUsingLoader = ViewAllClassLoadersInHierarchy.class.getClassLoader();
        System.out.println(currentUsingLoader); // AppClassLoader

        // 其 父加载器
        ClassLoader itsParentLoader = currentUsingLoader.getParent();
        System.out.println(itsParentLoader); // ExtClassLoader

        ClassLoader itsGrandParentLoader = itsParentLoader.getParent();
        System.out.println(itsGrandParentLoader); // 这里会打印null - 因为Bootstrap是使用C/C++实现的，不在JVM的体系中。
    }
}
