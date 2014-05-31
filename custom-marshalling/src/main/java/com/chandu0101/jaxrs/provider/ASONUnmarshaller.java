package com.chandu0101.jaxrs.provider;

import com.chandu0101.core.util.CommonConstants;
import com.chandu0101.jaxrs.controller.UserController;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static com.chandu0101.core.util.CommonConstants.APPLICATION_ASON;
import static com.chandu0101.core.util.KeyValueArrowMarshaller.unmarshall;
import static com.chandu0101.core.util.CommonConstants.APPLICATION_ASON;


/**
 * Created by chandrasekharkode on 5/31/14.
 */

@Provider
@Consumes(APPLICATION_ASON)
public class ASONUnmarshaller implements MessageBodyReader {

    @Override
    public boolean isReadable(Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }


    @Override
    public Object readFrom(Class aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        Object result = null;
        try {
            result = unmarshall(inputStream, aClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
