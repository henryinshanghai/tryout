package com.henry.tryout.async_program.via_future_in_JDK_02.exception_04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class asyncTask_with_exception_01 {

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
            System.out.println("----" + Thread.currentThread().getName() + " set future result----");
            future.completeExceptionally(new RuntimeException("error exception")); // note：中岛此处时，方法会抛出异常 - java.lang.RuntimeException: error exception

        }, "thread-1").start();

        // 3.等待计算结果
        System.out.println("---main thread wait future result---");
        System.out.println(future.get());
        // System.out.println(future.get(1000,TimeUnit.MILLISECONDS));
        System.out.println("---main thread got future result---"); // note2： this won't get printed.
    }
}
