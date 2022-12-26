package com.henry.tryout.design_pattern.strategy_pattern;

import java.math.BigDecimal;

// 具体策略*2
public class SubtractStrategy implements Strategy{
    @Override
    public BigDecimal compute(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
}
