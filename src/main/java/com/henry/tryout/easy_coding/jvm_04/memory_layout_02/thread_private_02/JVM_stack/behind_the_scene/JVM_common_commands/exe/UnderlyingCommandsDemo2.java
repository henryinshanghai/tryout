package com.henry.tryout.easy_coding.jvm_04.memory_layout_02.thread_private_02.JVM_stack.behind_the_scene.JVM_common_commands.exe;

public class UnderlyingCommandsDemo2 {
    public static void main(String[] args) {
        int i = 0;
        int a = ++i;
        System.out.println(a); // 1
    }
    /*
        对应的字节码指令如下👇
            2: iinc          1, 1 // 在局部变量表的当前位置 原地+1
            5: iload_1      // 从 局部变量表的slot_1 读取元素 到 操作栈栈顶
            6: istore_2     // 把 操作栈栈顶的元素 写入到 局部变量表的slot_2
     */
}
