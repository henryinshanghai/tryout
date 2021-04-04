package com.henry.tryout.springBootInBlue.spring4.commonConfig_02.Profile_04;

public class DemoBean_01 {

    private String content;

    public DemoBean_01(String content) {
        super(); // what is this? 为什么会需要调用父类的空构造器方法？
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
