package com.henry.tryout.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class HelloWorld {
    public static void main(String[] args) {
        /*  step1  get and initialize an engine  */
        VelocityEngine ve = new VelocityEngine();
        ve.init();

        /*  step2  get the Template  */
        Template t = ve.getTemplate( "src/main/resources/templates/helloworld.vm" ); // E:\develop\tryout\src\main\resources\templates\helloworld.vm

        /*  step3 create a context and add data */
        VelocityContext context = new VelocityContext();
        context.put("name", "World");

        /* step4 now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        /* show the World */
        System.out.println( writer.toString() );
    }
}
