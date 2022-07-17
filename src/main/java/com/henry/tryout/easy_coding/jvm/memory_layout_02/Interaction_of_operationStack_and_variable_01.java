package com.henry.tryout.easy_coding.jvm.memory_layout_02;

public class Interaction_of_operationStack_and_variable_01 {
    public static void main(String[] args) {
        int demo = simpleMethod();
        System.out.println(demo);
    }

    private static int simpleMethod() {
        int x = 13;
        int y = 14;
        int z = x + y;

        return z;
    }
}
/*
假装看到了字节码信息：
Code:
    stack=2, locals=4, args_Size=1 // 最大栈深度为2， 局部变量个数为4
        BIPUSH 13 // 把常量13 压入操作栈
        ISTORE_1 // 把常量13 保存在 局部变量表的slot_1中

        BIPUSH 14 // 把常量14 压入操作栈
        ISTORE_2 // 并保存到 局部变量表的slot_2中

        ILOAD_1 // 把 局部变量表的slot_1元素（int x）压入 操作栈中
        ILOAD_2 // 把局部变量表的slot_2元素(int y) 压入操作栈中
        IADD // 把上面的两个数字都取出来，在CPU中执行相加的操作，然后压回到 操作栈的栈顶
        ISTORE_3 // 把栈顶的结果存储到 局部变量表的slot_3中

        ILOAD_3
        IRETURN // 返回栈顶元素的值

启示：
1 局部变量表就像是一个中药柜，有很多有编号的小盒子。
字节码指令 ISTORE_1的作用就是 打开 1号盒子，然后把栈顶的数字13存储进去。
栈是一个很深的竖桶，任何时间都只能够对桶口的元素进行操作 - 因此数据的存取只能发生在栈顶。

特征：有些指令可以直接在 盒子中进行，比如 iinc指令。对盒子中的数值进行+1操作
i++ 与 ++i的区别：
a=i++

0：iload_1 // 从局部变量表的第一号盒子中取出数字，并压入栈顶
1: iinc 1,1 // 直接在盒子中实现 +1的操作
4: istore_2 // 把栈顶元素 赋值给 a

a=++i
0: iinc 1,1 // 在局部变量表的1号盒子中执行 +1的操作
3: iload_1 把1号盒子中的数字 压入栈顶
4: istore_2 // 把 +1后的值 赋值给a

特征： i++并不是一个原子操作，即便使用 volatile修饰，多个线程同时写时，仍旧会出现数据覆盖的情况




 */