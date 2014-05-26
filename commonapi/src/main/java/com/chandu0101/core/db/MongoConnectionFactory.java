package com.chandu0101.core.db;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by chandrasekharkode on 5/25/14.
 */
public class MongoConnectionFactory {

    private static DB db;

    public static DB getDB() throws UnknownHostException {
        if (db == null) {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            db = mongoClient.getDB("test");
        }
        return db;
    }
}
