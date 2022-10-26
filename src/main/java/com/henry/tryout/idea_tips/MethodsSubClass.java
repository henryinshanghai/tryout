package com.henry.tryout.idea_tips;

public class MethodsSubClass extends MethodsBaseClass{

    // 实现抽象方法快捷键 - Ctrl + I
    @Override
    public void myName() {
        System.out.println("my name is Henry");
    }

    // 重写父类方法的快捷键 - Ctrl + O
    @Override
    public void myScore() {
        System.out.println("my score is 100.");
    }
}
