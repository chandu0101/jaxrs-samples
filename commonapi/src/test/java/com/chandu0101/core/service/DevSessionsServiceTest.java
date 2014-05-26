package com.chandu0101.core.service;

import com.chandu0101.core.entity.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DevSessionsServiceTest {
    private DevSessionsService devSessionsService;

    @Before
    public void setUp() throws Exception {
        devSessionsService = new DevSessionsService();
        devSessionsService.initDB();
    }

    @After
    public void tearDown() throws Exception {
        devSessionsService = null;
    }

    @Test
    public void testGetAllSessions() throws Exception {
        List<Session> sessions = (List<Session>) devSessionsService.getAllSessions();
        assertEquals("Should return 5 session records" , sessions.size(),5);
        assertTrue("should return dev sessions",sessions.get(0).getUrl().contains("dev"));
    }
}