package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccessTokenExample {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
    }


    @Test
    public void test(){
        Response response = RestAssured.given().basePath("sign").and().queryParam("email","teacherva5@gmail.com").
                            queryParam("password","maxpayne").then().when().get();
                 response.then().log().all().assertThat().statusCode(200);
                 String token = response.jsonPath().get("accessToken");
        System.out.println("token = " + token);

        RestAssured.given().header("Authorization", token).get("/api/campuses").
                    then().log().all().assertThat().statusCode(200);


    }


    public static String getAccessToken(){
        Response response = RestAssured.given().basePath("sign").and().queryParam("email","teacherva5@gmail.com").
                            queryParam("password","maxpayne").then().when().get();
                            response.then().assertThat().statusCode(200);
            String token = response.jsonPath().get("accessToken");
            return token;
        }
}
