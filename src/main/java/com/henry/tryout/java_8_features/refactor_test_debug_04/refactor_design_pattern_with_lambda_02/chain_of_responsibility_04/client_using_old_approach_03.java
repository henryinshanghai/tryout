package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.chain_of_responsibility_04;

public class client_using_old_approach_03 {
    public static void main(String[] args) {
        // 获取到 处理对象
        HeaderTextProcessing_02 headerTextProcessing = new HeaderTextProcessing_02();
        SpellCheckerProcessing_02 spellCheckerProcessing = new SpellCheckerProcessing_02();

        // 关联处理对象
        headerTextProcessing.setSuccessor(spellCheckerProcessing);

        // 传入待处理的文本
        String result = headerTextProcessing.handle("Aren't labdas really sexy?");
        System.out.println(result);

    }
}
