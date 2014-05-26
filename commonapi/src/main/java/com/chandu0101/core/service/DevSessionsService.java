package com.chandu0101.core.service;

import com.chandu0101.core.entity.Session;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import static com.chandu0101.core.db.MongoConnectionFactory.getDB;
import static com.chandu0101.core.db.MongoObjectConverter.fromDBObject;

/**
 * Created by chandrasekharkode on 5/25/14.
 */
@Singleton
public class DevSessionsService extends BaseService{

    public static final String DEV_SESSIONS = "devsessions";

    DBCollection devSessionsCollection;

    @PostConstruct
    public void initDB() {
        try {
            devSessionsCollection = getDB().getCollection(DEV_SESSIONS);
            insertTestData(DEV_SESSIONS, devSessionsCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Session> getAllSessions() throws Exception {
        Collection<Session> result = new ArrayList<>();
        final DBCursor cursor = devSessionsCollection.find();
        try {
            for (DBObject dbObject : cursor.toArray()) {
                result.add(fromDBObject(dbObject, Session.class));
            }
        } finally {
            cursor.close();
        }
        return result;
    }
}
