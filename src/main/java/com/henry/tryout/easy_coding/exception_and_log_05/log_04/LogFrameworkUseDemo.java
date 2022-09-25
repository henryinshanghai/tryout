package com.henry.tryout.easy_coding.exception_and_log_05.log_04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // #1 引入门面slf4j中的类

public class LogFrameworkUseDemo {
    // 静态成员变量能够减少资源消耗 （因为与类绑定）
    // #2 调用工厂类的静态方法 - 获取到日志对象
    private static final Logger logger = LoggerFactory.getLogger(LogFrameworkUseDemo.class);

    public static void main(String[] args) {
        logger.info("invoking main method...");
    }
}
