package com.henry.tryout.async_program.via_future_in_JDK_02.exception_04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class asyncTask_with_exception_combine_00 {


    public static void one() throws InterruptedException, ExecutionException {
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
            future.completeExceptionally(new RuntimeException("error exception"));

        }, "thread-1").start();

        // 3.等待计算结果
        System.out.println("---main thread wait future result---");
        System.out.println(future.get());
        // System.out.println(future.get(1000,TimeUnit.MILLISECONDS));
        System.out.println("---main thread got future result---");
    }

    public static void two() throws InterruptedException, ExecutionException {
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
            future.completeExceptionally(new RuntimeException("error exception"));

        }, "thread-1").start();
        ;
        // 3.等待计算结果
        System.out.println("---main thread wait future result---");
        System.out.println(future.exceptionally(t -> "default").get());// 默认值
        // System.out.println(future.get(1000,TimeUnit.MILLISECONDS));
        System.out.println("---main thread got future result---");
    }

    public static void two1() throws InterruptedException, ExecutionException, TimeoutException {
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
            future.completeExceptionally(new RuntimeException("error exception"));

        }, "thread-1").start();
        ;

        // 3.等待计算结果
        System.out.println("---main thread wait future result---");
//        System.out.println(future.exceptionally(t -> "default").get());// 默认值
         System.out.println(future.get(1000, TimeUnit.MILLISECONDS)); // note: this seems useless, there's TimeoutException throw out
        System.out.println("---main thread got future result---");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

//        one();
//        two();
        two1();
    }

}
/*
    这个是八股文吗？
    不要想着毕其功于一役，而是要循序渐进，步步为营

对了，先注册另一个Github账号吧。
JenniferInSH
then, a bit lash out.
 */
