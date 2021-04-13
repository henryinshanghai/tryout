package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.multipleThread_02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_03 {

    public static void main(String[] args) {
        // 1 创建Spring容器对象
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig_01.class);

        // 2 获取Bean实例
        AsyncTaskService_02 asyncTaskService02 = context.getBean(AsyncTaskService_02.class);


        // 3 调用bean的实例方法 验证是不是多个线程在执行这一段代码
        for (int i = 0; i < 10; i++) {
            asyncTaskService02.executeAsyncTask(i);
            asyncTaskService02.executeAsyncTaskPlus(i);
        } // note:如果是多个线程并发执行，这里输出的i不会是递增的(而是换七八糟地输出一通)

        context.close();
    }
}
