package com.chandu0101.jaxrs.controller;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * Created by chandrasekharkode on 5/29/14.
 */
@Path(HeaderParamController.HEADER_PARAMS)
public class HeaderParamController {

    public static final String HEADER_PARAMS = "/headerparams";

    @GET
    public String getToken(@HeaderParam("token") String token) {
        return token;
    }

    /**
     * accessing headers from httpheader
     *
     * @param headers
     * @return
     */

    @GET
    public String getType(@Context HttpHeaders headers) {
        return headers.getHeaderString("type");
    }

}
