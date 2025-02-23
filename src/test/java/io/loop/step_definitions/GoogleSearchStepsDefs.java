package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.GoogleSearchPage;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.module.Configuration;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GoogleSearchStepsDefs {
    GoogleSearchPage googleSearchPage = new GoogleSearchPage();

    @Given("user in on Google search page")
    public void user_in_on_google_search_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties(
                "google.url"));

    }

    @When("user types Loop Academy in the google search box and clicks enter")
    public void user_types_loop_academy_in_the_google_search_box_and_clicks_enter() {

        googleSearchPage.searchBox.sendKeys("Loop Academy" + Keys.ENTER);

    }

    @Then("user should see Loop Academy - Google search in the google title")
    public void user_should_see_loop_academy_google_search_in_the_google_title() {

        WebElement element = Driver.getDriver().findElement(By.xpath("//iframe[@title='reCAPTCHA']"));

        Driver.getDriver().switchTo().frame(element);

        if (googleSearchPage.captcha.isDisplayed()) {
            googleSearchPage.captcha.click();
        }

        Driver.getDriver().switchTo().defaultContent();
        // Write code here that turns the phrase above into concrete actions

    }

    @When("user types {string} in google searchBox and clicks enter")
    public void user_types_in_google_searchbox_and_clicks_enter(String input) {
        googleSearchPage.searchBox.sendKeys(input + Keys.ENTER);
        WebElement element = Driver.getDriver().findElement(By.xpath("//iframe[@title='reCAPTCHA']"));

        Driver.getDriver().switchTo().frame(element);

        if (googleSearchPage.captcha.isDisplayed()) {
            googleSearchPage.captcha.click();
        }

        Driver.getDriver().switchTo().defaultContent();

    }

    @Then("user should see {string} in the google title")
    public void user_should_see_in_the_google_title(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Loop academy - Google Search"));
        String actualTitle = Driver.getDriver().getTitle();
        assertEquals("Expected does not match actual title", expectedTitle, actualTitle);

    }

    @Then("user searches the following items")
    public void user_searches_the_following_items(List<String> items) {
//        for (String item : items) {
//            googleSearchPage.searchBox.clear();
//            googleSearchPage.searchBox.sendKeys(item + Keys.ENTER);
//            googleSearchPage.handleCaptcha();
//            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
//            wait.until(ExpectedConditions.titleIs(item + " - Google Search"));
//            assertEquals("Expected doe NOT match", item + " - Google Search", Driver.getDriver().getTitle());
//
//        }
        items.forEach(p -> {
            googleSearchPage.searchBox.clear();
            googleSearchPage.searchBox.sendKeys(p + Keys.ENTER);
            googleSearchPage.handleCaptcha(Driver.getDriver(), googleSearchPage.captcha);
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.titleIs(p + " - Google Search"));
            assertEquals("Expected doe NOT match", p + " - Google Search", Driver.getDriver().getTitle());
        });
    }

    @Then("we love loop academy")
    public void we_love_loop_academy() {
        System.out.println("we love loop academy");
    }

    @When("user search for {string}")
    public void user_search_for(String country) {
        googleSearchPage.searchBox.sendKeys("What is the capital of " + country + Keys.ENTER);
    }

    @Then("user should see the {string} in the results")
    public void user_should_see_the_in_the_results(String capital) {
        googleSearchPage.handleCaptcha(Driver.getDriver(), googleSearchPage.captcha);


        assertEquals("Expected capital city: " + capital + " does NOT match actual one: " + googleSearchPage.capital.getText(), capital, googleSearchPage.capital.getText());
    }
}
