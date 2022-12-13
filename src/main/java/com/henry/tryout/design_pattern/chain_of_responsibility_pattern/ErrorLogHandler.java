package com.henry.tryout.design_pattern.chain_of_responsibility_pattern;

public class ErrorLogHandler extends AbstractLogHandler {

    // 在构造器中，设置当前处理者 所能够处理的日志等级 - 构造器决定了使用者初始化时需要传入的信息
    public ErrorLogHandler(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.err.println("Error: " + message);
    }
}
