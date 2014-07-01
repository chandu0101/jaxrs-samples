package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.core.util.CommonConstants;
import com.chandu0101.core.util.StringUtils;
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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.chandu0101.jaxrs.controller.FilterController.REQUEST_FILTER;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class FilterControllerTest {


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
        target = client.target(URI.create(new URL(base, CommonConstants.API_PATH.concat(REQUEST_FILTER)).toExternalForm()));
    }
    @Test
    public void negativeTestAuthenticateFilter() throws Exception {

        final Response response = target.request().header(HttpHeaders.AUTHORIZATION, "hackit").get();
        assertEquals("should return auth failed ", 401,response.getStatus());

    }

    @Test
    public void postiveTestAuthenticateFilter() throws Exception {

        final String response = target.request().header(HttpHeaders.AUTHORIZATION, "secret").get(String.class);
        assertEquals("should not throw unauth" ,"The Data",response);

    }

    @Test
    public void testCacheControl() throws Exception {

       final Response response = target.request().header(HttpHeaders.AUTHORIZATION, "secret").get();
        final String cc = response.getHeaders().getFirst(HttpHeaders.CACHE_CONTROL).toString();
       assertTrue("should append cache control in response",cc.contains("max-age=100"));
    }
}