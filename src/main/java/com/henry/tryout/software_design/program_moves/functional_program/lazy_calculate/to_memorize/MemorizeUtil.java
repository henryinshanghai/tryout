package com.henry.tryout.software_design.program_moves.functional_program.lazy_calculate.to_memorize;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

// 验证：???
public class MemorizeUtil {

    // #1 编写泛型方法的语法：???
    // #2 memorize()方法的作用：？？？
    /*
        参数：传入一个lambda表达式/函数式接口类型的参数
        返回值：返回一个函数式接口类型的结果
        分析作用 - 对于传入的delegate参数，做了什么处理？
            把传入的delegate参数中的值，设置进一个reference对象中。并返回reference对象所包含的值
     */
    public static <T> Supplier<T> memorize(Supplier<T> delegate) {
        // #1 创建一个 reference对象
        AtomicReference<T> value = new AtomicReference<>();

        // 返回一个函数/lambda表达式
        return () -> {
            // #2 从空的reference对象中，get出val - val的泛型类型在创建Reference对象时指定
            T val = value.get();
            if (val == null) {
                synchronized (value) {
                    val = value.get();
                    // #3 判断reference对象value中所包含的值是否为null
                    if (val == null) {
                        // #4 如果为null,则：从传入的delegate参数中get出值，然后根据它生成对象绑定到reference对象所包含的变量val上
                        val = Objects.requireNonNull(delegate.get());
                        // #5 把新的val值设置回去到reference对象中
                        value.set(val);
                    }
                }
            }
            // #6 返回reference对象中所包含的值
            return val;
        };
    }
}
