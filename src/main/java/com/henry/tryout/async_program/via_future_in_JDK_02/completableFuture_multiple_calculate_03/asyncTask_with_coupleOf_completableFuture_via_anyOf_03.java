package com.henry.tryout.async_program.via_future_in_JDK_02.completableFuture_multiple_calculate_03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class asyncTask_with_coupleOf_completableFuture_via_anyOf_03 {


    // 1.异步任务，返回future
    public static CompletableFuture<String> doSomethingOne(String id) {
        // 1.1创建异步任务
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                // 1.1.1休眠1s，模拟任务计算
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("compute " + id);

                return id;
            }
        });
    }

    // 2.开启异步任务，返回future
    public static CompletableFuture<String> doSomethingTwo(String id) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                // 2.1,休眠3s，模拟计算
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("compute " + id);

                return id;
            }
        });
    }


    public static void anyOf() throws InterruptedException, ExecutionException {
        // 1.创建future列表
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(doSomethingOne("1")); // 这里就已经对异步任务进行了调用 ×1
        futureList.add(doSomethingOne("2")); // 调用×2
        futureList.add(doSomethingTwo("3")); // 调用×3

        // 2.转换多个future为一个
        CompletableFuture<Object> result = CompletableFuture
                .anyOf(futureList.toArray(new CompletableFuture[futureList.size()]));

        // 3.等待某一个future完成
        System.out.println(result.get()); // futureList列表中有一个任务结束了，get()方法就会返回  所以这里只会打印某一个任务的返回值 - 具体是哪一个任务，取决于哪个任务率先完成

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1.allOf
//        allOf();

        // 2.anyOf
         anyOf();

        Thread.sleep(10000);
    }
}
