package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringEL_02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_03 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig_02.class);

        ElConfig_02 resourceService = context.getBean(ElConfig_02.class);

        resourceService.outputResource();

        context.close();
    }
}
