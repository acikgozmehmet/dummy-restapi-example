package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class QueryParamsExample {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://uinames.com/api";
    }


    @Test
    public void testAmount(){
        Response response = given().queryParam("amount",2).when().get();
        response.prettyPrint();
        System.out.println("response.jsonPath().getList(\"$\", Map.class).size() = " + response.jsonPath().getList("$", Map.class).size());
        Assert.assertTrue(response.jsonPath().getList("$", Map.class).size() == 2 );

    }


    @Test
    public void testRegion(){
//        Response response = given().queryParam("region","Norway").when().get();
        Response response = given().queryParams("region","Norway", "amount", 2).when().get();
        response.prettyPrint();
    }


}
