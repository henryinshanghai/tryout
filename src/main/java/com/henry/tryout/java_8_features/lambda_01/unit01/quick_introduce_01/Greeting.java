package com.henry.tryout.java_8_features.lambda_01.unit01.quick_introduce_01;
/*
    声明一个 “问候”的接口
 */
@FunctionalInterface // 作用：1 声明这是一个 函数式接口 - 这会提供一些编译期的约束（只能存在有一个抽象方法）； 2 明示程序员该接口的作用
public interface Greeting {

    // 只有一个抽象方法
    void perform();

    // void another(); 在尝试添加另外一个抽象方法的时候，编译期就会提示报错

}
