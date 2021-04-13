package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.taskArrangement_03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_03 {
    public static void main(String[] args) {
        // 创建Spring容器
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskSchedulerConfig_02.class);
    }
}
/*
结果：在创建Spring容器后，定时任务按照预期的效果执行了

# Questions
1 创建Spring容器时， 被注入到容器中的Bean就会被初始化？
2 定时任务随之开始active？？？
 */
