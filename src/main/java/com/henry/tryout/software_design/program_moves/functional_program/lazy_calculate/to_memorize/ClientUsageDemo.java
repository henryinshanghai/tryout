package com.henry.tryout.software_design.program_moves.functional_program.lazy_calculate.to_memorize;

import java.util.function.Supplier;

import static com.henry.tryout.software_design.program_moves.functional_program.lazy_calculate.to_memorize.MemorizeUtil.memorize;

// 验证：memorize()方法其实已经实现了 Proxy模式的能力(Proxy模式的能力是什么？)
// 启示：有些设计模式 是为了弥补程序设计语言本身的不足而出现的
public class ClientUsageDemo {
    public static void main(String[] args) {
        // 传入的参数是一个函数式接口类型的对象 - "() -> {}"
        // 返回的结果是：一个封装了参数值的reference对象
        // 这种操作对于编写代码有什么好处？避免重复进行一些消耗很大的操作 - how？
        Supplier<Integer> memorize = memorize(
                () -> {
                    /* complex calculate, would take ten minutes */
                    return 42;
                }
        );

        Integer value = memorize.get();
    }
}
