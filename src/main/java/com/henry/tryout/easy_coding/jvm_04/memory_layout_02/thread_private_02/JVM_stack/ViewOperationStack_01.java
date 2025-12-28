package com.henry.tryout.easy_coding.jvm_04.memory_layout_02.thread_private_02.JVM_stack;

// 验证：JVM虚拟机栈是怎样实现一个方法的调用的
// 手段：查看方法调用时，实际执行的字节码指令 - 工具：jclasslib
// 没啥卵用 看不懂~
public class ViewOperationStack_01 {
    public static void main(String[] args) {
        Test test = new Test();
        int result = test.simpleMethod();
        System.out.println(result);
    }
}

class Test {
    public int simpleMethod() {
        // 加载(LOAD)局部变量表中的局部变量到 方法帧的操作栈中
        int x = 13;
        int y = 14;
        // 在操作栈中执行运算，并把运算结果 写回到(STORE)局部变量表中
        int z = x + y;

        // 返回栈顶元素
        return z;
    }
}
