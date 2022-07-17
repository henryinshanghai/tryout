package com.henry.tryout.easy_coding.jvm.bytecode_and_classloader_01;

import java.lang.reflect.Field;

public class ClassTest_01 {
    // 数组类型有一个魔法属性： length - 用来获取数组长度
    private static int[] array = new int[3];
    private static int length = array.length;

    // 任何由class定义的类，都会有一个魔法属性：class - 用来获取类的Class类对象
    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 创建类对象 - 手段：通过 newInstance方法  bookmark_one
        One oneObject = one.newInstance();
        oneObject.call();

        Another anotherObject = ClassTest_01.another.newInstance();
        anotherObject.speak();

        // 获取私有属性对象 Field - 手段：通过 one这个大写的Class对象 bookmark_two
        Field privateFiledInOne = one.getDeclaredField("inner");

        // 在修改属性之前，先设置 私有对象可以访问和修改 bookmark_03
        privateFiledInOne.setAccessible(true);

        // 设置 私有属性的值 - 这会成功修改 类的私有属性 inner变量的值为 world changed
        privateFiledInOne.set(oneObject, "world changed");
        System.out.println(oneObject.getInner());
    }
}
/*
bookmark_one:
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
    这类的实现 不就是 类的定义吗？

bookmark_two:
在运行时可以通过 这种方式来获取到 类的声明信息，如 注解，方法。

bookmark_03:
可以在类外来修改 private成员。但是在修改之前需要 setAccessible(true)
否则 会抛出异常
 */