package au.com.woolies.businessLayers;


import au.com.woolies.pages.HeaderPage;
import au.com.woolies.pages.Homepage;
import au.com.woolies.utils.DriverManager;
import au.com.woolies.utils.GenericMethods;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class HeaderLib {

    private static final Logger log = LogManager.getLogger(HeaderLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    //GenericMethods genericMethods = new GenericMethods();
    Homepage homepage = new Homepage();
    HeaderPage headerPage = new HeaderPage();


    public void searchProduct(String productName){
        log.info("Searching for '{}'", productName);
        headerPage.enterSearchText(productName);
        headerPage.clickSearchButton();
    }





}
