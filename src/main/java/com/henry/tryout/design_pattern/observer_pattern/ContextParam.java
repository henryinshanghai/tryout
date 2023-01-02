package com.henry.tryout.design_pattern.observer_pattern;

// #1 上下文参数：用于表示 与事件相关的具体信息 - 在发布者 与 订阅者之间传递；
public class ContextParam {
    // 主要状态
    private int mainState;

    // 构造器 - 用于初始化成员变量
    public ContextParam(int mainState) {
        this.mainState = mainState;
    }

    public int getMainState() {
        return mainState;
    }

    public void setMainState(int mainState) {
        this.mainState = mainState;
    }
}
