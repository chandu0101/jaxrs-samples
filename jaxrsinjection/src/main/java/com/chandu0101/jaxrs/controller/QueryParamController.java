package com.chandu0101.jaxrs.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by chandrasekharkode on 5/29/14.
 */
@Path(QueryParamController.QUERY_PARAMS)
public class QueryParamController {

    public static final String QUERY_PARAMS = "/queryparams";

    @GET
    public String getSumofTwotNumbers(@QueryParam("valuea") int valuea, @QueryParam("valueb") int valueb) {
        return String.valueOf(valuea+valueb);
    }

    /**
     *   retrieving query params from uriInfo
     *
     * @param uriInfo
     * @return
     */

    @GET
    public String getMultiplicationOfTwoNumbers(@Context UriInfo uriInfo) {
        final int valuea = Integer.parseInt(uriInfo.getQueryParameters().getFirst("valuea"));
        final int valueb = Integer.parseInt(uriInfo.getQueryParameters().getFirst("valueb"));
        return String.valueOf(valuea*valueb);
    }
}
