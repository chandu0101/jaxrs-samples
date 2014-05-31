package com.chandu0101.jaxrs.controller;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by chandrasekharkode on 5/25/14.
 */
@Path(SessionsController.SESSIONS)
public class SessionsController {

    public static final String SESSIONS = "/sessions";

    @Inject
    private  StageSessionsController stageSessionsController;

    @Inject
    private DevSessionsController devSessionsController;

    // Subresource locator (this method only annotated with @path there is no http binding
    // subresource returned by this method will process request
    @Path("{dbtype}")
    public SubResource getSubResource(@Context UriInfo uriInfo, @PathParam("dbtype") String dbtype) {
        SubResource subResource = null;
        if ("stage".equalsIgnoreCase(dbtype)) subResource = stageSessionsController;
        if ("dev".equalsIgnoreCase(dbtype)) subResource = devSessionsController;
        return subResource;
    }

}
