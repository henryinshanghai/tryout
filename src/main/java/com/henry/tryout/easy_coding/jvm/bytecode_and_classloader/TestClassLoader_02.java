package com.henry.tryout.easy_coding.jvm.bytecode_and_classloader;

public class TestClassLoader_02 {
    public static void main(String[] args) {
        ClassLoader c = TestClassLoader_02.class.getClassLoader();
        System.out.println(c);
        ClassLoader c1 = c.getParent();
        System.out.println(c1);
        ClassLoader c2 = c1.getParent();
        System.out.println(c2); // 这里会打印null - 因为Bootstrap是使用C/C++实现的，不在JVM的体系中。
    }
}
