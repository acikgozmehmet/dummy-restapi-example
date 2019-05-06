package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.*;
public class RestAssuredLoggingExample {

    @Test
    public void loggingTest(){

//        baseURI = "https://uinames.com/api/";
//        Response response = given().log().all().baseUri("https://uinames.com/api/").when().get();
        given().log().all().baseUri("https://uinames.com/api/").when().get();

        Response response = when().get("https://uinames.com/api/");
        response.then().log().all();

//        response.then().log().all();



    }
}
