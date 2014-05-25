package com.chandu0101.core.rest;

import com.chandu0101.core.entity.BaseEntity;

import javax.ws.rs.core.Response;
import javax.xml.stream.events.EntityReference;
import java.net.URI;

/**
 * Created by chandrasekharkode on 5/24/14.
 */
public abstract class BaseController {

    /**
     * //TODO HATEOAS
     *
     * @param resource
     * @return
     */
    protected EntityReference entityResource(Object resource) {
        return null;
    }

    /**
     * //TODO HATEOAS
     *
     * @param resource
     * @return
     */
    protected CollectionResource collectionResource(Object resource) {
        return null;
    }

    /**
     * //TODO HATEOAS
     *
     * @param resource
     * @return
     */
    protected Response created(BaseEntity resource) {
        return Response.created(URI.create(resource.getId())).entity(resource).build();
    }

}
