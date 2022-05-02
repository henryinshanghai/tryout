package com.henry.tryout.java_8_features.optional_06.optional_in_action_04;

import java.util.HashMap;
import java.util.Map;

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
