package au.com.woolies.pages;

import au.com.woolies.utils.DriverManager;
import au.com.woolies.utils.GenericMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Homepage extends BasePage{
    private static final Logger log = LogManager.getLogger(Homepage.class);


    @FindBy(xpath = "//input[@id='search_query_top']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@name='submit_search']")
    private WebElement searchButton;











}
