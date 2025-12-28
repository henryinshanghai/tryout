package com.henry.tryout.easy_coding.object_orientation_02.method_04.override.stackoverflowDemo;

// 验证：
// #1 如果递归方法 没有提供 终止条件，会导致 栈溢出error
// #2 子类中 使用super关键字 可以 调用父类中的方法
// #3 当前类中 使用this关键字 可以 调用当前类中的实例方法
public class Father {
    protected void doSomething() {
        System.out.println("Father's doSomething");
        // 当前类中，可以使用 this关键字 来 调用其中的实例方法
        // 如果递归方法 没有提供 终止条件，会导致 栈溢出error
        this.doSomething();
    }

    public static void main(String[] args) {
        Father father = new Father();
        father.doSomething();
    }
}

class Son extends Father {

    @Override
    public void doSomething() {
        System.out.println("Son's doSomething");

        // 子类中，可以使用 super关键字 来 调用父类中的方法
        super.doSomething();
    }
}
