package com.henry.tryout.easy_coding.object_orientation_02.method_04.parameter;

// 验证：Java中的参数传递 都是 值复制的传递过程。
// 如果参数是 基本数据类型，复制的 是 参数的值；
// 如果参数是 引用类型，复制的 是 对象的地址（等同于 给对象添加了一个新的引用）；
public class ParamPassing {
    private static int intStatic = 222;
    public static String stringStatic = "old string";
    public static StringBuilder stringBuilderStatic = new StringBuilder("old stringBuilder");

    public static void main(String[] args) {
        // 传入 静态变量的引用 作为 实际参数
        method(intStatic);
        method(stringStatic);
        method(stringBuilderStatic, stringBuilderStatic);

        /* 方法执行后，打印 静态变量的值 */
        // 输出仍旧是222
        System.out.println(intStatic);
        // 无参方法调用后，反而修改成为888
        System.out.println(intStatic);

        // 输出 仍旧是 old string
        System.out.println(stringStatic);

        // 输出结果 old stringBuilder.method.first-method.second
        System.out.println(stringBuilderStatic);
    }

    // 验证：当 实际参数是引用变量 时，传递给形参的 是 实际参数 所指向的对象的首地址 的一个副本（相当于 给对象 添加了一个新的名字）
    private static void method(StringBuilder stringBuilderStatic1,
                               StringBuilder stringBuilderStatic2) {
        stringBuilderStatic1.append(".method.first-");
        stringBuilderStatic2.append("method.second-");

        // 为 引用 重新赋值
        stringBuilderStatic1 = new StringBuilder("new stringBuilder");
        stringBuilderStatic1.append("new method's append");
    }

    private static void method(String stringStatic) {
        // 🐖 String是不可变对象，所以这里 相当于 引用副本 重新指向了 新的字符串
        stringStatic = "new string";
    }


    // 验证：当 实际参数 是基本数据类型 时，传递给形参的 是 实际参数的值 的一个副本
    private static void method(int intStatic) {
        // 这里 只是对副本的操作，因此 不会影响 静态变量
        intStatic = 777;
    }

    private static void method() {
        // 🐖 这里在直接操作 静态变量
        intStatic = 888;
    }
}
