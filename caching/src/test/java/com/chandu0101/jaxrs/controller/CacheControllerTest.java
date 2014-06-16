package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.core.util.CommonConstants;
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
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class CacheControllerTest {

    public static final String E_TAG = "ETag";

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
        target = client.target(URI.create(new URL(base, CommonConstants.API_PATH.concat(CacheController.CACHE)).toExternalForm()));
    }

    @Test
    public void testGetBook() throws Exception {
        final Response response = target.path("/book").request().get();
        final String cacheControl = (String) response.getHeaders().getFirst("Cache-Control");
        assertTrue("should return cache information in reponse ",
                cacheControl.contains("max-age=60") && cacheControl.contains("private") && cacheControl.contains("no-store"));

    }

    @Test
    public void testGteVideo() throws Exception {
        final Response response = target.path("/video").request().get();
        String videoFIle = response.readEntity(String.class);
        assertEquals("should send etag of videofile in response header", "\"" + videoFIle.hashCode() + "\"", response.getHeaderString(E_TAG));
        final Response response1 = target.path("/video").request().header("If-None-Match", videoFIle.hashCode()).get();
        assertEquals("should return 304 not modified", 304, response1.getStatus());
    }

    @Test
    public void testUpdateBook() throws Exception {
        final Response response = target.path("/4").request().header("If-Match", "old text book".hashCode()).put(Entity.entity("new text", APPLICATION_JSON));
        assertEquals(" should  return precondition failed", 412, response.getStatus());

    }
}