package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {
    public GoogleSearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(name = "q" )
    public WebElement searchBox;
    @FindBy(xpath = "//input[@id='gbqfbb']//preceding-sibling::input")
    WebElement getSearchButton;
    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    public WebElement captcha;
}
