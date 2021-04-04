package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringEL_02;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringEL_02")
// step1 使用 @PropertySource注解 来 指定配置文件的地址
// note : 1 这里属性文件需要放置在 src/main/resources下面，再在此处进行引用.
// 2 而且这里的代码能够自动变化 interesting
@PropertySource("classpath:springResources/test.properties")
public class ElConfig_02 {

    @Value("I Love you!") // 1
    private String normal; // 注入普通字符串

    @Value("#{systemProperties['os.name']}") // 2
    private String osName; // 注入操作系统属性

    @Value("#{ T(java.lang.Math).random() * 100.0 }") // 3
    private double randomNumber; // 注入表达式的结果

    @Value("#{demoService_01.another}") // 4
    private String fromAnother; // 注入其他bean的属性

    @Value("classpath:springResources/test")
    private Resource testFile; // 注入文件资源 - 文件需要放置在 src/main/resources目录下

    @Value("http://www.baidu.com") // note: 这里需要加上http://协议
    private Resource testUrl; // 注入网络资源

    @Value("${book.name}") // note: 这里的语法符号是${}
    private String bookName; // 注入配置文件的内容 - 1

    @Autowired // 注入配置文件的内容 - 2
    private Environment environment;

//    // step2 配置一个 xxx类型的Bean，以便使用@Value()注解
//    @Bean // 注入配置文件的内容 - 3 不必须的
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigure() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    public void outputResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);

            System.out.println(IOUtils.toString(testFile.getInputStream()));

            System.out.println(IOUtils.toString(testUrl.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
