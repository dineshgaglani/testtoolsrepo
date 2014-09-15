package com.test.demo.service;

import com.test.demo.service.pojos.CrudRequest;
import com.test.demo.service.pojos.CrudResponse;

import javax.ws.rs.*;

/**
 * Created by dgaglani on 9/14/14.
 */
@Path("/crudservice")
public class CrudServiceResource {

    public static DBProvider dbProvider = new DBProvider();

    @Path("/json")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public CrudResponse insertIntoDb(CrudRequest request) {
        CrudResponse crudResponse = new CrudResponse();
        try {
            crudResponse.setKey(DBOperator.writeToDb(dbProvider, request.getContent(), request.getKey()));
            crudResponse.setContent(request.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return crudResponse;
    }

    @Path("/json")
    @GET
    @Produces("application/json")
    public CrudResponse getContentForKey(@QueryParam("key") String key) {
        CrudResponse crudResponse = new CrudResponse();
        try {
            crudResponse.setKey(key);
            crudResponse.setContent(DBOperator.readFromDb(dbProvider, key));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return crudResponse;
    }

    @Path("/json")
    @DELETE
    @Produces("application/json")
    public CrudResponse deleteContentForKey(@QueryParam("key") String key) {
        CrudResponse crudResponse = new CrudResponse();
        try {
            DBOperator.removeFromDb(dbProvider, key);
            crudResponse.setKey(null);
            crudResponse.setContent(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return crudResponse;
    }

}
