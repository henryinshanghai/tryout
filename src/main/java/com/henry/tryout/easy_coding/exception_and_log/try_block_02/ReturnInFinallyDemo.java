package com.henry.tryout.easy_coding.exception_and_log.try_block_02;

public class ReturnInFinallyDemo {
    static int x = 1;
    static int y = 10;
    static int z = 100;

    public static void main(String[] args) {
        int value = finallyReturn();

        System.out.println("value: " + value);
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("z: " + z);
    }

    private static int finallyReturn() {
        try {
            return ++x;
        } catch (Exception e) {
            return ++y;
        } finally {
            return ++z;
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
