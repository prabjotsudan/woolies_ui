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

    public void clickSignInLink(){
        GenericMethods.clickElement(signInLink);
    }

    public String getLoggedInUser(){
        return GenericMethods.getText(loggedInUserText);
    }

    public void enterSearchText(String searchText){
        GenericMethods.sendKeys(searchInput, searchText);
    }

    public void clickSearchButton(){
        GenericMethods.clickElement(searchButton);
    }















}
