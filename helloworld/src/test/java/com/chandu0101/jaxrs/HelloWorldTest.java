package com.chandu0101.jaxrs;

import com.chandu0101.core.deployment.TestDeployment;
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
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static com.chandu0101.core.util.CommonConstants.API_PATH;
import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
public class HelloWorldTest {


    @Deployment(testable = false)
    public static WebArchive create() {
        WebArchive war = new TestDeployment(com.chandu0101.jaxrs.App.class.getPackage()).getWebArchive();
        System.out.println(war.toString(true));
        return war;
    }

    private WebTarget target;

    @ArquillianResource
    private URL base;

    @Before
    public void setUpClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, API_PATH.concat("/hello")).toExternalForm()));
    }

    @Test
    public void testSayHello() throws Exception {
        Response response = target.request().get();
        assertEquals("should return success response", 200, response.getStatus());
    }
}