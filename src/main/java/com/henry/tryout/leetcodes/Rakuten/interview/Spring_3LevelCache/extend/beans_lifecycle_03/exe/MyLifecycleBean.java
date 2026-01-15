package com.henry.tryout.leetcodes.Rakuten.interview.Spring_3LevelCache.extend.beans_lifecycle_03.exe;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MyLifecycleBean
        implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;
    private ApplicationContext applicationContext;

    // 【1】构造函数 → 实例化
    public MyLifecycleBean() {
        System.out.println("【1】实例化：调用构造函数");
    }

    // 【2】依赖注入（如果有 @Autowired 字段）
    @Autowired
    public void setDependencies() {
        System.out.println("【2】属性填充：依赖注入完成");
    }

    // 【3a】BeanNameAware 回调
    @Override
    public void setBeanName(String name) {
        this.name = name;
        System.out.println("【3a】Aware 接口：Bean 名称 = " + name);
    }

    // 【3b】ApplicationContextAware 回调
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        System.out.println("【3b】Aware 接口：ApplicationContext 已注入");
    }

    // 【4】@PostConstruct
    @PostConstruct
    public void postConstruct() {
        System.out.println("【4】@PostConstruct：初始化前准备");
    }

    // 【5】InitializingBean.afterPropertiesSet()
    @Override
    public void afterPropertiesSet() {
        System.out.println("【5】InitializingBean：属性已设置，执行自定义初始化");
    }

    // 【6】自定义 init-method（通过 @Bean 配置，见下方说明）
    public void customInit() {
        System.out.println("【6】自定义 init-method：额外初始化逻辑");
    }

    // 业务方法
    public void doSomething() {
        System.out.println("【7】Bean 正常使用中：执行业务逻辑");
    }

    // 【8】@PreDestroy
    @PreDestroy
    public void preDestroy() {
        System.out.println("【8a】@PreDestroy：准备销毁资源");
    }

    // 【8b】DisposableBean.destroy()
    @Override
    public void destroy() {
        System.out.println("【8b】DisposableBean：执行销毁逻辑");
    }

    // 【8c】自定义 destroy-method（通过 @Bean 配置）
    public void customDestroy() {
        System.out.println("【8c】自定义 destroy-method：清理工作");
    }
}