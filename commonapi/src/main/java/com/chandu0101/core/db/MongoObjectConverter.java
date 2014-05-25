package com.chandu0101.core.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.lang.reflect.Field;

/**
 * Created by chandrasekharkode on 5/22/14.
 */

public final class MongoObjectConverter {


    public static final String ID = "id";
    public static final String _ID = "_id";

    private MongoObjectConverter() {
    }

    /**
     * creates object from MongoDB DBObject
     *
     * @param dbObject
     * @param classType
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static <T> T fromDBObject(DBObject dbObject, Class<T> classType) throws Exception {
        T result = null;
        if (dbObject != null) {
            result = classType.getConstructor().newInstance();
            for (Field field : classType.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(result, dbObject.get(field.getName()));
            }
            for (Field field : classType.getSuperclass().getDeclaredFields()) {
                if (ID.equalsIgnoreCase(field.getName())) {
                    field.setAccessible(true);
                    field.set(result, dbObject.get(_ID).toString());
                    break;
                }
            }
        }
        return result;
    }


    /**
     * creates MongoDB basicDB object from given instance ,_id field ignored
     *
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static DBObject toDBObject(Object object) throws Exception {
        DBObject dbObject = null;
        if (object != null) {
            dbObject = new BasicDBObject();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                dbObject.put(field.getName(), field.get(object));
            }
        }
        return dbObject;
    }
}
