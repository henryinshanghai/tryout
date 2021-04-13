package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.multipleThread_02;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService_02 {

    @Async // 1 声明当前方法是一个异步方法   作用：被注解的方法会被自动注入，并使用 ThreadPollTaskExecutor 来作为TaskExecutor
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务： " + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1： " + (i+1));
    }
}
