package com.henry.tryout.velocity.app.service.impl;

import com.henry.tryout.velocity.app.service.TemplateService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

/**
 * 生成模板服务默认实现
 *
 * @author yisheng.mikelv@foxmail.com 2020/3/11 0:20
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Override
    public void generate() {
        VelocityEngine ve = new VelocityEngine();
        //设置资源路径
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //初始化
        ve.init();

        //载入模板
        Template t = ve.getTemplate("templates/controller.java.vm");

        //定义替换规则
        VelocityContext context = new VelocityContext();
        context.put("package","org.developer.velocity.api");
        context.put("className","velocityDemo");
        context.put("Object","Value");

        //存储合并后的结果
        StringWriter sw = new StringWriter();
        t.merge(context,sw);
        String r = sw.toString();

        logger.info("##r:   \n" +r);

    }
}