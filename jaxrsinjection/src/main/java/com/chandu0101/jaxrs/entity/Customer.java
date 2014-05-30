package com.chandu0101.jaxrs.entity;

import javax.ws.rs.FormParam;

/**
 * Created by chandrasekharkode on 5/29/14.
 */
public class Customer {

    @FormParam("firstName")
    private String firstName;
    @FormParam("lastName")
    private String lastName;

  // we can any jaxrs inkection annotation here @pathparam,@headerparam @queryparam etc ..

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
