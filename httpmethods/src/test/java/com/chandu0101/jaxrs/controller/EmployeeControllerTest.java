package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.deployment.TestDeployment;
import com.chandu0101.core.entity.Employee;
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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chandu0101.core.service.EmployeeService.*;
import static com.chandu0101.jaxrs.App.API_PATH;
import static com.chandu0101.jaxrs.controller.EmployeeController.EMPLOYEES;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class EmployeeControllerTest {

    @Deployment(testable = false)
    public static WebArchive create() {
        WebArchive war = new TestDeployment(App.class.getPackage()).withPersistance().getWebArchive();
        System.out.println(war.toString(true));
        return war;
    }


    private WebTarget webTarget;

    private WebTarget target;

    @ArquillianResource
    private URL base;


    @Before
    public void setUpClass() throws IOException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, API_PATH.concat(EMPLOYEES)).toExternalForm()));
    }

    @Test
    public void testGetAllEmployess() throws Exception {
        Response response = target.request(APPLICATION_JSON).get();
        List<Employee> employees = response.readEntity(new GenericType<List<Employee>>() {
        });
        assertTrue("should return records more than 4", employees.size() >= 4);
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee emp = new Employee(CHANDRA, KODE);
        Response response = target.request(APPLICATION_JSON).post(Entity.json(emp));
        Employee saved = response.readEntity(Employee.class);
        emp.setId(saved.getId());
        assertEquals("should persist object", emp, saved);
    }


    @Test
    public void testUpdate() throws Exception {
        Employee employeeInDB = getEmployee();
        Map<String, String> newvalues = new HashMap<>();
        newvalues.put(FIRST_NAME, employeeInDB.getFirstName().concat(NEW));
        employeeInDB.setFirstName(employeeInDB.getFirstName().concat(NEW));
        Response response = target.path(employeeInDB.getId()).request(APPLICATION_JSON).put(Entity.json(newvalues));
        Employee updatedEmployee = response.readEntity(Employee.class);
        assertEquals("should return updated object ",employeeInDB,updatedEmployee );
    }

    @Test
    public void testDelete() throws Exception {
        Employee employee = getEmployee();
        target.path(employee.getId()).request().delete();
        assertTrue("should delete employee",checkEmployeeExists(employee.getId()));
    }

    private Employee getEmployee() {
        Response response = target.request(APPLICATION_JSON).get();
        List<Employee> employees = response.readEntity(new GenericType<List<Employee>>() {
        });
        return employees.get(0);
    }

    private boolean checkEmployeeExists(String id) {
        Response response = target.request(APPLICATION_JSON).get();
        List<Employee> employees = response.readEntity(new GenericType<List<Employee>>() {
        });
        final long count = employees.stream().filter(e -> id.equalsIgnoreCase(e.getId())).count();
        return count == 0;
    }
}