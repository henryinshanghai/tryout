package com.henry.tryout.easy_coding.object_orientation_02.method_04.constructor_and_static;

// 验证：
// #1 创建 类的实例对象 时，会
//      先执行 父类和子类的静态代码块(先父后子)，
//      然后再执行 父类和子类的 构造方法
// #2 静态代码块 只会 在第一次对类进行实例化时 执行（再次实例化时 不会再执行）；
// 父类
public class Parent {
    // 父类的静态代码块
    static {
        System.out.println("Parent 静态代码块");
    }

    // 父类的构造方法
    public Parent() {
        System.out.println("Parent 构造方法");
    }
}

// 子类
class Son extends Parent {
    // 子类的静态代码块
    static {
        System.out.println("Son 静态代码块");
    }

    // 子类的构造方法
    Son() {
        System.out.println("Son 构造方法");
    }

    public static void main(String[] args) {
        // 对子类的第一次实例化
        new Son();

        // 对子类的第二次实例化
        new Son();
    }
}