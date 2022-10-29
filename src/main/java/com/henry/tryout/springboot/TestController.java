package com.henry.tryout.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


// 验证：怎么对UI传入的数据student做数据校验？
// 手段1： 这里添加一堆的if语句对 student中的每个参数进行null校验
// 手段2： 使用 Spring提供的注解 -
// #1 为了使自定义的POJO能够接收到postman中发送的json字符串 - 需要使用@JsonProperty来修饰POJO的字段值（为什么字段名一样也需要这个注解？）;
// #2 给Student的字段添加上校验规则 - Spring提供的注解;
// #3 在接口中开启对参数的校验 - 手段：在参数前添加@Valid注解
// phase Ⅰ：校验失败后，给Postman的响应是 "error": "Bad Request", 校验注解中指定的error message只是在IDEA控制台打印了出来
@RestController
public class TestController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody @Valid Student student) { // #2 使用@Valid注解 来 开启对请求参数的校验
        studentService.addStudent(student);
        return "SUCCESS";
    }
}
