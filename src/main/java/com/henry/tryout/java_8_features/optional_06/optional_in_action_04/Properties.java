package com.henry.tryout.java_8_features.optional_06.optional_in_action_04;

import java.util.HashMap;
import java.util.Map;

// 验证：类的init()方法/初始化方法/构造方法中，应该要完成 对成员变量的初始化
public class Properties {

    private Map<String, String> hostMap = new HashMap<>();

    public void setProperty(String property, String value) {
        hostMap.put(property, value);
    }

    public String getProperty(String property) {
        return hostMap.get(property);
    }

    public void init() {
        hostMap.put("attrA", "5");
        hostMap.put("attrB", "true");
        hostMap.put("attrC", "-3");
    }
}
