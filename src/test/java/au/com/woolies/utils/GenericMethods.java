package au.com.woolies.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;



public class GenericMethods {
    private static final Logger log = LogManager.getLogger(GenericMethods.class);
    static String text = null;


    /**
     * Function to navigate to given address
     * @param url Address to navigate to
     */
    public static void navigateTo(String url){
        DriverManager.get().navigate().to(url);
        DriverManager.get().manage().window().maximize();
    }

    public static void clickElement(WebElement element) {
        try {
            DriverManager.shortWait().until(ExpectedConditions.and(ExpectedConditions.visibilityOf(element),
                    ExpectedConditions.elementToBeClickable(element)));
            DriverManager.shortWait().until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (TimeoutException | StaleElementReferenceException e) {

            log.error("Exception raised in clickElement : ", e);
            Assert.fail("The element is not present in the page");
        } catch (Exception e) {
            log.error("Exception raised in clickElement : ", e);
            Assert.fail("Failed to click the element");
        }
    }

    public static void sendKeys(WebElement element, String enterText) {
        try {
            DriverManager.shortWait().until(ExpectedConditions.visibilityOf(element));
            element.click();
            element.sendKeys(enterText);
        } catch (TimeoutException | NoSuchElementException e) {

            log.error("Exception raised in sendKeys : ", e);
            Assert.fail("The element is not present in the page to enter the text");
        } catch (Exception e) {

            log.error("Exception raised in sendKeys : ", e);
            Assert.fail("Failed to enter text");
        }
    }

    public static String getText(WebElement element) {
        try {
            DriverManager.shortWait().until(ExpectedConditions.visibilityOf(element));
            text = element.getText();
        } catch (NoSuchElementException e) {

            log.error("Exception raised in getText : ", e);
            Assert.fail("The element is not present in the page to get the text");
        } catch (Exception e) {

            log.error("Exception raised in getText : ", e);
            Assert.fail("Failed to fetch text");
        }
        return text;
    }

    public static void clearElement(WebElement element) {
        try {
            DriverManager.shortWait().until(ExpectedConditions.visibilityOf(element));
            element.clear();
        } catch (TimeoutException | NoSuchElementException e) {

            log.error("Exception raised in clearElement : ", e);
            Assert.fail("The element is not present in the page to clear text");
        } catch (Exception e) {

            log.error("Exception raised in clearElement : ", e);
            Assert.fail("Failed to clear text");
        }
    }

    public static void selectByValue(WebElement element, String enterText) {
        try {
            DriverManager.shortWait().until(ExpectedConditions.textToBePresentInElement(element, enterText));
            Select s = new Select(element);
            s.selectByVisibleText(enterText);
        } catch (NoSuchElementException | StaleElementReferenceException e) {

            log.error("Exception raised in selectByValue : ", e);
            Assert.fail("The element is not present in the page to select the value");
        } catch (Exception e) {

            log.error("Exception raised in selectByValue : ", e);
            Assert.fail("Failed to select value from dropdown");
        }
    }

    public static boolean assertElementIsDisplayed(WebElement element) {
        try {
            DriverManager.mediumWait().until(ExpectedConditions.and(ExpectedConditions.visibilityOf(element)));
            Assert.assertTrue(isDisplayed(element));
        } catch (TimeoutException | NoSuchElementException e) {

            log.error("Exception raised in assertElementIsDisplayed : ", e);
            Assert.fail("The element is not present in the page");
            return false;
        } catch (Exception e) {

            log.error("Exception raised in assertElementIsDisplayed : ", e);
            Assert.fail("The element is not present in the page");
            return false;
        }
        return true;
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            log.debug("Checking if element is displayed");
            element.isDisplayed();

            return true;
        } catch (Exception e) {

            log.debug("The element is not displayed");
            return false;
        }
    }

    public static String getPageTitle(){
        try {
            log.debug("Fetching page title");
            String title = DriverManager.get().getTitle();
            log.debug("Page title is {}", title);
            return title;
        } catch (Exception e) {

            log.error("Exception raised in getPageTitle : ", e);
            Assert.fail("Failed to fetch page title");
            return null;
        }
    }

    public static String getAttribute(WebElement element, String attributeName){
        log.debug("Fetching '{}' attribute from webElement", attributeName);
        String attribute = element.getAttribute(attributeName);
        log.debug("Attribute value is '{}'", attribute);
        return attribute;
    }

    public static void selectCheckbox(WebElement element) {
        try {
            element.click();
        } catch (TimeoutException | NoSuchElementException e) {

            log.error("Exception raised in selectCheckbox : ", e);
            Assert.fail("The element is not present in the page to select");
        } catch (Exception e) {

            log.error("Exception raised in selectCheckbox : ", e);
            Assert.fail("Failed to select checkbox");
        }
    }

}
