package com.chandu0101.jaxrs.controller;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;

/**
 *  Examples using @MatrixParam annotation
 * Created by chandrasekharkode on 5/26/14.
 */
@Path(MatrixParamController.MATRIX_PARAMS)
public class MatrixParamController {
    public static final String  MATRIX_PARAMS = "/matrixparams";

    /**
     * matrix param example
     * /bmw;color=yellow
     * Note : if you have two path segments with color matrix param then @MatrixParam would be
     *  ambiguous ,in this case we must use pathsegments
     */
    @GET
    @Path("{model}")
    public String getCarColor( @MatrixParam("color") String color) {
       return  color;
    }
}
