package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.SpringEL_02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DemoService_01 {

    @Value("其他类的属性") // 这里使用@Value() 来 注入普通的字符串
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
