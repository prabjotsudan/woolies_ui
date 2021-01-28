package au.com.woolies.businessLayers;


import au.com.woolies.pages.HeaderPage;
import au.com.woolies.pages.Homepage;
import au.com.woolies.pages.SearchResultsPage;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class SearchResultsLib {

    private static final Logger log = LogManager.getLogger(SearchResultsLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    //GenericMethods genericMethods = new GenericMethods();
//    Homepage homepage = new Homepage();
//    HeaderPage headerPage = new HeaderPage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();


    public void openProductDetails(){
        log.info("Opening product details");
        searchResultsPage.openFirstSearchResult();
    }

}
