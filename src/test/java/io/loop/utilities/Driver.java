package io.loop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
                    break;
                case "safari":
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
                    break;
                case "headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    driverPool.set(new ChromeDriver(options));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(ConfigurationReader.getProperties("timeouts"))));
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browserType);
            }

            // Set common driver properties

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