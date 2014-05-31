package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.service.DevSessionsService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

/**
 * Created by chandrasekharkode on 5/25/14.
 */
public class DevSessionsController implements SubResource {
    @Inject
    private DevSessionsService devSessionsService;

    @GET
    @Produces("application/json")
    public Collection<?> getAll(@Context UriInfo uriInfo) throws Exception {
        return devSessionsService.getAllSessions();
    }
}
