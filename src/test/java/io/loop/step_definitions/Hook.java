package io.loop.step_definitions;

import io.cucumber.java.*;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class Hook {


    private static final Logger LOG = LogManager.getLogger();

    @Before
    public void before(Scenario scenario) {
        Driver.getDriver();
        BrowserUtils.myScenario = scenario;
        LOG.info(".....START AUTOMATION......LOOPCAMP");
    }
    @After
    public void after(Scenario scenario) {
        //only takes screenshot if scenario is failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
//        Driver.closeDriver();

    }
    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        BrowserUtils.takeScreenShot();
    }
    @AfterAll
            public static void afterAll() {
//        Driver.closeDriver();
  LOG.info(".....FINISH AUTOMATION......LOOPCAMP");
    }
}

