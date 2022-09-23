package com.henry.tryout.easy_coding.exception_and_log_05.try_block_02;

// 验证：finally代码块中的return表达式 会覆盖掉 try代码块中的return表达式
public class ReturnInFinallyDemo_02 {
    static int x = 1;
    static int y = 10;
    static int z = 100;

    public static void main(String[] args) {
        int value = finallyReturn();

        System.out.println("value: " + value);
        System.out.println("x: " + x); // 结果：2
        System.out.println("y: " + y); // 结果：10
        System.out.println("z: " + z); // 结果：101
    }

    private static int finallyReturn() {
        try {
            return ++x; // 预期：x = 2
        } catch (Exception e) {
            return ++y; // 预期： y = 10
        } finally {
            return ++z; // 预期：z = 101 - finally中的return会作为方法的返回点
        }
    }
}
/*
执行结果：
1 最终执行的return是 finally代码块中的return，所以方法返回的是101；
2 return ++x中的++x被成功执行，因为x的结果成为了2；
3 如果有异常抛出，则：运行结果会是y=11，而x=1

启示：
    不要在 finally代码块中使用return语句。
 */
