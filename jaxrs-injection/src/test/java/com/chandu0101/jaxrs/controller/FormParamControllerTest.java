package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.jaxrs.App;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.chandu0101.core.util.CommonConstants.API_PATH;
import static com.chandu0101.jaxrs.controller.FormParamController.FORM_PARAMS;
import static javax.ws.rs.client.Entity.*;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class FormParamControllerTest {


    @Deployment(testable = false)
    public static WebArchive create() {
        WebArchive war = new TestDeployment(App.class.getPackage()).getWebArchive();
        System.out.println(war.toString(true));
        return war;
    }

    private WebTarget target;
    @ArquillianResource
    private URL base;

    @Before
    public void setUpClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, API_PATH.concat(FORM_PARAMS)).toExternalForm()));
    }


    @Test
    public void testAuthenticate() throws Exception {
        Form form = new Form();
        form.param("username", "testuser");
        form.param("password", "testpass");
        final String response = target.request(APPLICATION_JSON).post(entity(form, APPLICATION_FORM_URLENCODED_TYPE), String.class);
        assertEquals("should authenticate user","SUCCESS",response);
    }
}