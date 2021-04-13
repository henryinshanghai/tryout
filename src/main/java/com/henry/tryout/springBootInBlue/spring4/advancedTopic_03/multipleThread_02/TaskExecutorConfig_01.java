package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.multipleThread_02;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan("com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.multipleThread_02")
@EnableAsync // 1 使用@EnableAsync注解 来 开启 对异步任务的支持
public class TaskExecutorConfig_01 implements AsyncConfigurer { // 2-1 实现 AsyncConfigurer接口

    // 2-2 重写 getAsyncExecutor()方法
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor(); // 2-3 使用基于线程池的taskExecutor

        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();

        return taskExecutor; // 任务执行器
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
