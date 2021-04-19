package com.henry.tryout.async_program.via_future_in_JDK_02.completableFuture_multiple_calculate_03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class asyncTask_with_two_completableFuture_via_thenCompose_01 {


    // 1.异步任务，返回future
    public static CompletableFuture<String> doSomethingOne(String encodedCompanyId) {
        // 1.1创建异步任务
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                // 1.1.1休眠1s，模拟任务计算
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // 1.1.2 解密，并返回结果
                String id = encodedCompanyId;
                return id;
            }
        });
    }

    // 2.开启异步任务，返回future
    public static CompletableFuture<String> doSomethingTwo(String companyId) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                // 2.1,休眠3s，模拟计算
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // 2.2 查询公司信息，转换为str，并返回
                String str = companyId + ":alibaba";
                return str;
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // I，等doSomethingOne执行完毕后，接着执行doSomethingTwo
        // 手段：doSomethingOne()方法返回 future对象； 在 future对象上调用 thenCompose()方法
        // 方法接收的参数是一个lambda表达式
        // 注：这里的lambda表达式 是一种单纯的传值形式 - 可以使用 方法引用 来代替
        // 方法中封装的任务的返回值 会作为 新的任务的参数
        CompletableFuture result = doSomethingOne("123").thenCompose(id -> doSomethingTwo(id));
        // 阻塞main线程，以获取执行结果
        System.out.println(result.get()); // 123:alibaba

        // II,等doSomethingOne和doSomethingTwo都完成后，使用它们的结果做一件事
        // 两个并发的任务都完成后，再使用两者的结果 作为参数，去执行新的异步任务
        result = doSomethingOne("123") // 123
                    .thenCombine(
                        doSomethingTwo("456"), // 456:alibaba
                        (one, two) -> {
                            return one + " " + two; // 123 456:alibaba
                        });
        System.out.println(result.get());

    }


}
