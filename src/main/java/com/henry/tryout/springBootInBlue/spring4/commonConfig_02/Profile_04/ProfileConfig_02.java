package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.Profile_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class ProfileConfig_02 {

    /* 这里在 Spring的容器中配置了两个类型一样的Bean - 会违反Spring容器的规定吗？ */
    @Bean
    @Profile("dev") // 在当前环境是dev环境（活跃的profile为 dev profile）时，这个Bean才会被配置到 Spring容器中
    public DemoBean_01 devBean01() {
        return new DemoBean_01("from development profile");
    }

    @Bean
    @Profile("prod") // 在当前环境是dev环境（活跃的profile为 prod profile）时，这个Bean才会被配置到 Spring容器中
    public DemoBean_01 prodBean01() {
        return new DemoBean_01("from production profile");
    }
}
