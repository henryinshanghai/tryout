package com.henry.tryout.async_program.via_future_in_JDK_02.exception_04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class asyncTask_with_exception_and_default_solution_02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1.创建一个CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<String>();

        // 2.开启线程计算任务结果，并设置
        new Thread(() -> {

            // 2.1休眠3s，模拟任务计算
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 2.2设置计算结果到future
            System.out.println("----" + Thread.currentThread().getName() + " set future result----"); // print-2
            future.completeExceptionally(new RuntimeException("error exception")); // note：这里 手动创建一个异常对象，并交给 completeExceptionally()作为参数

        }, "thread-1").start();
        ;
        // 3.等待计算结果
        System.out.println("---main thread wait future result---"); // print-1
        System.out.println(future.exceptionally(t -> "default").get());// print-3 当遇到异常时，返回默认值 - 吞掉异常本身
        // System.out.println(future.get(1000,TimeUnit.MILLISECONDS));
        System.out.println("---main thread got future result---"); // print-4
    }
}
/*
---main thread wait future result---
----thread-1 set future result----
default
---main thread got future result---

 */
