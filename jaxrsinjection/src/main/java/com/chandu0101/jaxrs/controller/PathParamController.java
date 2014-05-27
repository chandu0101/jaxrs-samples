package com.chandu0101.jaxrs.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @PathParam examples
 * <p>
 * Created by chandrasekharkode on 5/26/14.
 */
@Path(PathParamController.PATH_PARAMS)
public class PathParamController {
    public static final String PATH_PARAMS = "/pathparams";

    @GET
    @Path("{first}-{last}")
    public String getFirstNameAndLastName(@PathParam("first") String first, @PathParam("last") String last) {
        return "Got First Name : " + first + " Last Name  : " + last;
    }

    /**
     * this method is example of getting whole pathsegment using pathparam injection
     * /pathprams/bmw328;color=orange
     */
    @GET
    @Path("{model}")
    public String getModelColor(@PathParam("model") PathSegment segment) {

        return segment.getMatrixParameters().getFirst("color");
    }

    /**
     * This method is an example of getting matching multiple path segments
     * /pathparams/bmw/328i/year
     */
    @GET
    @Path("{model : .+}/year")
    public String getSubModel(@PathParam("model") List<PathSegment> segments) {
        return segments.get(1).getPath();
    }

    /**
     * This method is an example to get path parameters UriInfo
     */
    @GET
    @Path("{model}/{year}")
    public String getModelColorAndYear(@Context UriInfo uriInfo) {
        String year = uriInfo.getPathParameters().getFirst("year");
        String color = uriInfo.getPathSegments().get(1).getMatrixParameters().getFirst("color");
        return year + " " + color;
    }


}
