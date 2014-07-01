package com.chandu0101.jaxrs.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 *  This filter adds default cachecontrol settings to response for all GET requests
 *
 * Created by chandrasekharkode on 6/29/14.
 */
@Provider
public class CacheControlFilter implements ContainerResponseFilter {


    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {
        if ("GET".equals(req.getMethod())) {
            CacheControl cc = new CacheControl();
            cc.setMaxAge(100);
            res.getHeaders().add(HttpHeaders.CACHE_CONTROL,cc);
        }

    }
}
