package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.chain_of_responsibility_04;

public class HeaderTextProcessing_02 extends ProcessingObject_01<String> {

    @Override
    protected String handleWork(String text) {
        return "From Henry: " + text;
    }
}
