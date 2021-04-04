package com.henry.tryout.springBootInBlue.spring4.basics_01.Config_02;

public class UseFunctionService_02 {

    FunctionService_01 functionService_01;

    public void setFunctionService_01(FunctionService_01 functionService_01) {
        this.functionService_01 = functionService_01;
    }

    public String sayHello(String word) {
        return functionService_01.sayHello(word);
    }
}
