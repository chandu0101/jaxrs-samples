package com.chandu0101.core.service;

import com.chandu0101.core.entity.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import static com.chandu0101.core.db.MongoConnectionFactory.getDB;
import static com.chandu0101.core.db.MongoObjectConverter.fromDBObject;
import static com.chandu0101.core.db.MongoObjectConverter.toDBObject;

/**
 * Created by chandrasekharkode on 5/20/14.
 */
@Singleton
public class EmployeeService extends BaseService {

    private static final String EMPLOYEES = "employees";
    public static final String ID = "_id";

    //test data
    public static final String KODE = "Kode";
    public static final String CHANDRA = "Chandra";

    public static final String ERLICH = "Erlich";
    public static final String BACHMAN = "Bachman";
    public static final String NEW = "new";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final DBObject searchErlichBachman = new BasicDBObject(FIRST_NAME, ERLICH).append(LAST_NAME, BACHMAN);
    public static final DBObject searchRichardHendricks = new BasicDBObject(FIRST_NAME, "Richard").append(LAST_NAME, "Hendriks");

    DBCollection employeeCollection;

    /*
    * Initiates MongoDB connection
    * */
    @PostConstruct
    public void initDB() {
        try {
            employeeCollection = getDB().getCollection(EMPLOYEES);
            insertTestData(EMPLOYEES, employeeCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Collection<Employee> getAll() throws Exception {
        Collection<Employee> allEmployees = new ArrayList<>();
        try (DBCursor cursor = employeeCollection.find()) {
            for (DBObject dbObject : cursor.toArray()) {
                allEmployees.add(fromDBObject(dbObject, Employee.class));
            }
        }
        return allEmployees;
    }

    public Employee get(String id) throws Exception {
        DBObject searchById = new BasicDBObject(ID, new ObjectId(id));
        DBObject dbObject = employeeCollection.findOne(searchById);
        return fromDBObject(dbObject, Employee.class);
    }

    public Employee save(Employee employee) throws Exception {
        DBObject dbObject = toDBObject(employee);
        employeeCollection.save(dbObject);
        return fromDBObject(dbObject, Employee.class);
    }


    public Employee update(Employee employee) throws Exception {
        DBObject searchById = new BasicDBObject(ID, new ObjectId(employee.getId()));
        employeeCollection.update(searchById, toDBObject(employee));
        return employee;
    }

    public void delete(String id) {
        employeeCollection.remove(new BasicDBObject(ID, new ObjectId(id)));
    }

    public Employee getByQuery(DBObject query) throws Exception {
        DBObject dbObject = employeeCollection.findOne(query);
        return fromDBObject(dbObject, Employee.class);
    }


}
