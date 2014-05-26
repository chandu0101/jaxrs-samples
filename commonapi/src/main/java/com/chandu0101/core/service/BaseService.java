package com.chandu0101.core.service;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import java.io.IOException;
import java.util.List;

import static com.chandu0101.core.util.CommonConstants.JSON_EXTENSION;
import static com.chandu0101.core.util.FileUtils.readFileAsString;

/**
 * Created by chandrasekharkode on 5/25/14.
 */
public abstract class BaseService {

    protected  void  insertTestData(String collectionName,DBCollection collection) throws IOException {
        collection.drop();
        String jsonTestData = readFileAsString(collectionName.concat(JSON_EXTENSION));
        collection.insert((List<DBObject>) JSON.parse(jsonTestData));
    }
}
