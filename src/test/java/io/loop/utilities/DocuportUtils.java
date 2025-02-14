package io.loop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class DocuportUtils {

    /**
     * logins to docuport application
     *
     * @param driver, which is initialized in the test base
     * @param role,   comes from docuport constants
     *                author nsh
     */
    public static void login(WebDriver driver, String role) throws InterruptedException {
        driver.get(ConfigurationReader.getProperties("docuportBETA"));
        WebElement username = driver.findElement(By.xpath("//label[.='Username or email']/following-sibling::input"));
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        switch (role.toLowerCase()) {
            case "client":
                username.sendKeys(DocuportConstants.USERNAME_CLIENT);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "advisor":
                username.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                password.sendKeys(DocuportConstants.PASSWORD);
                break;
            default:
                throw new InterruptedException("There is not such a role:" + role);
        }
        loginButton.click();

        if (role.toLowerCase().equals("client")) {
            Thread.sleep(2000);
            WebElement con = driver.findElement(By.xpath("//button[@type='submit']"));
            con.click();
        }
    }

    /**
     * logs out fron Docuport app
     *
     * @param driver
     * @author artemavramov
     */

    public static void logout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement userIcon = driver.findElement(By.xpath("//span[.='BG']"));
        userIcon.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement logoutButton = driver.findElement(By.xpath("//span[.='Log out']"));
        logoutButton.click();
    }
}
