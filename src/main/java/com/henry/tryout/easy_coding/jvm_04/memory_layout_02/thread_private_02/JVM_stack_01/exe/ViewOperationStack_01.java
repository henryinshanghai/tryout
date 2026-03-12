package com.henry.tryout.easy_coding.jvm_04.memory_layout_02.thread_private_02.JVM_stack_01.exe;

// 验证：JVM虚拟机栈是怎样实现一个方法的调用的
// 手段：查看方法调用时，实际执行的字节码指令 - 工具：jclasslib
// LOAD指令：把 局部变量表中的变量 加载到 操作数栈中
// STORE指令：把 操作栈栈顶的数 存储到 局部变量表的对应位置上
public class ViewOperationStack_01 {
    public static void main(String[] args) {
        Test test = new Test();
        int result = test.simpleMethod();
        System.out.println(result);
    }
}

class Test {
    public int simpleMethod() {
        int x = 13;
        int y = 14;
        int z = x + y;

        return z;
    }
}
