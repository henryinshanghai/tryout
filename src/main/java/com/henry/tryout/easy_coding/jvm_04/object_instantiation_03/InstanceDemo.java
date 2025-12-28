package com.henry.tryout.easy_coding.jvm_04.object_instantiation_03;

// 验证：new对象时,JVM中到底发生了什么？
// 手段：查看new 所对应的字节码指令
public class InstanceDemo {
    public static void main(String[] args) {
        // 创建一个Object类的实例对象，并 把它赋值到 一个引用变量ref上
        Object ref = new Object();
    }
}
