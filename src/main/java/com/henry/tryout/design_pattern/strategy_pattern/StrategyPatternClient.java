package com.henry.tryout.design_pattern.strategy_pattern;

import java.math.BigDecimal;

public class StrategyPatternClient {
    public static void main(String[] args) {
        ComputeContext addContext = new ComputeContext(new AddStrategy());
        BigDecimal addResult = addContext.exec(BigDecimal.ONE, BigDecimal.TEN);
        System.out.println("a + b = " + addResult);

        ComputeContext subtractContext = new ComputeContext(new SubtractStrategy());
        BigDecimal subtractResult = subtractContext.exec(BigDecimal.ONE, BigDecimal.TEN);
        System.out.println("a - b = " + subtractResult);

    }
}
