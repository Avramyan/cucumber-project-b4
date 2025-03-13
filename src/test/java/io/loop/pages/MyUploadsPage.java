package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyUploadsPage {

    public MyUploadsPage (){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//span[contains(text(),'Upload file')]/../..")
    public WebElement uploadFile;

    @FindBy(xpath = "//span[contains(text(),'Upload documents')]/../..")
    public WebElement uploadDocuments;

    @FindBy (xpath = "//span[contains(text(),' Upload ')]")
    public WebElement upload;

    @FindBy (xpath = "//span[contains(text(),'Search')]")
    public WebElement searchDropdown;

    @FindBy(xpath = "//p[@class='mb-0 subtitle-1']")
    public WebElement resultMessage;


    public void clickButton(String button){
        switch (button.toLowerCase().trim()){
            case "upload documents":
                BrowserUtils.waitForClickable(uploadDocuments, 10).click();
                break;
            case "upload":
                BrowserUtils.waitForClickable(upload,10).click();
                break;
            case "search dropdown":
                BrowserUtils.waitForClickable(searchDropdown,10).click();
                break;
            case "upload file":
                BrowserUtils.waitForClickable(uploadFile,10).click();
                break;

            default: throw new IllegalArgumentException("Not such a button: " + button);
        }
    }
}