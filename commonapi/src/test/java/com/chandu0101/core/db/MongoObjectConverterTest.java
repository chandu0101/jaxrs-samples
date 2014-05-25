package com.chandu0101.core.db;

import com.chandu0101.core.entity.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;

import static com.chandu0101.core.db.MongoObjectConverter.fromDBObject;
import static com.chandu0101.core.db.MongoObjectConverter.toDBObject;
import static org.junit.Assert.assertEquals;

public class MongoObjectConverterTest {

    public static final String CHANDRA = "Chandra";
    public static final String KODE = "Kode";
    public static final String ID = "1234";

    @Test
    public void testFromDBObject() throws Exception {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("_id", ID);
        dbObject.put("firstName", CHANDRA);
        dbObject.put("lastName", KODE);
        Employee emp = fromDBObject(dbObject, Employee.class);
        assertEquals("should return firstname", CHANDRA, emp.getFirstName());
        assertEquals("should return lastName", KODE, emp.getLastName());
        assertEquals("should return id", ID, emp.getId());

    }


    @Test
    public void testToDBObject() throws Exception {
        Employee entity = new Employee();
        entity.setId(ID);
        entity.setFirstName(CHANDRA);
        entity.setLastName(KODE);
        DBObject dbObject = toDBObject(entity);
        assertEquals("should return firstname", CHANDRA, dbObject.get("firstName"));
        assertEquals("should return lastname", KODE, dbObject.get("lastName"));
        assertEquals("should return null for id", null, dbObject.get("_id"));

    }
}