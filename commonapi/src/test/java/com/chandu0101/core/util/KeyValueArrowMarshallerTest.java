package com.chandu0101.core.util;

import com.chandu0101.core.entity.User;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.util.zip.InflaterInputStream;

import static com.chandu0101.core.util.KeyValueArrowMarshaller.marshall;
import static com.chandu0101.core.util.KeyValueArrowMarshaller.unmarshall;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class KeyValueArrowMarshallerTest {

    @Test
    public void testMarshall() throws Exception {
        User user = new User("test", "pass");
        String data = new String(marshall(user));
        System.out.println(data);
        assertTrue("should marshall object", data.contains("test") && data.contains("pass"));
    }

    @Test
    public void testUnmarshall() throws Exception {
        String data = "{username->test,password->pass}";
        User user = unmarshall(IOUtils.toInputStream(data),User.class);
        assertEquals("should unmarshall text" ,"test",user.getUsername());
        assertEquals("should unmarshall text" ,"pass",user.getPassword());
    }

}