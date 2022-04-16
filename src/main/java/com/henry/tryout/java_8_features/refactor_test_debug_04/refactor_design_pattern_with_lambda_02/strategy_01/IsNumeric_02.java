package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.strategy_01;

public class IsNumeric_02 implements ValidationStrategy_01 {

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
