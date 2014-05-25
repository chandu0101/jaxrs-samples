package com.chandu0101.jaxrs.controller;

import com.chandu0101.core.entity.Employee;
import com.chandu0101.core.rest.BaseController;
import com.chandu0101.core.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Map;

import static com.chandu0101.core.service.EmployeeService.FIRST_NAME;
import static com.chandu0101.core.service.EmployeeService.LAST_NAME;

/**
 * Created by chandrasekharkode on 5/24/14.
 */
@Path(EmployeeController.EMPLOYEES)
public class EmployeeController extends BaseController {

    public static final String EMPLOYEES = "/employees";

    @Inject
    EmployeeService employeeService;

    @GET
    @Produces("application/json")
    public Collection<Employee> getAllEmployess(@Context UriInfo uriInfo) throws Exception {
        return employeeService.getAll();
    }


    @POST
    @Consumes("application/json")
    public Response saveEmployee(@Context UriInfo uriInfo, Employee employee) throws Exception {
        return created(employeeService.save(employee));
    }


    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response update(@Context UriInfo uriInfo, @PathParam("id") String id, Map newValues) throws Exception {
        Employee emp = employeeService.get(id);
        if (emp != null) {
            if (newValues.containsKey(FIRST_NAME)) {
                emp.setFirstName((String) newValues.get(FIRST_NAME));
            }
            if (newValues.containsKey(LAST_NAME)) {
                emp.setLastName((String) newValues.get(LAST_NAME));
            }
        } else {
            throw new NotFoundException();
        }
        return created(employeeService.update(emp));
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) throws Exception {
        Employee emp = employeeService.get(id);
        if (emp == null) {
            throw new NotFoundException();
        }
        employeeService.delete(id);
    }



}
