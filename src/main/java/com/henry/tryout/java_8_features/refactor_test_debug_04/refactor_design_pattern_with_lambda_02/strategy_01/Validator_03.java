package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.strategy_01;

public class Validator_03 {
    // 持有校验策略的类型
    private final ValidationStrategy_01 strategy;

    public Validator_03(ValidationStrategy_01 strategy) {
        this.strategy = strategy;
    }

    // 对外提供的API - 校验传入的字符串的格式
    public boolean validate(String s) {
        return strategy.execute(s);
    }

    public String validatorName() {
        return strategy.toString();
    }
}
