package com.henry.tryout.design_pattern.chain_of_responsibility_pattern;

import java.io.File;

public class ClientCodeUsingResponsibilityChain {
    // 构造出责任链 （处理者链条）
    private static AbstractLogHandler constructChainOfLogHandler() {
        ErrorLogHandler errorLoggHandler = new ErrorLogHandler(AbstractLogHandler.ERROR);
        FileLogHandler fileLogHandler = new FileLogHandler(new File("app.log"), AbstractLogHandler.INFO);
        ConsoleLogHandler consoleLogHandler = new ConsoleLogHandler(AbstractLogHandler.DEBUG);

        // 日志级别 在链条中的顺序: error -> info -> debug
        errorLoggHandler.setNextHandler(fileLogHandler);
        fileLogHandler.setNextHandler(consoleLogHandler);

        return errorLoggHandler; // 返回链条的头节点
    }

    public static void main(String[] args) {
        // 特征： client在创建出 处理者链条后，就可以直接使用 抽象处理者类型的变量 来 编写代码
        // 由于不会使用任何具体的处理者类，因此 client代码 与 具体的处理者类 没有发生耦合
        AbstractLogHandler logHandlerChain = constructChainOfLogHandler();

        // 特征：可以在 责任链上的任何一个节点 触发 处理者 对日志进行处理
        System.out.println("------ DEBUG ------");
        logHandlerChain.handlerLogMessage(AbstractLogHandler.DEBUG, "This is a debug log");

        System.out.println("------ INFO ------");
        logHandlerChain.handlerLogMessage(AbstractLogHandler.INFO, "this is an info log");

        System.out.println("------ ERROR ------");
        logHandlerChain.handlerLogMessage(AbstractLogHandler.ERROR, "this is an error log");
    }
}
