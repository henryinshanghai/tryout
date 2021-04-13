package com.henry.tryout.springBootInBlue.spring4.test_07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class TestConfig_02 {

    @Bean
    @Profile("dev") // 当 活跃的profile为dev 时，创建该bean实例
    public TestBean_01 devTestBean() {
        return new TestBean_01("from development profile");
    }

    @Bean
    @Profile("prod") // 当 活跃的profile为prod 时，创建该bean实例
    public TestBean_01 prodTestBean() {
        return new TestBean_01("from production profile");
    }
}
