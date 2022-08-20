package com.henry.tryout.easy_coding.exception_and_log_05.log_04;

import java.util.logging.Logger;

public class printLog_01 {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("com.henry.tryout.easy_coding.exception_and_log.log_04.printLog_01");

        // 条件输出方式 👇 这个logger对象 并没有 isDebugEnable()方法
//        if (logger.isDebugEnable()) {
//            logger.debug("xxx" + ooo + "xxx" + ooo);
//        }

        // 占位符方式 👇
//        logger.debug("xxx {} xxx {}", oo, qq);
    }
}
