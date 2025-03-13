package io.loop.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.*;
import io.loop.pages.ReceivedDocs;
import io.loop.utilities.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class SampleDocuportStepDefs {

    LoginPage loginPage = new LoginPage();
    ReceivedDocs receivedDocs = new ReceivedDocs();
    LeftNavigatePage leftNavigatePage = new LeftNavigatePage();
    MyUploadsPage myUploadsPage = new MyUploadsPage();
    private static final Logger LOG = LogManager.getLogger();

    @When("user inserts {string} to {string} field on {string} page")
    public void user_inserts_to_field_on_page(String input, String field, String page) {
        switch (page.toLowerCase().trim()) {
            case "login":
                loginPage.insertField(field, input);
                LOG.info(input + " - was successfully sent to -  " + field);
                break;
            case "received doc":
                receivedDocs.insertField(field, input);
                LOG.info(input + " - was successfully sent to -  " + field);
                break;
            default:
                throw new IllegalArgumentException("not such a page: " + page);

        }
    }

    @When("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) {
        switch (page.toLowerCase().trim()) {
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
            case "my uploads":
                myUploadsPage.clickButton(button);
                LOG.info(button + " - was successfully clicked");
                break;
            default:
                throw new IllegalArgumentException("not such a page: " + page);
        }
    }

    //selenium upload
//    @Then("user uploads document")
//    public void user_uploads_document() {
//
//        WebElement element = Driver.getDriver().findElement(By.xpath("//input[@type='file']"));
//        element.sendKeys("/Users/artemavramov/Desktop/note.txt");


        // Robot upload
    @Then("user uploads document")
    public void user_uploads_document() throws Exception {

        BrowserUtils.uploadFileUsingAppleScript("/Users/artemavramov/Desktop/note.txt");
    }

    }
