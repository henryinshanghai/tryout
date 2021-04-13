package com.henry.tryout.async_program.via_future_in_JDK_02.futuretask_01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Async_thread_with_futureTask_01 {

    // 有返回值的任务A
    public static String doSomethingA() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");

        return "TaskAResult";
    }

    // 有返回值的任务B
    public static String doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB---");
        return "TaskBResult";

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 1.创建future任务
        // 作用：封装 待执行的任务A；
        // 手段：FutureTask的构造器 + lambda表达式/Runnable/Callable实例
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            String result = null;
            try {
                result = doSomethingA();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result; // FutureTask中添加的任务可以有返回值
        });

        // 2.开启异步单元执行任务A
        // 在创建线程的时候，就添加进去 需要执行的异步任务
        Thread thread = new Thread(futureTask, "threadA"); // 执行线程的名字，方便调试
        thread.start();

        // 3.执行任务B - 使用main线程
        String taskBResult = doSomethingB(); // 此时 任务A与任务B在同一时刻执行 aka 并发执行

        // 4.同步等待 - 等待线程A运行结束
        // 由于get()方法会 导致main线程阻塞，所以 下面的print()语句 一定 后于 任务中的print语句 打印
        String taskAResult = futureTask.get();

        //5.打印两个任务执行结果
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);

    }

}
