package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;



public class BaseURIDemo {

    @Test
    public void culturesTest(){
        RestAssured.baseURI = "https://api.got.show/api/";
//        Response response = RestAssured.given().accept(ContentType.JSON).and().contentType(ContentType.JSON).get("/book/cultures");
        Response response = RestAssured.get("/book/cultures");
        Assert.assertTrue(response.statusLine().contains("200"));
        response.prettyPrint();

        Assert.assertTrue(response.asString().contains("Ironborn"));
    }
}
