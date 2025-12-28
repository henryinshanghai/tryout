package com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02.self_defined_classloader_03;

import java.io.FileNotFoundException;

// 验证：
// #1 为了使用 自定义的类加载器 来 加载指定的类，需要 避免该类被高级别的类加载器 加载
// 手段：确保 待加载的类 只存在于 指定的自定义目录中，而不存在于 当前项目的classpath 中；
// 效果：AppClassLoader找不到 待加载的类 后，加载请求 就会被 回退到 自定义的类加载器中，进而调用 其findClass()方法
// #2 自定义类加载器的SOP：① 继承自ClassLoader；② 重写findClass()方法；③ 在重写的findClass()方法中，调用 defineClass()方法；
public class SelfDefinedClassLoader extends ClassLoader { // 1 继承 ClassLoader；

    // 2 重写 findClass()方法；
    // 🐖 运行前提：待加载的类 没有 被父加载器 给提前加载
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
        // 从自定义的路径中 来 加载类
        return null;
    }

    public static void main(String[] args) {
        SelfDefinedClassLoader customClassLoader = new SelfDefinedClassLoader();
        try {
            // 从实例对象 获取到 其所属类的Class对象 - 手段:Class.forName(xxx)
            Class<?> clazz = Class.forName("com.henry.tryout.easy_coding.jvm_04.bytecode_and_classloader_01.classloader_02.self_defined_classloader_03.One", true, customClassLoader);
            Object obj = clazz.newInstance();

            // 获取到 加载One类时 所使用到的 类加载器
            // 使用的类加载器 是AppClassLoader，而不是 自定义的类加载器。因为 溯源委派加载模型中，父加载器 已经完成了 对One的加载
            System.out.println(obj.getClass().getClassLoader());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class One {

}

/*
应用场景：
fact1： 中间件一般都会有自己依赖的jar包；
fact2： 当工程中使用了多个框架的时候，就很有可能 会需要进行类的仲裁；
fact3： 进行仲裁时，会按照某种规则 来 统一指定jar的版本
    基于此，如果两个类的包路径、类名都相同，则：会引起 类冲突。
    解决方案：主流的容器类框架 都会自定义类加载器 - 以此隔离不同中间件中的类，避免出现类冲突
 */
