package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.strategy_01;

public class ValidatorClient_04 {
    public static void main(String[] args) {
        usingOldApproachForStrategyPattern();

        // 由于 这里的策略接口是一个 函数式接口，所以 整套代码都可以使用 Lambda表达式进行简化 - 接口的实现类就不再需要了
        usingLambdaExpressionForStrategyPattern();


    }

    private static void usingLambdaExpressionForStrategyPattern() {
        Validator_03 numericValidator = new Validator_03((String s) -> s.matches("[a-z]+"));

        Validator_03 lowerCaseValidator = new Validator_03((String s) -> s.matches("//d+"));
    }

    private static void usingOldApproachForStrategyPattern() {
        Validator_03 numericValidator = new Validator_03(new IsNumeric_02());

        String input_string = "aaaaaa";
        boolean validateResult = numericValidator.validate(input_string);
        System.out.println("对" + input_string + "使用xxx 校验的结果为： " + validateResult);

        String input_string_2 = "12345";
        Validator_03 lowerCaseValidator = new Validator_03(new IsAllLowerCase_02());
        boolean result2 = lowerCaseValidator.validate(input_string_2);
        System.out.println("对" + input_string_2 + "的校验结果为： " + result2);
    }
}
