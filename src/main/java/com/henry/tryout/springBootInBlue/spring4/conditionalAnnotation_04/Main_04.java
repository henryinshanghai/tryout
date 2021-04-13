package com.henry.tryout.springBootInBlue.spring4.conditionalAnnotation_04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main_04 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConditionConfig_03.class);

        // note： 我当前的系统是Windows环境，所以这里预期实例化的会是 WindowsListService
        ListService_0201 listService0201 = context.getBean(ListService_0201.class);

        System.out.println(context.getEnvironment().getProperty("os.name")
            + "系统下， 列举当前目录下的所有项目的命令为： "
            + listService0201.showListCmd());

    }
}
