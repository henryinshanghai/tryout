package com.henry.tryout.easy_coding.object_orientation_02.method_04.generic;

// 验证：关于泛型的几点认识👇
// #1 <>中的每个元素 都指代一种 未知类型；比如 <String> 就是一种未知类型的代号，而不是String类型
// #2 <>的位置 只能 在类名之后 或者 在方法返回值之前
// #3 在 使用泛型类型 时，它只具备 执行Object类型方法的能力
// #4 在 编译得到的字节码 中，不存在 任何所谓的“泛型”，就只有Object类型 aka 泛型只是一种 编写代码时的语法检查
public class GenericDefinitionDemo<T> {

    /**
     * 泛型方法
     *
     * @param string    未知类型1的形式参数
     * @param alibaba   未知类型3的形式参数
     * @param <String>  未知类型1的代号     在方法体中被用到
     * @param <T>       未知类型2的代号    没有被用到
     * @param <Alibaba> 未知类型3的代号    在方法参数中被用到
     * @return
     */
    static <String, T, Alibaba> String get(String string, Alibaba alibaba) {
        return string;
    }

    public static void main(String[] args) {
        Integer first = 222;
        Long second = 333L;

        // 调用get()方法
        Integer result = get(first, second);
    }
}
