package com.henry.tryout.easy_coding.jvm_04.memory_layout_02.thread_private_02.JVM_stack_01.behind_the_scene.JVM_common_commands.exe;

public class UnderlyingCommandDemo1 {
    public static void main(String[] args) {
        int i = 0;
        int a = i++;
        System.out.println(a); // 0
    }
    /*
    对应的字节码如下👇
        2: iload_1  // 从 局部变量表的slot_1上 读取操作数 到 操作数栈中
        3: iinc          1, 1   // 在局部变量表的位置?上进行 原地+1的操作
        6: istore_2 // 把 操作栈栈顶的元素 保存到 局部变量表的slot_2位置上
     */
}
