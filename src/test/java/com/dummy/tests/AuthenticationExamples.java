package com.dummy.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthenticationExamples {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com/";
    }


    @Test
    public void usingApiKey(){
        RestAssured.given().log().all().
                                  queryParam("t", "Kung Fury").
                                  queryParam("apikey", "6b1130c4").
                    when().get().then().statusCode(200);

    }


    @Test
    public void basicAuthentication401Validation(){
        RestAssured.baseURI = "https://the-internet.herokuapp.com/basic_auth";
        RestAssured.get().then().assertThat().statusCode(401);
    }

    @Test
    public void basicAuthentication(){
        RestAssured.baseURI = "https://the-internet.herokuapp.com/basic_auth";
        RestAssured.given().auth().basic("admin", "admin").when().get().then().assertThat().statusCode(200);
    }


}
