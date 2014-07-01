package com.chandu0101.jaxrs.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by chandrasekharkode on 6/29/14.
 */

@Path(FilterController.REQUEST_FILTER)
public class FilterController {

    public static final String REQUEST_FILTER = "/requestfilter";


    @GET
    public String getData(@Context UriInfo uriInfo) throws Exception {
        return "The Data";
    }
}
