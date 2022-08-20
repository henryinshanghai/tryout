package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02;

import java.lang.reflect.Field;

public class ClassTest_drill {
    private static Class<One> one = One.class;
    private static Class<Another> another = Another.class;


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 从Class对象中获取到 类的一个实例对象
        One oneObject = one.newInstance();
        oneObject.call();

        /* 修改实例对象的私有属性的值 👇*/
        Field inner = one.getDeclaredField("inner");
        inner.setAccessible(true);
        inner.set(oneObject, "henry test");

        System.out.println(oneObject.getInner());
    }
}
