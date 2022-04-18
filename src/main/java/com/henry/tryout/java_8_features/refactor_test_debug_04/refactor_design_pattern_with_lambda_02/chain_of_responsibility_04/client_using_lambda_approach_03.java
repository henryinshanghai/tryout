package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.chain_of_responsibility_04;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class client_using_lambda_approach_03 {
    public static void main(String[] args) {
        // 使用lambda表达式 来 代替子类+抽象类 - 子类型属于僵化的代码
        // 手段： UnaryOperator类型的 lambda表达式
        UnaryOperator<String> headerProcessing = (String text) -> "From Henry: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) ->  text.replaceAll("labda", "lambda");

        // 关联处理对象 - 手段： andThen(xxx)
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        // 开启处理对象的序列 - 手段：apply()
        String result = pipeline.apply("Aren't labdas really sexy?");
        System.out.println(result);
    } // 相比较而言，不需要抽象类，也不需要具体实现类 - 使用JDK内置的类型就实现了责任链模式（虽然这些类型都还挺陌生的）
}
