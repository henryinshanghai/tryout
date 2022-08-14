package com.henry.tryout.easy_coding.uint_test.code_coverage_02;

public class CoverageSampleMethods_01 {
    /*
        5行可执行语句；3个入参；
        测试用例：
     */
    public boolean testMethod(int a, int b, int c) {
        boolean result = false;

        if (a == 1 && b == 2 || c == 3) {
            result = true;
        }

        return false;
    }

}

