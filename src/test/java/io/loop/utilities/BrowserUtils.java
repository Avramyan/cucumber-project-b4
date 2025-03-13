package io.loop.utilities;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class BrowserUtils {
    private static final Logger LOG = LogManager.getLogger();
    public static Scenario myScenario;

    /**
     * takes screenshot
     *
     * @author AA
     */
    public static void takeScreenShot() {
        try {
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());

        } catch (WebDriverException e) {
            e.getMessage();

        } catch (ClassCastException e) {
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
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     * @author AA
     */
    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * waits for the provided element to be invisible on the page
     *
     * @param element
     * @param timeToWaitInSec
     * @author AA
     */

    public static void waitForInvisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * waits for the provided element to be visible
     *
     * @param timeToWaitInSec
     * @oaram element
     * @author AA
     */

    public static WebElement waitForVisible(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

//    public static void uploadFile(String filePath) throws AWTException, IOException {
//        StringSelection selection = new StringSelection(filePath);
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
//
//        //simulate keyboard
//        Robot robot = new Robot();
//        robot.delay(1000);
//        // press

    /// /        robot.keyPress(KeyEvent.);
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        robot.delay(1000);
//
//        robot.keyPress(KeyEvent.VK_META);
//        robot.keyPress(KeyEvent.VK_SHIFT);
//        robot.keyRelease(KeyEvent.VK_G);
//
//        robot.keyRelease(KeyEvent.VK_META);
//        robot.keyRelease(KeyEvent.VK_SHIFT);
//        robot.keyRelease(KeyEvent.VK_G);
//
//        robot.delay(1000);
//
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        robot.delay(1000);
    public static void uploadFileUsingAppleScript(String filePath) throws IOException, InterruptedException {
        String appleScriptCommand =
                "tell application \"System Events\"\n" +
                        "    delay 1\n" +
                        "    keystroke \"G\" using {command down, shift down}\n" +
                        "    delay 1\n" +
                        "    keystroke \"" + filePath + "\"\n" +
                        "    keystroke return\n" +
                        "    delay 1\n" +
                        "    keystroke return\n" +
                        "end tell";

        String[] command = { "osascript", "-e", appleScriptCommand };
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }







    /**
     * Move the mouse to given element
     *
     * @param element on which to hover
     * @author AA
     */

    public static void hover(WebElement element) {
        Actions action = new Actions(Driver.getDriver());
        action.moveToElement(element).build().perform();

    }

    /**
     * Scrolls down to an element using JavaScript
     *
     * @param element
     * @author nadir
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     * @author nadir
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true)", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     * @author nadir
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).perform();
    }

    public static void justWait(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
    }

    public static List<String> getElementsText(List<WebElement> elements){
        List<String> elementsText = new ArrayList<>();
        for (WebElement element : elements) {
            elementsText.add(element.getText());
        }
        return elementsText;
    }

    public static List<String> getElementsTextWithStream(List<WebElement> elements){
        return elements.stream()
                .map(x->x.getText())
                .collect(Collectors.toList());

    }

    public static List<String> getElementsTextWithStream2(List<WebElement> elements){
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    public static void waitForPageLoad(long timeOutInSeconds){
        ExpectedCondition<Boolean> expectedConditions = new ExpectedCondition<Boolean>(){
            public Boolean apply (WebDriver driver){
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            LOG.info("Waiting for page load");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectedConditions);
        } catch (Throwable error){
            LOG.error("Timeout waiting for the Page Load Request completed after: " + timeOutInSeconds + " seconds");
        }
    }
    public static void waitForStaleElement(WebElement element){
        int y = 0;

        while (y<=15){
            try{
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st){
                y++;
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException i){
                    i.printStackTrace();
                }
            } catch (WebDriverException we){
                y++;
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException i){
                    i.printStackTrace();
                }
            }
        }
    }


    public static void waitUntilPageLoad(){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
        wait.until((WebDriver d) ->{
            Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) Driver.getDriver())
                    .executeScript("return document.readyState").equals("complete");
            if(!isPageLoaded)
                LOG.info("Document is loading");
            return isPageLoaded;
        });
    }

    public static void createFileWithContent(String filePath, String content){
        File file = new File(filePath);
        try{
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            try{
                fw.write(content);
            }catch (IOException e){
                LOG.error(e.getMessage());

            }finally {
                try {
                    fw.close();
                }catch (IOException e){
                    LOG.error(e.getMessage());
                }
            }
        }catch (IOException e){
            LOG.error(e.getMessage(),e.getCause());
        }


    }
}







