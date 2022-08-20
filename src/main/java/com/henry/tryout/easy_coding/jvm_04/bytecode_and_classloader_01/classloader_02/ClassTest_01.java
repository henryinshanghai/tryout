package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02;

import java.lang.reflect.Field;

public class ClassTest_01 {
    // 数组类型有一个魔法属性： length - 用来获取数组长度
    private static int[] array = new int[3];
    private static int length = array.length;

    // 任何由class定义的类，都会有一个魔法属性：class - 用来获取类的Class类对象
    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 创建类的实例对象 - 手段：通过 Class对象的newInstance()方法
        One oneObject = one.newInstance();
        oneObject.call(); // 输出 Hello World!

        Another anotherObject = ClassTest_01.another.newInstance();
        anotherObject.speak(); // 输出 easy coding

        /* 使用 类的Class对象 来 修改类的私有属性 👇 */
        // 1 获取私有属性对象 Field - 手段：通过 Class对象的 getDeclaredField()方法
        Field privateFiledInOne = one.getDeclaredField("inner");

        // 2 在修改属性之前，先设置 私有对象可以访问和修改 - 手段： setAccessible()
        privateFiledInOne.setAccessible(true);

        // 3 设置 实例对象oneObject 对应的私有属性的值 - 手段：field对象的set()方法
        privateFiledInOne.set(oneObject, "world changed");
        System.out.println(oneObject.getInner());
    }
}

class One {
    private String inner = "time files.";

    public void call() {
        System.out.println("Hello world.");
    }

    public String getInner() {
        return inner;
    }
}

class Another {
    public void speak() {
        System.out.println("easy coding.");
    }
}
/*

Class newInstance()方法 在 JDK9.0时已经被设置为 过时；
new VS. newInstance()
    new 的特征：
        1 属于强制类型校验，可以调用任何构造方法；
        2 在使用new A 的时候，类A可以没有被加载过；
    newInstance()的特征：
        1 属于弱类型，只能调用 无参数的构造方法；
        2 如果类A没有 默认的构造方法的话，调用newInstance()方法会抛出异常 InstantiationException；
        3 如果构造方法没有权限访问，则会抛出 IllegalAccessException异常。
总结：Java通过 类加载器 来 把类的实现 与 类的定义 进行解耦？？

 */