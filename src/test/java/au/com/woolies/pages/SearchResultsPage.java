package au.com.woolies.pages;

import au.com.woolies.utils.GenericMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchResultsPage extends BasePage{
    private static final Logger log = LogManager.getLogger(SearchResultsPage.class);


    @FindBy(xpath = "//ul[contains(@class,'product_list')]/li[1]")
    private WebElement firstSearchResult;

    public void openFirstSearchResult(){
        GenericMethods.clickElement(firstSearchResult);
    }


}
