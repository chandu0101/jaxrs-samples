jaxrs-samples
==============

Where I learn about Jaxrs 2.0 restful webservices

**Environment** :  Java SE8 , Java EE7 ,RESTFUL , Arquillian , MongoDB , IntellijIDEA
  
**Module Dependency** :

![module dependency](/commonapi/src/main/resources/modules.png?raw=true)

**jaxrs_samples : Parent Project**

 **Commonapi** :  Common project for all other modules ,which provides services,utils etc ...

 **Helloworld** : Just Simple jaxrs 2.0 RESTFUL helloworld message app

 **HtppMethods** : Jaxrs 2.0 app which will demonstrate http method bindings

                     • @javax.ws.rs.GET
                     • @javax.ws.rs.PUT
                     • @javax.ws.rs.POST
                     • @javax.ws.rs.DELETE
                     
  **SubResourceLocator** :   Subresource lo‐ cators are Java methods annotated with @Path, but with no HTTP method annotation, like @GET, applied to them. This type of method returns an object that is, itself, a JAX- RS annotated service that knows how to dispatch the remainder of the request.
                           [More Info](http://docs.oracle.com/javaee/6/tutorial/doc/gknav.html)
                           
  **Jaxrs Injection** : Examples of jaxrs injections 
  
                            @javax.ws.rs.PathParam
                            This annotation allows you to extract values from URI template parameters.
                            @javax.ws.rs.MatrixParam
                            This annotation allows you to extract values from URI matrix parameters.
                            @javax.ws.rs.QueryParam
                            This annotation allows you to extract values from URI query parameters.
                            @javax.ws.rs.FormParam
                            This annotation allows you to extract values from posted form data.
                            @javax.ws.rs.HeaderParam
                            This annotation allows you to extract values from HTTP request headers.
                            @javax.ws.rs.CookieParam
                            This annotation allows you to extract values from HTTP cookies set by the client.
                            @javax.ws.rs.BeanParam
                            It allows you to inject an application-specific class whose property methods or fields are annotated with any of the injection parameters mentioned above
                            
 **Custom Marshalling** :     Example of marshalling/unmarshalling a custom format
                              {key -> value ,key -> value} using javax.ws.rs.ext.MessageBodyReader and         javax.ws.rs.ext.MessageBodyWriter.
                            
**Caching ** :  Examples of using HTTP 1.1  CacheControl , caching using ETAG , conditional  updates etc ..
