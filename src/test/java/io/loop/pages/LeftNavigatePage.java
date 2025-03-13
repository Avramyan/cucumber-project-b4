package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftNavigatePage {


    public LeftNavigatePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    public WebElement homeButton;

    @FindBy(xpath = "//span[contains(text(),'Received')]")
    public WebElement receivedDocsButton;

    @FindBy(xpath = "//span[contains(text(),'My uploads')]")
    public WebElement myUploads;

    @FindBy(xpath = "//span[contains(text(),'Invitations')]")
    public WebElement invitationsButton;

    @FindBy(xpath = "//a[contains(text(),'Terms')]")
    public WebElement termsAndConditionsButton;

    public void clickButton(String button){
        switch (button.toLowerCase().trim()){
            case "received doc":
                BrowserUtils.waitForClickable(receivedDocsButton, 10).click();
                break;
            case "home":
                BrowserUtils.waitForClickable(homeButton, 10).click();
                break;

            case "upload":
                BrowserUtils.waitForClickable(uploadButton, 10).click();
                break;

            case "invitations":
                BrowserUtils.waitForClickable(invitationsButton, 10).click();
                break;

            case "terms and conditions":
                BrowserUtils.waitForClickable(termsAndConditionsButton, 10).click();
                break;

            case "my uploads":
                BrowserUtils.waitForVisible(myUploads, 10).click();
                break;

            default: throw new IllegalArgumentException("Not such a button: " + button);
        }
    }
}