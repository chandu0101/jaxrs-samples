package com.chandu0101.jaxrs.filter;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *  This filter will be executed first for all request coming to server
 *
 *  if token is correct it will go to go corresponding jax rs method otherwise it will throw notauthorized exception
 *
 * Created by chandrasekharkode on 6/29/14.
 */

@PreMatching
@Provider
public class AuthenticateFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
       String token = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
       if(token == null || !verifyToken(token)) {
          throw  new NotAuthorizedException("Try again");
       }
    }

    private boolean verifyToken(String token) {
        return "secret".equalsIgnoreCase(token);
    }
}
