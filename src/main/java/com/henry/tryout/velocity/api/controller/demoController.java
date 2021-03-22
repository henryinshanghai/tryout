package com.henry.tryout.velocity.api.controller;

import com.henry.tryout.velocity.app.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/generateTemplate")
    public void generateTemplate(){
        templateService.generate();
    }
}