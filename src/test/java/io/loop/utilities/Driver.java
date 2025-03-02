package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    /*
    Creating a private constructor to prevent instantiation
    */
    private Driver() {
    }

    /*
    ThreadLocal instance for WebDriver to support parallel execution
    */
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /**
     * Returns a singleton WebDriver instance using ThreadLocal
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {  // Corrected condition
            String browserType = ConfigurationReader.getProperties("browser");

            switch (browserType.toLowerCase()) {
                case "chrome":
                    driverPool.set(new ChromeDriver());
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    break;
                case "safari":
                    driverPool.set(new SafariDriver());
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browserType);
            }

            // Set common driver properties
            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driverPool.get();
    }

    /**
     * Closes the WebDriver and removes it from ThreadLocal
     */
    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}