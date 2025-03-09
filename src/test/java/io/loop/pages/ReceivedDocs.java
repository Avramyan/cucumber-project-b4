package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceivedDocs {


    public ReceivedDocs() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//span[contains(text(),'Search')]")
    public WebElement searchButton;

    @FindBy(xpath = "//label[contains(text(),'Document name')]/following-sibling::input")
    public WebElement documentName;

    @FindBy(xpath = "//label[.='Tags']/following-sibling::div/input")
    public WebElement tagsInput;


    @FindBy(xpath = "//label[.='Upload date']/following-sibling::input")
    public WebElement dateIput;

    @FindBy(xpath = "//label[.='Uploaded by']/following-sibling::input")
    public WebElement uploadedByInput;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "search":
                BrowserUtils.waitForClickable(searchButton, 10).click();
                break;
            case "tags":
                BrowserUtils.waitForClickable(tagsInput, 10).click();

                break;
            case "date":
                BrowserUtils.waitForClickable(dateIput, 10).click();
                break;
            case "upload":
                BrowserUtils.waitForClickable(uploadedByInput, 10).click();
            default:
                throw new IllegalArgumentException("Not such a button: " + button);
        }
    }

    public void insertField(String field, String input) {
        switch (field.toLowerCase().trim()) {
            case "document name":
                BrowserUtils.waitForVisible(documentName, 10).sendKeys(input);

                break;
            case "tags":
                BrowserUtils.waitForVisible(tagsInput, 10).sendKeys(input);
                WebElement eachTag = tagsInput.findElement(By.xpath("//span[.='" + input + "']/parent::div/span"));
                BrowserUtils.waitForClickable(eachTag, 10).click();
                break;
            default:
                throw new IllegalArgumentException("No such a field: " + field);
        }
    }
}