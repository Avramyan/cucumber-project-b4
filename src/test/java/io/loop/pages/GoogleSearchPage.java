package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchPage {
    public GoogleSearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement searchBox;
    @FindBy(xpath = "//input[@id='gbqfbb']//preceding-sibling::input")
    WebElement getSearchButton;
    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    public WebElement captcha;

    @FindBy(xpath = "//div[@class='wvKXQ']")
    public WebElement capital;

    public void handleCaptcha(WebDriver driver, WebElement captchaElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Switch to reCAPTCHA iframe
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@title='reCAPTCHA']")));
            driver.switchTo().frame(iframe);

            // If captcha is displayed, wait for user input
            if (captchaElement.isDisplayed()) {
                System.out.println("Captcha detected! Solve it manually.");
                Thread.sleep(10000);  // 10 seconds to solve manually
            }
        } catch (Exception e) {
            System.out.println("No captcha present, continuing execution.");
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}



