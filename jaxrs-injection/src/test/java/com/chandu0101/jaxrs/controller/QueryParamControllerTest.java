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
import static com.chandu0101.jaxrs.controller.QueryParamController.QUERY_PARAMS;
import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class QueryParamControllerTest {


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
        target = client.target(URI.create(new URL(base, API_PATH.concat(QUERY_PARAMS)).toExternalForm()));
    }

    @Test
    public void testGetOffsetDetails() throws Exception {
        String response = target.queryParam("valuea", 5).queryParam("valueb", 5).request().get(String.class);
        assertEquals("should return sum of valuea and valueb", Integer.parseInt(response), 10);
    }

    @Test
    public void testgetMultiplicationOfTwoNumbers() throws Exception {
        String response = target.path("context").queryParam("valuea", 5).queryParam("valueb", 5).request().get(String.class);
        assertEquals("should return multiplication of value and valueb", Integer.parseInt(response), 25);

    }
}