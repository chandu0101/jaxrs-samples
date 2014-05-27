package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.jaxrs.App;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.chandu0101.core.util.CommonConstants.API_PATH;
import static com.chandu0101.jaxrs.controller.PathParamController.PATH_PARAMS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class PathParamControllerTest {

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
        target = client.target(URI.create(new URL(base, API_PATH.concat(PATH_PARAMS)).toExternalForm()));
    }

    @Test
    public void testGetFirstNameAndLastName() throws Exception {
        String response = target.path("chandra-kode").request().get(String.class);
        assertTrue("should return firstname chandra", response.contains("chandra"));
        assertTrue("should return lastname kode", response.contains("kode"));

    }

    @Test
    public void testGetModelColor() throws Exception {
        final String response = target.path("bmw328i;color=orange").request().get(String.class);
        assertEquals("should return color orange", "orange", response);
    }

    @Test
    public void testGetSubModel() throws Exception {
        String response = target.path("/bmw/328i/year").request().get(String.class);
        assertEquals("should retrurn submodel 328i", "328i", response);
    }

    @Test
    public void testGetModelColorAndYear() throws Exception {
        final String resposne = target.path("bmw555;color=red/2016").request().get(String.class);
        assertTrue("should return color and year", resposne.contains("red") && resposne.contains("2016"));
    }
}