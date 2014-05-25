package com.chandu0101.jaxrs;

import com.chandu0101.jaxrs.controller.EmployeeController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chandrasekharkode on 5/20/14.
 */
@ApplicationPath(App.API_PATH)
public class App extends Application {

    public static final String API_PATH  = "api";

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
         classes.add(EmployeeController.class);
        return  classes;

    }
}
