package com.henry.tryout.easy_coding.code_specification;

public class IfSingleStatementTest {
    public static void main(String[] args) {
        // 以下单语句编译报错 - 因为单语句在没有添加大括号的情况时，所声明的变量不可能再被使用
        // 编译器认为这样的变量没有任何意义
        /*
        if (true) int x;
        for (; ; ) int y;
        while(true) int z;
         */
    }
}
