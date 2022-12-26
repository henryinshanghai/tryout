package com.henry.tryout.design_pattern.strategy_pattern;

import java.math.BigDecimal;

// 上下文类 特征{12}
public class ComputeContext {
    // #1 持有 策略类接口类型的 成员变量
    private Strategy strategy;

    // #2 一个用于设置 strategy变量的 setter或者构造器方法
    public ComputeContext(Strategy strategy) {
        this.strategy = strategy;
    }

    // #3 委托 strategy 来 具体地执行 计算方法
    public BigDecimal exec(BigDecimal a, BigDecimal b) {
        return this.strategy.compute(a, b);
    }
}
