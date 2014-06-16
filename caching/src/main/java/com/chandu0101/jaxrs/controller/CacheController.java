package com.chandu0101.jaxrs.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.concurrent.TimeUnit;

import static javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Created by chandrasekharkode on 6/8/14.
 * <p>
 * Examples of HTTP 1.1 caching
 * <p>
 * private :  only clients (mostly the browser) and no one else in the chain (like a proxy) should cache this
 * public : any entity in the chain can cache this
 * no-cache  : should not be cached anyway
 * no-store :  can be cached but should not be stored on disk (most browsers will hold the resources in memory until they will be quit)
 * no-transform  : the resource should not be modified (for example shrink image by proxy)
 * max-age :  how long the resource is valid (measured in seconds)
 * s-maxage  : same like max-age but this value is just for non clients
 */
@Path(CacheController.CACHE)
public class CacheController {

    public static final String CACHE = "/cache";
    public static final String TEXT_BOOK = "Text Book";

    /**
     * Example of basic caching for 600
     *
     * @return
     */
    @GET
    @Path("/book")
    public Response getBook() throws InterruptedException {
        String book = " Sample Text Book";
//        TimeUnit.SECONDS.sleep(5);
        final CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge((int) TimeUnit.MINUTES.toSeconds(1)); // time
        cacheControl.setPrivate(true); // cachable by only client
        cacheControl.setNoStore(true); // dont store on disk
        return Response.ok(book).cacheControl(cacheControl).build();
    }

    /**
     * Example of conditional get/ revalidation using ETAG
     * <p> if-none match
     * client will send request with etag ( has code of object)
     * if it is equal to the one exist in database it will return response
     * with no entity ,if not it will return response with new content
     *
     * @return
     */
    @GET
    @Path("/video")
    public Response getVideo(@Context Request request) {
        String videoFile = "Video Content";
        final EntityTag tag = new EntityTag(String.valueOf(videoFile.hashCode()));
        final ResponseBuilder builder = request.evaluatePreconditions(tag); // check if request header If-None-Match: has same etag as current videofile
        final CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge((int) TimeUnit.MINUTES.toSeconds(5));
        if (builder != null) {
            builder.cacheControl(cacheControl);
            return builder.build();
        }
        return Response.ok(videoFile).tag(tag).cacheControl(cacheControl).build();
    }

    /**
     * method to conditional update using if-match header in request( handling concurrency )
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateBook(@Context Request request, @PathParam("id") String id, String newText) throws Exception {

        String book = getBook(id);
        final EntityTag entityTag = new EntityTag(String.valueOf(book.hashCode()));
        final ResponseBuilder responseBuilder = request.evaluatePreconditions(entityTag); // checks if-match
        if (responseBuilder != null) { //  entity in DB is not similar to client snapshot,so we will inform the user instead of updating blindly
            return responseBuilder.build();
        }
        // perform update of book  using new text and then sent response with new saved entity
        return Response.ok().build();
    }

    private String getBook(String id) {
        return TEXT_BOOK; // usually some db call to get bookk using id ,some random
    }

}
