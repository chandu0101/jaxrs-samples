package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.core.entity.User;
import com.chandu0101.core.util.CommonConstants;
import com.chandu0101.jaxrs.App;
import com.chandu0101.jaxrs.provider.ASONMarshaller;
import com.chandu0101.jaxrs.provider.ASONUnmarshaller;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.chandu0101.core.util.CommonConstants.APPLICATION_ASON;


@RunWith(Arquillian.class)
public class UserControllerTest {

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
        Client client = ClientBuilder.newClient().register(ASONUnmarshaller.class).register(ASONMarshaller.class);
        target = client.target(URI.create(new URL(base, CommonConstants.API_PATH.concat(UserController.USERS)).toExternalForm()));
    }

    @Test
    public void testGetUser() throws Exception {
        final User user = target.request(APPLICATION_ASON).get(User.class);
        assertEquals("should get user object", "test", user.getUsername());
        assertEquals("should get user object", "pass", user.getPassword());

    }

    @Test
    public void testSaveUser() throws Exception {
        User user = new User("test", "pass");
        final User user1 = target.request(APPLICATION_ASON).post(Entity.entity(user, APPLICATION_ASON), User.class);
        assertTrue("should persist object", user.equals(user1));
    }
}