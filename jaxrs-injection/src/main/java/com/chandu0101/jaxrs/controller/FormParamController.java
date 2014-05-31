package com.chandu0101.jaxrs.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by chandrasekharkode on 5/29/14.
 */
@Path(FormParamController.FORM_PARAMS)
public class FormParamController {
    public static final String FORM_PARAMS = "/formparams";


    @POST
    public String authenticate(@FormParam("username") String username, @FormParam("password") String password) throws Exception {
        String response = "FAILURE";
        if ("testuser".equalsIgnoreCase(username) && "testpass".equalsIgnoreCase(password)) {
            response = "SUCCESS";
        }
        return response;
    }
}
