package com.dummy.step_definitions;

import com.dummy.utilities.ConfigurationReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.junit.BeforeClass;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import utilities.APIRunner;
//import utilities.Driver;

public class Hooks extends Commons {

/*
    @BeforeClass
    public static void setUpClass(){
        RestAssured.baseURI = ConfigurationReader.getProperty("baseurl");
    }
*/



    @Before
    public void setUp(Scenario scenario){
        System.out.println("This is running before each scenario");
        logger.info("****************************** Starting scenario  execution *****************************************\n");
//        RestAssured.baseURI = baseURI;
//        System.out.println("requestSpecification; = " +requestSpecification);
//        System.out.println("response; = " +response);

    }

    @After
    public void tearDown(Scenario scenario){

/*
        if(scenario.isFailed() && Driver.getDriverReference() != null) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.embed(screenshot, "image/png");
        }


        if(scenario.isFailed() && APIRunner.getCr() != null){
            scenario.write(APIRunner.getResponse().getJsonResponse());
        }
*/
        if(scenario.isFailed() ){
//            System.out.println(" string as response " + response.asString());
//            scenario.write(response.asString());
             scenario.write("Failed");
            logger.error("scenario failed");
        }

        logger.info("\n****************************** Ending scenario  execution *****************************************\n");

    }
}
