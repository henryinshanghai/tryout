package com.henry.tryout.async_program.via_threadAndPool_01;

public class SyncExample_01 {
    public static void doSomethingA() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB---");

    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // 1.执行任务A
        doSomethingA();

        // 2.执行任务B
        doSomethingB();

        System.out.println(System.currentTimeMillis() - start);

    } // 任务序列 顺序执行，最终耗时4秒钟
}
