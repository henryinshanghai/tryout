package com.henry.tryout.design_pattern.strategy_pattern;

import java.math.BigDecimal;

// 具体策略*1
public class AddStrategy implements Strategy {

    @Override
    public BigDecimal compute(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }
}
