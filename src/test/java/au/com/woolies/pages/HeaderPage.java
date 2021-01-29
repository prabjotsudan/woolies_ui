package au.com.woolies.pages;

import au.com.woolies.utils.GenericMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HeaderPage extends BasePage{
    private static final Logger log = LogManager.getLogger(HeaderPage.class);


    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInLink;

    @FindBy(xpath = "//div[@class='header_user_info']/a/span")
    private WebElement loggedInUserText;

    @FindBy(xpath = "//input[@id='search_query_top']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@name='submit_search']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cartLink;

    @FindBy(xpath = "//span[@class='navigation_page']")
    private WebElement navigationIndicator;




    public void clickSignInLink(){
        GenericMethods.clickElement(signInLink);
        log.debug("Clicked SignIn link from header");
    }

    public String getLoggedInUser(){
        String loggedInUser = GenericMethods.getText(loggedInUserText);
        log.debug("Logged in user is {}", loggedInUser);
        return loggedInUser;
    }

    public void enterSearchText(String searchText){
        GenericMethods.sendKeys(searchInput, searchText);
        log.debug("Entered '{}' in search box", searchText);
    }

    public void clickSearchButton(){

        GenericMethods.clickElement(searchButton);
        log.debug("Clicked search button");
    }

    public void clickCartLink(){
        GenericMethods.clickElement(cartLink);
        log.debug("Clicked shopping cart link from header");

    }

    public String getCurrentPageIndicator(){
        String currentPageIndicator = GenericMethods.getText(navigationIndicator);
        log.debug("Current page is '{}'", currentPageIndicator);
        return currentPageIndicator;
    }















}
