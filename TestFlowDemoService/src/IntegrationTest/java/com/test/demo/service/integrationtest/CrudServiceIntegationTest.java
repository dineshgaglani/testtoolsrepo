package com.test.demo.service.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.service.pojos.CrudRequest;
import com.test.demo.service.pojos.CrudResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by dgaglani on 9/15/14.
 */
@ContextConfiguration( locations={ "classpath:applicationContext-integration-test.xml" } )
public class CrudServiceIntegationTest extends AbstractTestNGSpringContextTests {

    @Value( "http://${test-suite.service-host}:${test-suite.service-port}/${integration-test-crud-service-path}" )
    private String baseEndpoint;

    @Test
    public void testCrudService() throws Exception {
        Client client = Client.create();
        WebResource webResource = client
                .resource(baseEndpoint);
        CrudRequest request = new CrudRequest();
        String firstRequestContent = "firstInsertIntoDb";
        request.setContent(firstRequestContent);
        //post
        ClientResponse postResponse = webResource.type("application/json").accept("application/json")
                .post(ClientResponse.class, request);
        CrudResponse firstResponse = new ObjectMapper().readValue(postResponse.getEntity(String.class), CrudResponse.class);
        //Get
        ClientResponse getResponse = webResource.queryParam("key", firstResponse.getKey()).accept("application/json")
                .get(ClientResponse.class);
        CrudResponse crudGetResponse = new ObjectMapper().readValue(getResponse.getEntity(String.class),CrudResponse.class);
        Assert.assertEquals(crudGetResponse.getContent(), firstRequestContent, "The first insert does not match ");
        //Update
        String secondRequestString = "updatedFirstInsert";
        CrudRequest secondRequest = new CrudRequest();
        secondRequest.setContent(secondRequestString);
        secondRequest.setKey(firstResponse.getKey());
        ClientResponse updatedResponse = webResource.type("application/json").accept("application/json")
                .post(ClientResponse.class, secondRequest);
        CrudResponse firstUpdatedCrudResponse = new ObjectMapper().readValue(updatedResponse.getEntity(String.class),CrudResponse.class);
        Assert.assertEquals(firstResponse.getKey(), firstUpdatedCrudResponse.getKey(), "Different keys being returned on update");
        //Get
        ClientResponse updatedGetResponse = webResource.queryParam("key", firstUpdatedCrudResponse.getKey()).accept("application/json")
                .get(ClientResponse.class);
        CrudResponse crudUpdatedGetResponse = new ObjectMapper().readValue(updatedGetResponse.getEntity(String.class),CrudResponse.class);
        Assert.assertEquals(crudUpdatedGetResponse.getContent(), secondRequestString, "Updated insert does not match");
        //Delete
        ClientResponse deleteResponse = webResource.queryParam("key", firstUpdatedCrudResponse.getKey()).accept("application/json")
                .delete(ClientResponse.class);
        Assert.assertTrue(deleteResponse.getClientResponseStatus().equals(ClientResponse.Status.OK), "delete did not return success response");
        //Get
        ClientResponse deletedGetResponse = webResource.queryParam("key", firstUpdatedCrudResponse.getKey()).accept("application/json")
                .get(ClientResponse.class);
        CrudResponse crudDeletedGetResponse = new ObjectMapper().readValue(deletedGetResponse.getEntity(String.class),CrudResponse.class);
        Assert.assertEquals(crudDeletedGetResponse.getContent(), "", "Deleted key returned content");
    }


}
