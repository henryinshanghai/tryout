package com.henry.tryout.easy_coding.exception_and_log.try_block_02;

public class finallyNotWorkDemo {
    public static void main(String[] args) {
        int result = finallyNotWork();
        System.out.println(result);
    }

    private static int finallyNotWork() {
        int temp = 10000;
        try {
            throw new Exception();
        } catch (Exception e) {
            return ++temp;
        } finally {
            temp = 99999;
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
1 finally代码块的职责不是 对变量进行赋值，而是
    清理资源、释放连接、关闭管道流等
2 不要在 finally代码块中做 return操作。
 */
