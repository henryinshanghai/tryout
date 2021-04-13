package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.taskArrangement_03;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.taskArrangement_03")
@EnableScheduling // 2 开启Spring对于 定实任务/计划任务 的支持
public class TaskSchedulerConfig_02 {

}
