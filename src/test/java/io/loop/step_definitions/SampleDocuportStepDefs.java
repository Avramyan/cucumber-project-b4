package io.loop.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.HomePage;
import io.loop.pages.LeftNavigatePage;
import io.loop.pages.LoginPage;
import io.loop.pages.ReceivedDocs;
import io.loop.pages.ReceivedDocs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SampleDocuportStepDefs {

    LoginPage loginPage = new LoginPage();
    ReceivedDocs receivedDocs = new ReceivedDocs();
    LeftNavigatePage leftNavigatePage = new LeftNavigatePage();
    private static final Logger LOG = LogManager.getLogger();

    @When("user inserts {string} to {string} field on {string} page")
    public void user_inserts_to_field_on_page(String input, String field, String page) {
        switch (page.toLowerCase().trim()){
            case "login":
                loginPage.insertField(field,input);
                LOG.info(input + " - was successfully sent to -  " + field);
                break;
            case "received doc":
                receivedDocs.insertField(field, input);
                LOG.info(input + " - was successfully sent to -  " + field);
                break;
            default: throw new IllegalArgumentException("not such a page: " + page);

        }
    }
    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) {
        switch (page.toLowerCase().trim()){
            case "login", "choose account":
                loginPage.clickButton(button);
                LOG.info(button + " - was successfully clicked");
                break;
            case "left navigate":
                leftNavigatePage.clickButton(button);
                LOG.info(button + " - was successfully clicked");
                break;
            case "received doc":
                receivedDocs.clickButton(button);
                LOG.info(button + " - was successfully clicked");
                break;
            default: throw new IllegalArgumentException("not such a page: " + page);
        }
    }
}