package com.dummy.tests;

import com.dummy.step_definitions.Commons;
import com.dummy.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matcher.*;

public class SimpleRestDemo extends Commons {

//    private static String RestAssured.baseURI = baseURI;

    @Test
    public void test1(){
//        RestAssured.baseURI = baseURI;
        Response response = RestAssured.given().
                                                contentType(ContentType.JSON).and().accept(ContentType.JSON).
                                        when().
                                                get();

        response.prettyPrint();

        Assert.assertEquals(200,response.statusCode());

        String responseStr = response.asString();
        Assert.assertTrue(responseStr.contains("name"));
    }


    @Test
    public void headersTest(){
//        Response response = RestAssured.get(baseURI);
        System.out.println("response = " + response.headers() );

        String contentType = response.header("Content-Type");
        System.out.println("contentType = " + contentType);
        Assert.assertTrue(contentType.contains("application/json"));
    }


    @Test
    public void statusLineTest(){
//        Response response = RestAssured.get(baseURI);
//        System.out.println(response.statusLine());
        Assert.assertTrue(response.statusLine().contains("200"));
    }



}
