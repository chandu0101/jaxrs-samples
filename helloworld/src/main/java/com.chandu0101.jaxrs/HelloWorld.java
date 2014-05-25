package com.chandu0101.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by chandrasekharkode on 5/20/14.
 */
@Path("/hello")
public class HelloWorld {

    @GET
    public String sayHello() {
        return "Hello From JaxRS 2.0 !";
    }
}
