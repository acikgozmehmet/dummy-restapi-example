package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PostRequestExample {

    String accessToken;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
    }


    @Test
    public void createTeam(){
        Response response = RestAssured.given().log().all().param("email","teacherva5@gmail.com").
        param("password","maxpayne").basePath("sign").when().get();

        response.then().log().all().assertThat().statusCode(200);

        accessToken = response.jsonPath().get("accessToken");
        System.out.println("accessToken = " + accessToken);

//        Map map = new HashMap();
//        map.put("campus-location", "IL");
//        map.put("batch-number", 8);
//        map.put("team-name", "Thunders27");
        


        Response response1 = RestAssured.given().basePath("api/teams/team").accept(ContentType.JSON).and().
                                                 contentType(ContentType.JSON).and().
                queryParam("campus-location", "IL").and().
                queryParam("batch-number", 8).and().
                queryParam("team-name", "Thunders60").
                and().header("Authorization", accessToken).
                                          when().
                                                 post();
        response1.prettyPrint();

        Assert.assertEquals(response1.statusCode(), 201);
        System.out.println("response1.statusCode() = " + response1.statusCode());


    }
}
