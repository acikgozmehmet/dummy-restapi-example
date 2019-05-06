package com.dummy.tests;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggingExample {

    Logger log = Logger.getLogger(LoggingExample.class);

    @Before
    public void before(){
        log.info("I am in the before method");
    }

    @Test
    public void test(){
        log.trace("I am in the test method: TRACE");
        log.info("I am in the test method: INFO");
        log.warn("I am in the test method: WARN ");
        RestAssured.baseURI = "https://uinames.com/api/";

        RestAssured.get();
    }

    @After
    public void after(){
        log.info("I am in the after method");
    }
}
