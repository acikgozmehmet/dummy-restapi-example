package com.dummy.step_definitions;

import com.dummy.tests.LoggingExample;
import com.dummy.utilities.ConfigurationReader;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

public abstract class Commons {

//    protected String baseURI = ConfigurationReader.getProperty("baseurl");
    protected RequestSpecification requestSpecification;
    protected Response response;

    Logger logger = Logger.getLogger(Commons.class);

    static {
        RestAssured.baseURI = ConfigurationReader.getProperty("baseurl");

    }




}
