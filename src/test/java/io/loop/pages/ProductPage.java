package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10)); // Initialize wait

    public ProductPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void clickCategory(String category) {
        // Remove the implicit wait here
        Driver.getDriver().findElement(By.xpath("//a[contains(.,'" + category + "')]")).click();
    }

    public String getProductPrice(String product) {
        By productPriceLocator = By.xpath("//a[contains(text(),'" + product + "')]/../following-sibling::h5");
        String actualPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator)).getText();
        return actualPrice.replace("$", "").trim(); // Clean up price formatting
    }
}