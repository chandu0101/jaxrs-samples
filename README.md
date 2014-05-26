learning_jaxrs
==============

Where I learn about Jaxrs 2.0 restful webservices

**Environment** :  Java SE8 , Java EE7 ,RESTFUL , Arquillian , MongoDB , IntellijIDEA
  
**Module Dependency** :

![module dependency](/commonapi/src/main/resources/modules.png?raw=true)

**learning_jaxrs : Parent Project**

 **Commonapi** :  Common project for all other modules ,which provides services,utils etc ...

 **Helloworld** : Just Simple jaxrs 2.0 RESTFUL helloworld message app

 **HtppMethods** : Jaxrs 2.0 app which will demonstrate http method bindings

                     • @javax.ws.rs.GET
                     • @javax.ws.rs.PUT
                     • @javax.ws.rs.POST
                     • @javax.ws.rs.DELETE
                     
  **SubResourceLocator** :   Subresource lo‐ cators are Java methods annotated with @Path, but with no HTTP method annotation, like @GET, applied to them. This type of method returns an object that is, itself, a JAX- RS annotated service that knows how to dispatch the remainder of the request.
                           [More Info](http://docs.oracle.com/javaee/6/tutorial/doc/gknav.html)