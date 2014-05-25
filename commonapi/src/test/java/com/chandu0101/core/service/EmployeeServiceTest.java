package com.chandu0101.core.service;

import com.chandu0101.core.entity.Employee;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static com.chandu0101.core.service.EmployeeService.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class EmployeeServiceTest {


    private EmployeeService employeeService;

    @Before
    public void setUp() throws Exception {
        employeeService = new EmployeeService();
        employeeService.initDB();
    }

    @After
    public void tearDown() throws Exception {
        employeeService = null;
    }


    @Test
    public void testSave() throws Exception {
        Employee emp = new Employee(CHANDRA, KODE);
        Employee savedEmp = employeeService.save(emp);
        emp.setId(savedEmp.getId());
        assertEquals("should  persist emp object ", emp, savedEmp);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Employee> employees = employeeService.getAll();
        assertTrue("should return 2 or more objects", employees.size() >= 4);
    }

    @Test
    public void testGetByQuery() throws Exception {
        Employee emp = employeeService.getByQuery(searchErlichBachman);
        assertEquals("should return richard firstname", ERLICH, emp.getFirstName());
        assertEquals("should return hendrick lastname", BACHMAN, emp.getLastName());
    }

    @Test
    public void testGet() throws Exception {
        Employee emp = employeeService.getByQuery(searchErlichBachman);
        Employee emp2 = employeeService.get(emp.getId());
        assertEquals("should return employee object for id ", emp, emp2);
    }

    @Test
    public void testUpdate() throws Exception {
        Employee emp = employeeService.getByQuery(searchErlichBachman);
        emp.setFirstName(ERLICH.concat(NEW));
        Employee empUpdated = employeeService.update(emp);
        assertEquals("should update first name ", ERLICH.concat(NEW), empUpdated.getFirstName());
        empUpdated.setFirstName(ERLICH);
        employeeService.update(empUpdated);
    }

    @Test
    public void testDelete() throws Exception {
        Employee richard = employeeService.getByQuery(searchRichardHendricks);
        assertTrue("should return object", richard != null);
        employeeService.delete(richard.getId());
        richard = employeeService.getByQuery(searchRichardHendricks);
        assertTrue("should delete richard object ", richard == null);
    }

}