package com.chandu0101.jaxrs.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static com.chandu0101.core.util.KeyValueArrowMarshaller.marshall;
import static com.chandu0101.core.util.CommonConstants.APPLICATION_ASON;


/**
 * Marshaller for data in the form of
 * {username->test,password->pass}
 * <p>
 * Created by chandrasekharkode on 5/31/14.
 */
@Provider
@Produces(APPLICATION_ASON)
public class ASONMarshaller implements MessageBodyWriter {
    @Override
    public boolean isWriteable(Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true; // usually check for isAnnotationPresent
    }

    @Override
    public long getSize(Object o, Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Object o, Class aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap multivaluedMap, OutputStream stream) throws IOException, WebApplicationException {
        stream.write(marshall(o));
    }
}
