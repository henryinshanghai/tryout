package com.henry.tryout.springBootInBlue.spring4.basics_01.DI_01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseFunctionService_02 {

    @Autowired
    FunctionService_01 functionService;

    public String SayHello(String word) {
        return functionService.sayHello(word);
    }
}
