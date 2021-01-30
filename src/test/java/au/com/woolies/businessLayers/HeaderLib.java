package au.com.woolies.businessLayers;


import au.com.woolies.pages.HeaderPage;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.Properties;

public class HeaderLib {

    private static final Logger log = LogManager.getLogger(HeaderLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    private static  String cartPageIndicatorString = "Your shopping cart";
    HeaderPage headerPage = new HeaderPage();

    /**
     * Function to search product
     * @param productName Name of product to be searched
     */
    public void searchProduct(String productName){
        log.info("Searching for '{}'", productName);
        headerPage.enterSearchText(productName);
        headerPage.clickSearchButton();
    }

    /**
     * Function to open shopping cart page
     */
    public void navigateToMyCart(){
        log.info("Opening shopping cart");
        headerPage.clickCartLink();
        if (headerPage.getCurrentPageIndicator().equals(cartPageIndicatorString)){
            log.info("Navigated to shopping cart page");
        }
        else{
            Assert.fail("Failed to open shopping cart");
        }
    }

    /**
     * Function to get current page name from navigation indicator
     */
    public String getCurrentPageIndicator(){
        return headerPage.getCurrentPageIndicator();
    }





}
