package com.chandu0101.jaxrs.controller;

import com.chandu0101.jaxrs.entity.Customer;


import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by chandrasekharkode on 5/29/14.
 */
@Path(BeanParamController.BEAN_PARAMS)
public class BeanParamController {
    public static final String BEAN_PARAMS = "/beanparams";

    @POST
    @Produces(APPLICATION_JSON)
    public Customer getCustomerName(@BeanParam Customer newCustomer) throws Exception {
        return newCustomer;
    }
}
