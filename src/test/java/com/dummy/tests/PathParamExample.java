package com.dummy.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

public class PathParamExample {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://api.got.show";
    }


    @Test
    public void getCityByNameTest(){

//        RestAssured.basePath = "api/show/cities/";
        Response  response = RestAssured.given().basePath("api/show/cities/{name}").
                pathParam("name","Qarth").
                when().get();
//                pathParam("name","Qarth").get();
//        Response  response = RestAssured.get("api/show/cities/");
        response.prettyPrint();
    }

    @Test
    public void getCharacterbyNameTest(){
//        RestAssured.basePath = "api/show/cities/";
//                ;
        Response response = RestAssured.given().basePath("/api/show/characters/{name}").
                pathParam("name","Jon Snow").when().get();
        response.prettyPrint();
    }



}
