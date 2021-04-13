package com.henry.tryout.springBootInBlue.spring4.test_07;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit4ClassRunner类型 在JUnit环境下，提供了Spring Test Context Framework的功能
@ContextConfiguration(classes = {TestConfig_02.class}) // 加载 对Spring容器/应用上下文对象的配置信息（aka 配置类）
@ActiveProfiles("prod") // 声明活跃的profile - 为prod 【陈述句】
public class DemoBeanIntegrationTests_03 {

    // 为当前测试类型注入bean实例
    // 手段：直接从Spring容器中 autowire
    // 预期：既然已经声明 - 活跃的profile是 prod，那么Spring容器在创建bean实例时，应该会 创建出一个content为 "from production profile"的bean实例
    @Autowired
    private TestBean_01 testBean01;

    // 编写测试方法
    @Test
    public void prodBeanShouldInject() {
        String expected = "from production profile";
        String actual = testBean01.getContent();

        Assert.assertEquals(expected, actual); // 预言它们是相同的，然后看看会不会被打脸
    } // Bingo！ 预言成功！！！
}
