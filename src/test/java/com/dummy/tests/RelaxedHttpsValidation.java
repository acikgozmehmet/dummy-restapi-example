package com.dummy.tests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class RelaxedHttpsValidation {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://untrusted-root.badssl.com/";
    }

    @Test
    public void relax(){
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.get().then().log().all().statusCode(200);

    }

}
