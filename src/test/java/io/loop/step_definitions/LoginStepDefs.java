package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.LoginPage;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.ConfigurationReader;

import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;

import java.util.Map;

import static org.junit.Assert.assertTrue;


public class LoginStepDefs {
    LoginPage loginPage = new LoginPage();

    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
    }

    @When("user enters username for client")
    public void user_enters_username_for_client() {
        BrowserUtils.waitForClickable(loginPage.loginButton, DocuportConstants.EXTRA_LARGE);
        assertTrue("login button in not displayed", loginPage.loginButton.isDisplayed());
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);


    }

    @When("user enters password for client")
    public void user_enters_password_for_client() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD);


    }

    @When("user click login button")
    public void user_click_login_button() {
        loginPage.loginButton.click();

    }

    @Then("user should be able to see the home for client")
    public void user_should_be_able_to_see_the_home_for_client() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue("intentionaly failing", true);
    }

    @When("user enters username for employee")
    public void user_enters_username_for_employee() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("user enters password for employee")
    public void user_enters_password_for_employee() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("user should be able to see the home for employee")
    public void user_should_be_able_to_see_the_home_for_employee() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("user enters username for advisor")
    public void user_enters_username_for_advisor() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("user enters password for advisor")
    public void user_enters_password_for_advisor() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("user should be able to see the home for advisor")
    public void user_should_be_able_to_see_the_home_for_advisor() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("user enters username for supervisor")
    public void user_enters_username_for_supervisor() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("user enters password for supervisor")
    public void user_enters_password_for_supervisor() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("user should be able to see the home for supervisor")
    public void user_should_be_able_to_see_the_home_for_supervisor() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("user enters credentials")
    public void user_enters_credentials(Map<String, String> credentials) {
//        for (Map.Entry<String, String> entry : credentials.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println("key: " + key + " value: " + value);
//        }
        loginPage.login2(credentials.get("username"), credentials.get("password"));


    }


    }

