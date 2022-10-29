package com.henry.tryout.springboot;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*
    参考： https://www.cnblogs.com/yanggb/p/10859907.html
    简介：
        作用 - 给Controller控制器添加统一的操作或处理
        特征： #1 是Spring3.2中新增的注解
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionInterceptor {

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest request, Exception e) {
        String failMsg = null;
        if (e instanceof MethodArgumentNotValidException) {
            // 获取到校验直接中指定的 error message，并绑定到局部变量 failMsg上
            failMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }
        return failMsg; // 在这里直接返回给UI/Postman
    }
}
