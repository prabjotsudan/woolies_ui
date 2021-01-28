package au.com.woolies.businessLayers;


import au.com.woolies.pages.ProductDetailsPage;
import au.com.woolies.pages.SearchResultsPage;
import au.com.woolies.utils.GenericMethods;
import au.com.woolies.utils.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class ProductDetailsLib {

    private static final Logger log = LogManager.getLogger(ProductDetailsLib.class);
    private static Properties setupProperties = PropertyReader.getSetupProperties();
    //GenericMethods genericMethods = new GenericMethods();
//    Homepage homepage = new Homepage();
//    HeaderPage headerPage = new HeaderPage();
    ProductDetailsPage productDetailsPage = new ProductDetailsPage();

    public void enterProductDetails(String quantity, String size, String colour){
        log.info("Selecting product details as per order");
        productDetailsPage.enterProductQuantity(quantity);
        productDetailsPage.selectProductSize(size);
        productDetailsPage.selectProductColour(colour);
    }

    public void addToCart(){
        productDetailsPage.addToCart();
    }




}
