package com.henry.tryout.jersey;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class JerseyControllerDemo {

    @GET
    @Path("/sendMessage")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBean sendMessage(
            HttpServletRequest request, // @Content 注解已经被取消了？
            @QueryParam("phone") String phone
    ) {
        Form form = new Form();
        form.param("phone", phone);

        String response = JerseyClientUtil.getJerseyClientUtil()
                .postInvoke("<url_placeholder>",
                        form, null, String.class);

        ResultBean resultBean = new ResultBean(response);
        return resultBean;
    }
}
