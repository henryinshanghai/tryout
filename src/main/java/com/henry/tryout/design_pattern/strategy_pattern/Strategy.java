package com.henry.tryout.design_pattern.strategy_pattern;

import java.math.BigDecimal;

// 策略/算法接口
public interface Strategy {
    BigDecimal compute(BigDecimal a, BigDecimal b);
}
