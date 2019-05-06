package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WarmUp {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://api.got.show";
    }


    @Test
    public void continentsTest(){
        RestAssured.basePath = "/api/book/continents";
        Response response = RestAssured.given().contentType(ContentType.JSON).and().accept(ContentType.JSON).
                when().get();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.asString().contains("Westeros"));
    }

    @Test
    public void episodesTest(){
        RestAssured.basePath = "/api/show/episodes";
        Response response = RestAssured.
                            given().accept(ContentType.JSON).and().contentType(ContentType.JSON).
                            when().get();

        response.prettyPrint();
        Assert.assertTrue(response.statusCode()==200);
        Assert.assertTrue(response.asString().contains("Rains of Castamere"));
        response.jsonPath();
    }

    @Test
    public void testUinames(){
        RestAssured.baseURI = "https://uinames.com/api/";
        Response response = RestAssured.given().accept(ContentType.JSON).and().contentType(ContentType.JSON).
                                        when().get();

        response.prettyPrint();
        System.out.println((String)response.jsonPath().get("name"));

    }
}
