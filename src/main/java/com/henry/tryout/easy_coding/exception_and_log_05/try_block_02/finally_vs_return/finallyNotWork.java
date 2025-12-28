package com.henry.tryout.easy_coding.exception_and_log_05.try_block_02.finally_vs_return;

// 验证： finally代码块  在return表达式之后 执行的
// 原理：执行finally的时候 会 先把 return表达式 暂存起来；执行完finally之后，再return暂存的值。
// 验证手段：① 在catch{}代码块中 return 变量temp；② 并 在finally{}代码块中 给temp变量 重新赋值
// 启示：finally代码块的职责 不是 对变量进行赋值，而是 - 清理资源、释放连接、关闭管道流等
public class finallyNotWork {
    public static void main(String[] args) {
        int result = finallyNotWork();

        // 结果：10001
        // 说明：在return语句之后，finally代码块 仍会执行（此时 return表达式 被暂存起来了）
        System.out.println(result);
    }

    private static int finallyNotWork() {
        int temp = 10000;
        try {
            throw new Exception();
        } catch (Exception e) {
            // ① 在catch{}代码块中 return 变量temp；
            return ++temp; // 预期：10001
        } finally {
            // ② 在finally{}代码块中 给temp变量 重新赋值
            System.out.println("change temps value to 99999");
            temp = 99999; // 预期：99999
        }
    }
}
/*
字节码：
    15 iinc 0 by 1 // 对变量temp执行+1操作
    16 iload_0
    17 istore_2 // return表达式计算的结果 被存储在 slot_2中
    18 ldc #7 <99999>
    20 istore_0 // finally存储 99999 到 slot_0上

    21 iload_2 // 方法返回的时候，直接提取 slot_2上的值，aka 10001
    22 ireturn

    启示：
    1
    2 不要在 finally代码块中做 return操作。
 */
