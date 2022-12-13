package com.henry.tryout.design_pattern.chain_of_responsibility_pattern;

public abstract class AbstractLogHandler {
    /* 定义日志等级 */
    public static int DEBUG = 200;
    public static int INFO = 800;
    public static int ERROR = 1000;

    // 当前logHandler 所处理的日志等级
    protected int level;

    // 声明 处理者链条 中的下一个处理者（责任链上的下一个元素）
    // #疑问：什么条件下，需要持有一个成员变量？
    protected AbstractLogHandler nextHandler;

    // 设置责任链中的下一个元素
    public void setNextHandler(AbstractLogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 打印日志
    abstract protected void write(String message);

    // 对日志进行处理
    /* level - 日志等级 message - 日志信息 */
    public void handlerLogMessage(int level, String message) {
        // #1 是否对请求进行处理
        if (this.level <= level) {
            write(message);
        }

        // #2 是否要把请求传递给下一个 处理者
        if (nextHandler != null) {
            nextHandler.handlerLogMessage(level, message);
        }
    }
}
