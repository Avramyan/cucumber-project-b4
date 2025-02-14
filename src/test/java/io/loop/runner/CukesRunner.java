package io.loop.runner;

import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "io/loop/step_definitions",
        dryRun = true,
        tags = "@AATest1 or @AATest2"
)

public class CukesRunner {

}
