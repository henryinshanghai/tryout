package com.henry.tryout.easy_coding.exception_and_log_05.log_04;

// #1 导入Logger对象


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// 实践：在代码中使用日志的方式
public class printLog_01 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("com.henry.tryout.easy_coding.exception_and_log.log_04.printLog_01");

        // 条件输出方式 👇 这个logger对象 并没有 isDebugEnable()方法
//        if (logger.isDebugEnable()) {
//            logger.debug("xxx" + ooo + "xxx" + ooo);
//        }

        // 使用占位符方式 打印日志 👇
        logger.info("invoking main method..."); // INFO
        logger.debug("{} is bigger than {}", "10", "8"); // DEBUG
    }
}
