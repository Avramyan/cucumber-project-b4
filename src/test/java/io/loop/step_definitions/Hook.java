package io.loop.step_definitions;

import io.cucumber.java.*;
import io.loop.utilities.Driver;

public class Hook {
    @Before
    public void before() {
        Driver.getDriver();

    }
    @After
    public void after() {
        Driver.closeDriver();

    }

}
