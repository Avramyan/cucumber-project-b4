package io.loop.utilities;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;
import static org.junit.Assert.assertTrue;

public class BrowserUtils {

public static Scenario myScenario;

    /**
     * takes screenshot
     * @author AA
     */
    public static void takeScreenShot() {
        try{
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());

        }catch (WebDriverException e){
            e.getMessage();

        }catch (ClassCastException e){
            e.getMessage();
        }

    }


    /**
     * validate if driver switched to expected URL or title
     *
     * @param driver
     * @param expectedURL
     * @param expectedTitle implements assertion
     */
    public static void switchWindowAndValidate(WebDriver driver, String expectedURL, String expectedTitle) {
        expectedTitle = expectedTitle.toLowerCase();
        expectedURL = expectedURL.toLowerCase();

        // get all windowHandles, switch one by one each time check if the URL matches expected to stop
        Set<String> windowHandles = driver.getWindowHandles();
        for (String each : windowHandles) {
            driver.switchTo().window(each);
            if (driver.getCurrentUrl().toLowerCase().contains(expectedURL)) {
                break;
            }
        }
        // after
        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));


    }

    /**
     * @param driver
     * @param targetTitle
     * @author artemavramov
     */
    public static void switchTohWindow(WebDriver driver, String targetTitle) {
        String origin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().toLowerCase().contains(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);
    }


    /**
     * click any link from loop practice
     *
     * @param nameOfPage
     * @author AA
     */
    public static void loopLinkClick(String nameOfPage) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[.='" + nameOfPage + "']"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * waits for provided element to be clickable
     * @param element
     *
     * @param timeout
     * @return
     * @author AA
     */
    public static WebElement waitForClickable(WebElement element, int timeout){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout ));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * waits for the provided element to be invisible on the page
     * @param element
     * @param timeToWaitInSec
     * @author AA
     */

    public static  void waitForInvisibility(WebElement element, int timeToWaitInSec){
      WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
      wait.until(ExpectedConditions.invisibilityOf(element));
    }
    /**
     * waits for the provided element to be visible
     * @oaram element
     * @param timeToWaitInSec
     * @author AA
     */

    public static WebElement waitForVisible(WebElement element, int timeToWaitInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}

