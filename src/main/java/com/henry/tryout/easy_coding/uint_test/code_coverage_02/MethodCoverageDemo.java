package com.henry.tryout.easy_coding.uint_test.code_coverage_02;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MethodCoverageDemo {

    // 针对 testMethod方法的测试用例
    /*
        这个测试用例会cover到 testMethod()方法的所有行，因此行覆盖率为100%
        BUT，执行条件中 c==3根本就没有被执行到。 - 行覆盖率存在一定的局限性
     */
    @Test
    @DisplayName("line coverage sample test")
    public void testLineCoverageSample() {
        CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();

        Assert.assertEquals(false, coverageSampleMethods.testMethod(1, 2, 0));
    }

    // 定义一个参数化测试 - 手段： @ParameterizedTest注解
    @ParameterizedTest
    @DisplayName("Condition Decision coverage sample test result true")
    // 定义多次运行测试时所使用的参数列表 - 手段：@CsvSource注解  传入一个String数组    特征：每次运行测试所使用的参数 由,进行分隔
    @CsvSource({
            "0, 2, 3",
            "1, 0, 3"
    })
    public void testConditionDecisionCoverageTrue(int a, int b, int c) {
        CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();

        Assert.assertEquals(true, coverageSampleMethods.testMethod(a, b, c));
    }

    @Test
    @DisplayName("Condition Decision coverage sample test result false")
    public void testConditionDecisionCoverageFalse() {
        CoverageSampleMethods coverageSampleMethods = new CoverageSampleMethods();

        Assert.assertEquals(false, coverageSampleMethods.testMethod(0, 0, 0));
    }
}
/*
第一个测试用例 testConditionDecisionCoverageTrue会运行两次：
参数值分别为：0, 2, 3 与 1, 0, 3

第二个测试用例 testConditionDecisionCoverageFalse的参数值： 0, 0, 0

被测试方法中存在的判定：
    (a==1 && b==2 || c==3);
判定中所包含的3个条件：
    a==1、b==2、c==3
判定所有可能的结果：
    true、false
条件所有可能的结果：
    8种 // 这个要求是要有8种组合吗？貌似不是
因此 这两个测试用例，满足了 条件判定覆盖的要求。

结论：分支覆盖 是 条件判定覆盖 的一个子集。
 */