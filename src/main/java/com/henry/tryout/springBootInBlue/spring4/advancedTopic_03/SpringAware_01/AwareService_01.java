package com.henry.tryout.springBootInBlue.spring4.advancedTopic_03.SpringAware_01;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

// 为什么说“Bean对Spring容器的存在是没有意识的”？
// 因为一般情况下定义的Bean就是一个简单的POJO类型，并没有 实现/继承 Spring容器的接口/实现类(xxxApplication)
/*
Spring Aware的作用是： 让Bean获得到 Spring容器的服务。
Spring容器有哪些呢？
    xxxApplicationContext的类型都是Spring容器

这里原文的逻辑不太通顺，所以只做抄录：
“
    因为ApplicationContext接口继承了 MessageSource接口、ApplicationEventPublisher接口 和 ResourceLoader接口
    所以当Bean继承了ApplicationContextAware就能够获得Spring容器的所有服务。【why？】
    但原则上，我们还是用到什么接口就实现什么接口

”
 */
// 需要Spring提供的什么服务，就实现对应的接口即可
// 获取Bean名称的服务 -> BeanNameAware接口
// 资源加载的服务 -> ResourceLoaderAware接口
@Service
public class AwareService_01 implements BeanNameAware, ResourceLoaderAware {

    private String beanName;
    private ResourceLoader loader;

    /* 重写的方法 */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    /* 独有的方法 */
    public void outputResult() {
        System.out.println("Bean的名称为： " + beanName);
        // 加载项目中的资源
        Resource resource = loader.getResource("classpath:springAwareResource/test");

        try {
            // 把二进制流转换成为 字符串内容
            System.out.println("ResourceLoader所加载的文件内容为：" +
                    IOUtils.toString(resource.getInputStream())); // 在getStream的时候，编译期会给出编译期Exception
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
