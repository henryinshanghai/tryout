package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02;

import java.io.FileNotFoundException;

/*
自定义类加载器
 */
public class HowToDefineClassLoader_05 extends ClassLoader { // 1 继承 ClassLoader；

    // 2 重写 findClass()方法；
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                // 3 调用defineClass()方法；
                return defineClass(name, result, 0, result.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        // 从自定义的路径中 来 加载类 - todo 这是需要自定义的内容
        return null;
    }

    public static void main(String[] args) {
        HowToDefineClassLoader_05 customClassLoader = new HowToDefineClassLoader_05();
        try {
            // 从实例对象 获取到 其所属类的Class对象 - 手段:Class.forName(xxx)
            Class<?> clazz = Class.forName("One", true, customClassLoader);
            Object obj = clazz.newInstance();
            // 获取到 加载类时所使用到的 类加载器列表
            System.out.println(obj.getClass().getClassLoader());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

/*
应用场景：
fact1： 中间件一般都会有自己依赖的jar包；
fact2： 当工程中使用了多个框架的时候，就很有可能 会需要进行类的仲裁；
fact3： 进行仲裁时，会按照某种规则 来 统一指定jar的版本
    基于此，如果两个类的包路径、类名都相同，则：会引起 类冲突。
    解决方案：主流的容器类框架 都会自定义类加载器 - 以此隔离不同中间件中的类，避免出现类冲突
 */
