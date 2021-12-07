package uk.co.mercedesbenz.runners;

import io.cucumber.java.Before;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(glue={"uk.co.mercedesbenz.steps"}, features = "src/test/resources", plugin = { "pretty", "html:target/site/cucumber-pretty",
        "json:target/cucumber.json" })

public class RunCucumberTests {
}
