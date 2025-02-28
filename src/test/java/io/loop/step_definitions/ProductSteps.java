package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.loop.pages.POM;
import io.loop.pages.ProductPage;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProductSteps {

    POM pages = new POM();
    ProductPage productPage = new ProductPage();
    private static final Logger LOG = LogManager.getLogger();

    @Given("User is on the HomePage")
    public void user_is_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("product"));
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        LOG.info("User is on Homepage");
    }

    @Then("User should be able to see expected prices in the following products")
    public void user_should_be_able_to_see_expected_prices_in_the_following_products(List<Map<String, String>> productDetails) {
        for (Map<String, String> productDetail : productDetails) {
            // Click the category
            pages.getProductPage().clickCategory(productDetail.get("Category"));

            // Get actual price
            String actualPrice = pages.getProductPage().getProductPrice(productDetail.get("Product"));

            // Get expected price from the data table
            String expectedPrice = productDetail.get("expectedPrice");

            // Do assertion
            assertEquals("Expected does not match the actual", expectedPrice, actualPrice);
            LOG.info("Validation of the price for: " + productDetail.get("Category") +
                    ", Product: " + productDetail.get("Product") +
                    " - Expected: " + expectedPrice +
                    " - Actual: " + actualPrice);
        }
    }

    @Then("User should be able to see expected prices in the following products with listOfList")
    public void user_should_be_able_to_see_expected_prices_in_the_following_products_with_list_of_list(List<List<String>> productDetails) {
        for (List<String> productDetail : productDetails) {
            // Click the category
            productPage.clickCategory(productDetail.get(0));

            // Get actual price for each product
            String actualPrice = productPage.getProductPrice(productDetail.get(1));

            // Get expected price from feature file
            String expectedPrice = productDetail.get(2);

            // Assertion
            assertEquals("Expected does not match the actual", expectedPrice, actualPrice);
            LOG.info("Validation of the price for: " + productDetail.get(0) +
                    ", Product: " + productDetail.get(1) +
                    " - Expected: " + expectedPrice +
                    " - Actual: " + actualPrice);
        }
    }
    @Then("user should be able to see the following names in their groups")
    public void user_should_be_able_to_see_the_following_names_in_their_groups(Map<String,List<String>> students)  {
        List<String> group = students.get("Group 1");
        System.out.println("Group 1: " + group);
        List<String> group2 = students.get("Group 2");
        System.out.println("Group 2: " + group2);

    }
}