package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.core.util.CommonConstants;
import com.chandu0101.jaxrs.App;
import com.chandu0101.jaxrs.entity.Customer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BeanParamControllerTest {

    @Deployment(testable = false)
    public static WebArchive create() {
        WebArchive war = new TestDeployment(App.class.getPackage()).withPersistance().getWebArchive();
        System.out.println(war.toString(true));
        return war;
    }


    private WebTarget target;

    @ArquillianResource
    private URL base;


    @Before
    public void setUpClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, CommonConstants.API_PATH.concat(BeanParamController.BEAN_PARAMS)).toExternalForm()));
    }

    @Test
    public void testGetCustomerName() throws Exception {
        Form form = new Form();
        form.param("firstName","chandra");
        form.param("lastName","kode");
        final Customer customer = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Customer.class);
        assertEquals("should populate customer objec","chandra",customer.getFirstName());
        assertEquals("should populate customer objec","kode",customer.getLastName());
    }
}