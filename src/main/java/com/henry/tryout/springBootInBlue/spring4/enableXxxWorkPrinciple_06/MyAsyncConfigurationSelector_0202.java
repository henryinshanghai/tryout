package com.henry.tryout.springBootInBlue.spring4.enableXxxWorkPrinciple_06;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.ProxyAsyncConfiguration;

public class MyAsyncConfigurationSelector_0202 extends AdviceModeImportSelector{ // 1 根接口为 ImportSelector

    private static final String ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME = "org.springframework.scheduling.aspectj.AspectJAsyncConfiguration";

    public MyAsyncConfigurationSelector_0202() {
    }

    @Override // 2 重写 根接口中的selectImports()方法 - 在方法中，进行条件判断，根据不同情况返回不同的 用于配置的类型
    @Nullable
    public String[] selectImports(AdviceMode adviceMode) {
        /* 先判断条件，然后选择需要导入的配置类型 */
        switch(adviceMode) {
            case PROXY: // case 1
                return new String[]{ProxyAsyncConfiguration.class.getName()};
            case ASPECTJ: // case 2
                return new String[]{"org.springframework.scheduling.aspectj.AspectJAsyncConfiguration"};
            default: // case 3
                return null;
        }
    }
}
