package com.chandu0101.core.service;

import com.chandu0101.core.entity.Session;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.chandu0101.core.db.MongoConnectionFactory.getDB;
import static com.chandu0101.core.db.MongoObjectConverter.fromDBObject;

/**
 * Created by chandrasekharkode on 5/25/14.
 */

@Singleton
public class StageSessionService extends BaseService {

    public static final String STAGE_SESSIONS = "stagesessions";

    DBCollection stageSessionsCollection;

    @PostConstruct
    public void initDB() {
        try {
            stageSessionsCollection = getDB().getCollection(STAGE_SESSIONS);
            insertTestData(STAGE_SESSIONS, stageSessionsCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Collection<Session> getAllSessions() throws Exception {
        Collection<Session> result = new ArrayList<>();
        try (DBCursor cursor = stageSessionsCollection.find()) {
            for (DBObject dbObject : cursor.toArray()) {
                result.add(fromDBObject(dbObject, Session.class));
            }
        }
        return result;
    }
}
