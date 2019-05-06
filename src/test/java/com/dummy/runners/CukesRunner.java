package com.dummy.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports",
//                "json:target/json-reports/cucumber.json"
                "json:target/cucumber.json"
        },

        features = {"src/test/resources/features"},
        glue = "com/dummy/step_definitions",
        dryRun = false,
        tags ={ "@wip" }

//        tags ={ "@del" }
        //tags ={ "@wip" }
)
public class CukesRunner {
}
