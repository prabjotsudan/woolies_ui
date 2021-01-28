package au.com.woolies.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {

    private static int shortWait = 6;
    private static int mediumWait = 15;
    private static int longWaitTime = 30;
    private static final Logger log = LogManager.getLogger(DriverManager.class);

    private static Map <Thread, WebDriver> driverMap = new ConcurrentHashMap<>();
    private static Properties setupProperties = PropertyReader.getSetupProperties();

    private static WebDriver initDriver(String browser) throws MalformedURLException {
        WebDriver driver = null;
        if (setupProperties.getProperty("useBrowserStack").equals("true")){
            log.info("Initialising remote webdriver with url: {}", setupProperties.getProperty("BS.url"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability("os", setupProperties.getProperty("BS.os"));
            caps.setCapability("os_version", setupProperties.getProperty("BS.os.version"));
            caps.setCapability("browser", setupProperties.getProperty("BS.browser"));
            caps.setCapability("browser_version", setupProperties.getProperty("BS.browser.version"));
            caps.setCapability("name", setupProperties.getProperty("BS.name")+" "+dtf.format(now));



            driver = new RemoteWebDriver(new URL(setupProperties.getProperty("BS.url")), caps);
        }
        else {
            log.debug("Initialising '{}' driver", browser);
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "internetexplorer":
                    // used x32 cause sendKeys with x64 is very slow
                    WebDriverManager.iedriver().arch32().setup();
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    break;
            }
        }
        driverMap.put(Thread.currentThread(), driver);
        return driver;
    }

    public static WebDriver get() {
        try {
            WebDriver driver = driverMap.get(Thread.currentThread());
            if (driver == null) {
                return initDriver(setupProperties.getProperty("browser"));
            } else {
                return driver;
            }
        }
        catch(MalformedURLException e){
            log.error(e.toString());
            Assert.fail("Failed to initialise driver");
            return null;
        }
    }

    public static void quitDriver(){
        WebDriver driver = driverMap.get(Thread.currentThread());
        if (driverMap.containsKey(Thread.currentThread())) {
            driver.quit();
            driverMap.remove(Thread.currentThread());
            log.debug("Driver closed - '{}", driver);
        }
        else{
            log.debug("No active driver found for '{}' thread", Thread.currentThread());
        }
    }

    public static FluentWait getFluentWait(int timeout) {
        return (new FluentWait(get())).withTimeout(Duration.ofSeconds((long)timeout))
                .pollingEvery(Duration.ofMillis(250L))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(InvalidElementStateException.class);
    }

    public static FluentWait<WebDriver> shortWait() {
        return getFluentWait(shortWait);
    }

    public static FluentWait<WebDriver> mediumWait() {
        return getFluentWait(mediumWait);
    }

    public static FluentWait<WebDriver> longWait() {
        return getFluentWait(longWaitTime);
    }

}
