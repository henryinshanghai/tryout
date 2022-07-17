package com.henry.tryout.easy_coding.jvm.object_instance;

public class InstanceDemo {
    public static void main(String[] args) {
        Object ref = new Object();
    }
}
/*
stack=2, locals=1, args_size=1
    NEW java/lang/Object
    DUP
    INVOKESPECIAL java/lang/Object.<init> ()V
    ASTORE_1
    LocalVariableTable:
        Start Length Slot Name Signature
        8     1      0    ref   Ljava/lang/Object;

NEW: 如果找不到CLass对象，就会进行 类的加载。
    特征：
        1 加载成功后，会为对象 在堆中分配内存 - 从 Object开始到 当前类 路径上的所有属性都需要分配内存空间。
        2 在空间分配完成后，会进行 零值初始化。
        3 引用本身也是占据存储空间的，作为变量，引用会占用4个字节。
        4 NEW指令执行完成后，会把 指向实例对象的引用变量 给压入虚拟机栈顶。

DUP：
    作用：在栈顶 对引用变量进行复制 - 这样栈顶会有两个指向 堆内实例对象的引用变量。
    特征：
        1 如果 init方法有参数，则：会把参数压入操作栈中；
        2 被压到底下的引用，用来赋值 或者 保存到局部变量表；
        3 栈顶的引用，作为 句柄调用相关方法。

INVOKESPECIAL
    作用：
        1 调用对象的实例方法；
        2 使用 栈顶的引用变量 来调用 <init>方法；
    <clinit> 是 类初始化时所执行的方法，
    <init>是 对象初始化时所执行的方法。



 */