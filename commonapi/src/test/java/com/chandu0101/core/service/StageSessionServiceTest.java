package com.chandu0101.core.service;

import com.chandu0101.core.entity.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StageSessionServiceTest {

    private StageSessionService stageSessionService;

    @Before
    public void setUp() throws Exception {
      stageSessionService = new StageSessionService();
      stageSessionService.initDB();
    }

    @After
    public void tearDown() throws Exception {
      stageSessionService = null;
    }

    @Test
    public void testGetAllSessions() throws Exception {
        List<Session> sessions = (List<Session>) stageSessionService.getAllSessions();
      assertEquals("Should return 5 session records" , sessions.size(),5);
        assertTrue("should return stage session" ,sessions.get(0).getUrl().contains("stage"));
    }
}