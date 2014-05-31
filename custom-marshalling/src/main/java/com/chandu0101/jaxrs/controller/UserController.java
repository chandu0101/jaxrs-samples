package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.Response;

/**
 * Created by chandrasekharkode on 5/31/14.
 */
@Path(UserController.USERS)
public class UserController {
    public static final String USERS = "/users";
    @GET
    @Produces("application/ason")
    public User getUser() {
       return new User("test","pass");
    }


    @POST
    @Consumes("application/ason")
    @Produces("application/ason")
    public User saveUser(User user) throws Exception {
        return user;
    }
}
