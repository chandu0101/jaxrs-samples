package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.core.entity.Session;
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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static com.chandu0101.jaxrs.App.API_PATH;
import static com.chandu0101.jaxrs.controller.SessionsController.SESSIONS;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class SessionsControllerTest {


    private static final String STAGE = "stage";
    private static final String DEV = "dev";

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
        target = client.target(URI.create(new URL(base, API_PATH.concat(SESSIONS)).toExternalForm()));
    }

    @Test
    public void testStageSessions() throws Exception {
        final Response response = target.path(STAGE).request(APPLICATION_JSON).get();
        List<Session> sessions = response.readEntity(new GenericType<List<Session>>() {
        });
        assertEquals("should return 5 objects ", sessions.size(), 5);
        assertTrue("should return stage sessions", sessions.get(0).getUrl().contains(STAGE));
    }

    @Test
    public void testDevSessions() throws Exception {
        final Response response = target.path(DEV).request(APPLICATION_JSON).get();
        List<Session> sessions = response.readEntity(new GenericType<List<Session>>() {
        });
        assertEquals("should return 5 objects ", sessions.size(), 5);
        assertTrue("should return stage sessions", sessions.get(0).getUrl().contains(DEV));
    }
}