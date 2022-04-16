package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.strategy_01;

public class IsAllLowerCase_02 implements ValidationStrategy_01 {

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
