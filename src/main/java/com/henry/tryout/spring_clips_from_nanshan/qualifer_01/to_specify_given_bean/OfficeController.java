package com.henry.tryout.spring_clips_from_nanshan.qualifer_01.to_specify_given_bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;

public class OfficeController {

    // 在注入依赖时，使用 @Qualifier注解 来 特指 所需要注入的bean实例
    @Qualifier("oldOfficeService")
    @Autowired
    private OfficeService officeService;

    @RequestMapping("/test")
    public void test() {
        officeService.log(); // this is previous/old office service.
    }
}
