package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.taskArrangement_03;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledTaskService_01 {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /* step1 使用@Schedule()注解 来 定义需要被视为 定时任务 的方法 */
    // 1 固定时间间隔执行   手段：fixedRate属性
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("每隔五秒执行一次 " + dateFormat.format(new Date()));
    }

    // 2 在指定的时间点执行  手段：corn表达式
    @Scheduled(cron = "0 15 14 ? * *") // 每天的11点28分     note：这个表达式可以直接在网站上生成，不需要记忆它的语法规则
    public void fixTimeExecution() {
        System.out.println("在指定的时间点 " + dateFormat.format(new Date()) + "执行");
    }




}
