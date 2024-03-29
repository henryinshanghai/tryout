package com.henry.tryout.async_program.via_threadAndPool_01.viaThread_01;

public class AsyncExample_runnable_02 {

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

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        // 1.开启异步单元执行任务A
        Thread thread = new Thread(() -> {
            try {
                doSomethingA();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "threadA");
        thread.start();

        // 2.执行任务B
        doSomethingB();

        // 3.同步等待线程A运行结束
        thread.join();
        System.out.println(System.currentTimeMillis() - start);
    } // 把任务A交给一个单独的线程执行， 任务A与任务B并行执行 耗时在两秒钟左右
}
