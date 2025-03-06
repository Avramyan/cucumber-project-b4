package io.loop.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"pretty",
                "html:target/html-reports/cucumber-report.html",
                "json:target/json-reports/json-report.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/test/resources/features",
        glue = "io/loop/step_definitions",
        dryRun = false,
        tags = "@headless",
        monochrome = true
)

public class CukesRunner {
    /*
    ```
Pretty: Prints the Gherkin source with additional colors and stack traces for errors.
plugin = {"pretty"}

HTML Report: Generates a basic HTML report in the specified directory.
plugin = {"html:target/cucumber-reports.html"}

JSON Report: Generates a JSON report, which can be used for further processing or to generate other reports.
plugin = {"json:target/cucumber-reports/Cucumber.json"}

JUnit Report (XML format): Generates a JUnit XML report, which is useful for integrating with CI tools like Jenkins.
plugin = {"junit:target/cucumber-reports/Cucumber.xml"}

Rerun: Creates a file with scenarios that failed. This can be used to rerun only the failed scenarios.
plugin = {"rerun:target/rerun.txt"}

Progress: Displays dots or symbols in the console for each scenario step, providing a quick overview of the progress.
plugin = {"progress"}

Usage: Displays the step definitions that were used and how many times they were executed.
plugin = {"usage:target/cucumber-reports/usage.json"}

Timeline: Generates an interactive timeline report that provides insights into the parallel execution of tests.
plugin = {"timeline:target/cucumber-reports/timeline"}

Detailed Pretty Report: Combines the pretty and html plugins to generate a detailed HTML report.
plugin = {"pretty", "html:target/cucumber-reports.html"}
```
     */

}
