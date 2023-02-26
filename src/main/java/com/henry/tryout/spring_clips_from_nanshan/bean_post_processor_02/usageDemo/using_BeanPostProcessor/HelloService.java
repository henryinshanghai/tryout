package com.henry.tryout.spring_clips_from_nanshan.bean_post_processor_02.usageDemo.using_BeanPostProcessor;

// 在接口上添加 @VersionSwitch() 用于表示这个接口存在有多个版本的实现
@VersionSwitch("V1")
public interface HelloService {
    void sayHello();
}
