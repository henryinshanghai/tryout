package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02.load_a_class_phases_01;

import java.lang.reflect.Field;

// 验证：
// #1 任何 由class关键字所定义的类，都有一个class属性 来 由类名 获取到 其Class对象；
// #2 Java中，通过 类加载器的设计 实现了 对 类的定义 与 类的实现 的解耦 - 在运行时，绑定 定义 与 实现；
// #3 使用类的Class对象，开发者 能够实现 对类的私有变量的读取与修改；
public class ClassTest {
    // 数组类型有一个魔法属性： length - 用来获取数组长度
    private static int[] array = new int[3];
    private static int length = array.length;

    // 任何 由class定义的类，都会有一个 魔法属性：class - 用来获取 类的Class类对象
    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 创建 类的实例对象 - 手段：通过 Class对象的 newInstance()方法
        /*
            Class类下的newInstance() 在JDK9中 已经置为过时，推荐使用 getDeclaredConstructor().newInstance()的方式；
            new VS. newInstance
            new是 强类型校验，因此：
                ① 可以调用 任何一个构造方法；② 在使用new操作的时候，这个类 可以没有被加载过；
            而Class类下的newInstance()是 弱类型，因此：
                ① 只能够 调用 无参数的构造方法；
                    如果 不存在 无参构造方法 的话，就会抛出 InstantiationException异常；
                    如果 此构造方法 没有权限访问，则 抛出 IllegalAccessException异常。
            Java 通过 类加载器 来 把 类的实现(类中方法体内的代码) 与 类的定义(类中的声明部分) 进行解耦，
            所以 它是 实现 面向接口编程、依赖倒置 的必然选择。
            原理：
                类加载器 在运行时 动态绑定 定义 与 实现，
                通过 双亲委派模型 来 保证 核心定义安全，同时 允许 灵活扩展 实现。
         */
        One oneObject = one.newInstance();
        oneObject.call(); // 输出 Hello World!

        Another anotherObject = ClassTest.another.newInstance();
        anotherObject.speak(); // 输出 easy coding

        /* 使用 类的Class对象 来 修改类的私有属性 👇 */
        // 1 获取 私有属性对象Field - 手段：通过 Class对象的 getDeclaredField()方法
        // 可以使用 类似的方式 来 获取其他声明
        Field privateFiledInOne = one.getDeclaredField("inner");

        // 2 在 修改属性 之前，先设置 私有对象 可以访问和修改 - 手段： setAccessible()
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