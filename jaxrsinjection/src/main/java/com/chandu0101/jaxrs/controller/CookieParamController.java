package com.chandu0101.jaxrs.controller;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by chandrasekharkode on 5/29/14.
 */
@Path(CookieParamController.COOKIE_PARAMS)
public class CookieParamController {

    public static final String  COOKIE_PARAMS = "/cookieparams";

    @GET
    public String getSessionID(@CookieParam("sessionid") String sessionid) {
       return sessionid;
    }

}
