package com.henry.tryout.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.*;

public class Velocity_HelloWorld {
    public static void main(String[] args) {
        /*  step1  get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        ve.init();

        /*  step2  get the Template  */
        Template t = ve.getTemplate( "src/main/resources/templates/series/grammar_07.vm" ); // E:\develop\tryout\src\main\resources\templates\helloworld.vm

        /*  step3 create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("name", "World");



        Customer customer = new Customer();
        customer.setFirstName("liu");
        customer.setLastName("henry");
        customer.setBehaviour("pick up the bottle");
        context.put("customer", customer);

        List<Customer> customerList =  Arrays.asList(
                new Customer("liu", "heng"),
                new Customer("zheng", "mengqin"),
                new Customer("fu", "jin"),
                new Customer("xu", "zhenyu"),
                new Customer("ding", "hao"),
                new Customer("li", "weijun"),
                new Customer("deng", "kai")
        );
        context.put("customerList", customerList);

        Map<String, Integer> basicInfos = new HashMap<>();
        basicInfos.put("henry", 26);
        basicInfos.put("jane", 27);
        basicInfos.put("jeniffer", 30);
        context.put("basicInfos", basicInfos);

        /* step4 now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */
        System.out.println( writer.toString() );
    }
}
